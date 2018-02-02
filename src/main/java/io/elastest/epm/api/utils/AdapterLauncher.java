package io.elastest.epm.api.utils;

import com.jcraft.jsch.*;
import java.io.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdapterLauncher {

  private static final Logger log = LoggerFactory.getLogger(AdapterLauncher.class);

  public static void startAdapters(
      InputStream privateKey, String host, String user, String passPhrase, String password, String epmIp)
      throws JSchException, IOException, SftpException {

    final File tempFile = File.createTempFile("private", "");
    tempFile.deleteOnExit();
    try (FileOutputStream out = new FileOutputStream(tempFile)) {
      IOUtils.copy(privateKey, out);
    }

    JSch jsch = new JSch();

    jsch.addIdentity(tempFile.getAbsolutePath(), passPhrase.getBytes());

    Session session = jsch.getSession(user, host, 22);

    //session.setPassword(password);

    java.util.Properties config = new java.util.Properties();
    config.put("StrictHostKeyChecking", "no");
    session.setConfig(config);

    session.connect();

    InputStream installationIs = new FileInputStream("adapters_installation.sh");
    sendFile(session, installationIs, "adapters_installation.sh");

    InputStream compose = new FileInputStream("docker-compose-adapters.yml");
    sendFile(session, compose, "docker-compose.yml");

    executeCommand(session, "sudo su root ./adapters_installation.sh " + epmIp + " " + host);

    session.disconnect();

    tempFile.delete();
  }

  private static void executeCommand(Session session, String command)
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

  private static void sendFile(Session session, InputStream is, String fileName)
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
