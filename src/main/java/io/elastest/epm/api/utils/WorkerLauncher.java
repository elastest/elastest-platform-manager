package io.elastest.epm.api.utils;

import com.google.protobuf.ByteString;
import com.jcraft.jsch.Session;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.model.Key;
import io.elastest.epm.model.Worker;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.generated.FileMessage;
import io.elastest.epm.pop.generated.OperationHandlerGrpc;
import io.elastest.epm.pop.generated.ResourceGroupProto;
import io.elastest.epm.pop.generated.VDU;
import io.elastest.epm.properties.ElastestProperties;
import io.elastest.epm.repository.AdapterRepository;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.WorkerRepository;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkerLauncher {

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


    @Value("${et.public.host}")
    private String epmIp;

    public Worker configureWorker(Worker worker, Key key)
            throws Exception {

        if (workerRepository.findOneByIp(worker.getIp()) != null)
            throw new Exception("There is already a worker registered at the ip: " + worker.getIp());

        if (keyRepository.findOneByName(worker.getKeyname()) == null)
            throw new NotFoundException("The key was not found!");

        if (worker.getUser().equals("") || worker.getIp().equals(""))
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
        return workerRepository.save(worker);
    }

    public List<Worker> createWorker(InputStream data) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = data.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
        InputStream is2 = new ByteArrayInputStream(baos.toByteArray());

        String key = Utils.extractKey(is1);
        if (key.equals(""))
            throw new NotFoundException("Key file not found: the private key must be in the root directory of the package and have the name: key");
        Key newKey = new Key();
        newKey.setName("key-" + (int) (Math.random() * 1000000));
        newKey.setKey(key);
        keyRepository.save(newKey);

        Adapter adapter = adapterRepository.findFirstByType("ansible");
        if (adapter == null) throw new NotFoundException("No ansible adapter was registered. Please register an ansible adapter to be able to create a worker.");

        OperationHandlerGrpc.OperationHandlerBlockingStub client = utils.getAdapterClient(adapter);
        ByteString p = ByteString.copyFrom(IOUtils.toByteArray(is2));
        FileMessage fileMessage =
                FileMessage.newBuilder().setFile(p).build();
        ResourceGroupProto rg = client.create(fileMessage);

        return resourceGroupToWorkers(rg, newKey);
    }

    private List<Worker> resourceGroupToWorkers(ResourceGroupProto resourceGroupProto, Key key) throws Exception {
        List<Worker> workers = new ArrayList<>();

        for(VDU vdu : resourceGroupProto.getVdusList()) {
            Worker worker = new Worker();
            worker.setKeyname(key.getName());
            worker.setIp(vdu.getIp());
            worker.setEpmIp(epmIp);
            // TODO: FIX
            worker.setUser("ubuntu");
            worker.setPassword("");
            worker.setPassphrase("");
            workers.add(configureWorker(worker, key));
        }

        return workers;
    }
}
