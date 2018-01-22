package io.elastest.epm.api.utils;

import com.jcraft.jsch.*;
import java.io.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdapterLauncher {

  private static final Logger log = LoggerFactory.getLogger(AdapterLauncher.class);

  public static void startAdapters(
      InputStream privateKey, String host, String user, String passPhrase)
      throws JSchException, IOException {

    final File tempFile = File.createTempFile("private", "");
    tempFile.deleteOnExit();
    try (FileOutputStream out = new FileOutputStream(tempFile)) {
      IOUtils.copy(privateKey, out);
    }

    JSch jsch = new JSch();

    jsch.addIdentity(tempFile.getAbsolutePath(), passPhrase.getBytes());

    Session session = jsch.getSession(user, host, 22);

    java.util.Properties config = new java.util.Properties();
    config.put("StrictHostKeyChecking", "no");
    session.setConfig(config);

    session.connect();

    ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

    InputStream in = channelExec.getInputStream();

    // Install Everything needed for the adapters to run
    channelExec.setCommand("wget -O - https://raw.githubusercontent.com/elastest/elastest-platform-manager/worker_registration/adapters_installation.sh | bash");
    channelExec.connect();

    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String line;
    int index = 0;

    while ((line = reader.readLine()) != null) {
      log.debug(++index + " : " + line);
    }

    // Start the adapters
    // Install Everything needed for the adapters to run
    channelExec.setCommand("wget https://raw.githubusercontent.com/elastest/elastest-platform-manager/worker_registration/docker-compose-adapters.yml" +
            " -O docker-compose.yml | docker-compose pull | docker-compose up -d --force-recreate ");
    channelExec.connect();

    reader = new BufferedReader(new InputStreamReader(in));
    index = 0;

    while ((line = reader.readLine()) != null) {
      log.debug(++index + " : " + line);
    }



    int exitStatus = channelExec.getExitStatus();
    channelExec.disconnect();
    session.disconnect();

    tempFile.delete();

    if (exitStatus < 0) {
      log.error("Done, but exit status not set!");
    } else if (exitStatus > 0) {
      log.error("Done, but with error!");
    } else {
      log.debug("Done!");
    }
  }
}
