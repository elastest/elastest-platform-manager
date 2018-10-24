package io.elastest.epm.api.utils;

import com.jcraft.jsch.Session;
import io.elastest.epm.api.body.WorkerFromVDU;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.AuthCredentials;
import io.elastest.epm.model.Key;
import io.elastest.epm.model.VDU;
import io.elastest.epm.model.Worker;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.generated.ResourceGroupProto;
import io.elastest.epm.properties.ElastestProperties;
import io.elastest.epm.repository.AdapterRepository;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.VduRepository;
import io.elastest.epm.repository.WorkerRepository;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkerLauncher {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElastestProperties elastestProperties;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private SSHHelper sshHelper;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private AdapterRepository adapterRepository;
    @Autowired
    private Utils utils;
    @Autowired
    PoPRepository poPRepository;
    @Autowired
    private AdapterLauncher adapterLauncher;

    @Autowired
    private VduRepository vduRepository;

    @Value("${et.public.host}")
    private String epmIp;

    public Worker configureWorker(Worker worker)
            throws Exception {

        Key key = keyRepository.findOneByName(worker.getAuthCredentials().getKey());

        if (workerRepository.findOneByIp(worker.getIp()) != null)
            throw new Exception("There is already a worker registered at the ip: " + worker.getIp());

        if (keyRepository.findOneByName(worker.getAuthCredentials().getKey()) == null)
            throw new NotFoundException("The key was not found!");

        if (worker.getAuthCredentials().getUser().equals("") || worker.getIp().equals(""))
            throw new NotFoundException(
                    "To register a worker the PoP must provide the InferaceEndpoint"
                            + " and InterfaceInfo containing user, IP of the EPM and passphrase information");

        Session session = sshHelper.createSession(worker, key);
        InputStream compose = new FileInputStream("configuration_scripts/docker-compose-adapters.yml");
        sshHelper.sendFile(session, compose, "docker-compose.yml");

        InputStream configureIs = new FileInputStream("configuration_scripts/preconfigure.sh");
        sshHelper.sendFile(session, configureIs, "preconfigure.sh");

        String empConfig = "";

        if (elastestProperties.getEmp().isEnabled()) {
            empConfig =
                    " "
                            + elastestProperties.getEmp().getEndPoint()
                            + ":"
                            + elastestProperties.getEmp().getPort() + " "
                            + elastestProperties.getEmp().getTopic() + " "
                            + elastestProperties.getEmp().getSeriesName() + " "
                            + worker.getIp();
            sshHelper.executeCommand(session, "sudo su root ./preconfigure.sh " + empConfig);
        }
        session.disconnect();

        if (worker.getType() != null && worker.getType().size() > 0) {
            for (String type: worker.getType()) {
                adapterLauncher.startAdapter(worker, type);
            }
        }

        return workerRepository.save(worker);
    }

    private List<Worker> resourceGroupToWorkers(ResourceGroupProto resourceGroupProto, Key key) throws Exception {
        List<Worker> workers = new ArrayList<>();

        for(io.elastest.epm.pop.generated.VDU vdu : resourceGroupProto.getVdusList()) {
            Worker worker = new Worker();
            AuthCredentials authCredentials = new AuthCredentials();
            authCredentials.setKey(key.getName());
            worker.setIp(vdu.getIp());
            worker.setEpmIp(epmIp);
            // TODO: FIX
            authCredentials.setUser("ubuntu");
            authCredentials.setPassword("");
            authCredentials.setPassphrase("");
            worker.setAuthCredentials(authCredentials);
            workers.add(configureWorker(worker));
        }

        return workers;
    }

    public Worker createWorker(WorkerFromVDU workerFromVDU) throws Exception {
        log.debug("Creating worker from vdu: " + workerFromVDU.getVduId());
        VDU vdu = vduRepository.findOne(workerFromVDU.getVduId());
        if (vdu != null) {
            return workerFromVDU(vdu, workerFromVDU.getType());
        }
        log.debug("VDU NOT FOUND!");
        return null;
    }

    public Worker workerFromVDU(VDU vdu, List<String> type) throws Exception {
        Worker worker = new Worker();
        AuthCredentials authCredentials = new AuthCredentials();
        authCredentials.setKey(vdu.getKey());
        worker.setIp(vdu.getIp());
        worker.setEpmIp(epmIp);
        authCredentials.setUser("ubuntu");
        authCredentials.setPassword("");
        authCredentials.setPassphrase("");
        worker.setAuthCredentials(authCredentials);
        worker.setType(type);
        return configureWorker(worker);
    }
}
