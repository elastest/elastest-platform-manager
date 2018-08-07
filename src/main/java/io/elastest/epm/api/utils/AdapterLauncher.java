package io.elastest.epm.api.utils;

import com.jcraft.jsch.*;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Key;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.Worker;
import io.elastest.epm.properties.ElastestProperties;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.PoPRepository;

import java.io.*;
import java.util.Objects;

import io.elastest.epm.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdapterLauncher {


    @Autowired
    private PoPRepository poPRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private SSHHelper sshHelper;

    @Value("${et.public.host}")
    private String epmIp;

    private static final Logger log = LoggerFactory.getLogger(AdapterLauncher.class);

    public String startAdapter(String workerId, String type) throws NotFoundException, JSchException, SftpException, IOException, InterruptedException {
        Worker worker = workerRepository.findOne(workerId);
        if (worker == null) throw new NotFoundException("No worker with id: " + workerId + " registered.");

        if (keyRepository.findOneByName(worker.getKeyname()) == null)
            throw new NotFoundException("The key was not found!");

        switch (type) {
            default:
                return "Available adapters setups: docker-compose, docker, ansible";
            case "docker":
                startAdapter(
                        worker, keyRepository.findOneByName(worker.getKeyname()), type);
                return "Adapter started.";
            case "ansible":
                return "Not implemented yet.";
            case "docker-compose":
                startAdapter(
                        worker, keyRepository.findOneByName(worker.getKeyname()), type);
                return "Adapter started.";
        }
    }

    public void startAdapter(Worker worker, Key key, String type)
            throws JSchException, IOException, SftpException, InterruptedException {

        Session session = sshHelper.createSession(worker, key);

        InputStream compose = new FileInputStream("configuration_scripts/docker-compose-adapters.yml");
        sshHelper.sendFile(session, compose, "docker-compose.yml");

        InputStream installationIs;

        switch (type) {
            case "docker-compose":
                installationIs = new FileInputStream("configuration_scripts/install_docker_compose.sh");
                sshHelper.sendFile(session, installationIs, "docker_compose.sh");

                if (!epmIp.equals("")) {
                    sshHelper.executeCommand(
                            session,
                            "sudo su root ./docker_compose.sh " + epmIp + " " + worker.getIp());
                }
                else {
                    sshHelper.executeCommand(
                            session,
                            "sudo su root ./docker_compose.sh " + worker.getEpmIp() + " " + worker.getIp());
                }
                PoP composePop = new PoP();
                composePop.setInterfaceEndpoint(worker.getIp());
                composePop.setName("compose-" + worker.getIp());
                composePop.addInterfaceInfoItem(new KeyValuePair("type", "docker-compose"));
                poPRepository.save(composePop);
                break;
            case "docker":
                installationIs = new FileInputStream("configuration_scripts/install_docker.sh");
                sshHelper.sendFile(session, installationIs, "docker.sh");
                if (!epmIp.equals("")) {
                    sshHelper.executeCommand(session, "sudo su root ./docker.sh " + epmIp + " " + worker.getIp());

                }
                else {
                    sshHelper.executeCommand(session, "sudo su root ./docker.sh " + worker.getEpmIp() + " " + worker.getIp());

                }
                break;
        }
        session.disconnect();
    }
}
