package io.elastest.epm.api.utils;

import com.jcraft.jsch.*;
import io.elastest.epm.model.Key;
import io.elastest.epm.model.Worker;
import java.io.*;

import io.elastest.epm.properties.ElastestProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AdapterLauncher {

  @Autowired private ElastestProperties elastestProperties;

  private static final Logger log = LoggerFactory.getLogger(AdapterLauncher.class);

  public void startAdapter(Worker worker, Key key, String type)
      throws JSchException, IOException, SftpException {

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

    InputStream compose = new FileInputStream("docker-compose-adapters.yml");
    sendFile(session, compose, "docker-compose.yml");

    String empConfig = "";
    if(elastestProperties.getEmp().isEnabled()){
      empConfig = " " + elastestProperties.getEmp().getEndPoint()+":"+elastestProperties.getEmp().getPort();
    }

    InputStream installationIs;

    switch (type){
      case "docker-compose":
        installationIs = new FileInputStream("configuration_scripts/install_docker_compose.sh");
        sendFile(session, installationIs, "docker_compose.sh");
        executeCommand(
                session,
                "sudo su root ./docker_compose.sh " + worker.getEpmIp() + " " + worker.getIp() + empConfig);
        break;
      case "docker":
        installationIs = new FileInputStream("configuration_scripts/install_docker.sh");
        sendFile(session, installationIs, "docker.sh");
        executeCommand(
                session,
                "sudo su root ./docker.sh " + worker.getEpmIp() + " " + worker.getIp() + empConfig);
        break;
    }
    session.disconnect();

    tempFile.delete();
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
