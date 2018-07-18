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

import io.elastest.epm.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdapterLauncher {

    @Autowired
    private ElastestProperties elastestProperties;
    @Autowired
    private PoPRepository poPRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private KeyRepository keyRepository;

    private static final Logger log = LoggerFactory.getLogger(AdapterLauncher.class);

    public String startAdapter(String workerId, String type) throws NotFoundException, JSchException, SftpException, IOException {
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
            throws JSchException, IOException, SftpException {

        Session session = createSession(worker, key);

        InputStream compose = new FileInputStream("configuration_scripts/docker-compose-adapters.yml");
        sendFile(session, compose, "docker-compose.yml");

        InputStream installationIs;

        switch (type) {
            case "docker-compose":
                installationIs = new FileInputStream("configuration_scripts/install_docker_compose.sh");
                sendFile(session, installationIs, "docker_compose.sh");
                executeCommand(
                        session,
                        "sudo su root ./docker_compose.sh " + worker.getEpmIp() + " " + worker.getIp());
                PoP composePop = new PoP();
                composePop.setInterfaceEndpoint(worker.getIp());
                composePop.setName("compose-" + worker.getIp());
                composePop.addInterfaceInfoItem(new KeyValuePair("type", "docker-compose"));
                poPRepository.save(composePop);
                break;
            case "docker":
                installationIs = new FileInputStream("configuration_scripts/install_docker.sh");
                sendFile(session, installationIs, "docker.sh");
                executeCommand(
                        session, "sudo su root ./docker.sh " + worker.getEpmIp() + " " + worker.getIp());
                break;
        }
        session.disconnect();
    }

    public Worker configureWorker(Worker worker, Key key)
            throws Exception {

        if (workerRepository.findOneByIp(worker.getIp()) != null)
            throw new Exception("There is already a worker registered at the ip: " + worker.getIp());

        if (keyRepository.findOneByName(worker.getKeyname()) == null)
            throw new NotFoundException("The key was not found!");

        if (worker.getUser().equals("") || worker.getEpmIp().equals("") || worker.getIp().equals(null))
            throw new NotFoundException(
                    "To register a worker the PoP must provide the InferaceEndpoint"
                            + " and InterfaceInfo containing user, IP of the EPM and passphrase information");

        Session session = createSession(worker, key);
        InputStream compose = new FileInputStream("configuration_scripts/docker-compose-adapters.yml");
        sendFile(session, compose, "docker-compose.yml");

        InputStream configureIs = new FileInputStream("configuration_scripts/preconfigure.sh");
        sendFile(session, configureIs, "preconfigure.sh");

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
            executeCommand(session, "sudo su root ./preconfigure.sh " + empConfig);
        }
        session.disconnect();
        return workerRepository.save(worker);
    }

    private Session createSession(Worker worker, Key key) throws JSchException, IOException {
        final File tempFile = File.createTempFile("private", "");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            OutputStreamWriter osw = new OutputStreamWriter(out);
            osw.write(key.getKey());
            osw.flush();
        }

        JSch jsch = new JSch();

        jsch.addIdentity(tempFile.getAbsolutePath(), worker.getPassphrase().getBytes());

        Session session = jsch.getSession(worker.getUser(), worker.getIp(), 22);

        //session.setPassword(password);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect();
        return session;
    }

    private void executeCommand(Session session, String command)
            throws IOException, JSchException, SftpException {
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();

        channelExec.setCommand(command);
        channelExec.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        int index = 0;

        while ((line = reader.readLine()) != null) {
            log.debug(++index + " : " + line);
        }

        int exitStatus = channelExec.getExitStatus();
        channelExec.disconnect();

        if (exitStatus < 0) {
            log.error("Done, but exit status not set!");
        } else if (exitStatus > 0) {
            log.error("Done, but with error!");
        } else {
            log.debug("Done!");
        }
    }

    private void sendFile(Session session, InputStream is, String fileName)
            throws JSchException, SftpException {

        Channel uploadChannel = session.openChannel("sftp");
        uploadChannel.connect();

        ((ChannelSftp) uploadChannel).put(is, fileName);

        int exitStatus = uploadChannel.getExitStatus();
        uploadChannel.disconnect();

        if (exitStatus == 0) {
            log.debug("Uploaded successfully!");
        } else {
            log.debug("Failed to upload file: " + fileName);
        }
    }
}
