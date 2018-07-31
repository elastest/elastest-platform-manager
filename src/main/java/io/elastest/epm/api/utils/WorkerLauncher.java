package io.elastest.epm.api.utils;

import com.jcraft.jsch.Session;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Key;
import io.elastest.epm.model.Worker;
import io.elastest.epm.properties.ElastestProperties;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

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
}
