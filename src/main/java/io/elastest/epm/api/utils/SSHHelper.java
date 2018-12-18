package io.elastest.epm.api.utils;

import com.jcraft.jsch.*;
import io.elastest.epm.model.Key;
import io.elastest.epm.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ConnectException;

@Component
public class SSHHelper {

    private static final Logger log = LoggerFactory.getLogger(AdapterLauncher.class);

    public void executeCommand(Session session, String command)
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

    public void sendFile(Session session, InputStream is, String fileName)
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

    public Session createSession(Worker worker, Key key) throws JSchException, IOException, InterruptedException {
        final File tempFile = File.createTempFile("private", "");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            OutputStreamWriter osw = new OutputStreamWriter(out);
            osw.write(key.getKey());
            osw.flush();
        }

        JSch jsch = new JSch();

        jsch.addIdentity(tempFile.getAbsolutePath(), worker.getAuthCredentials().getPassphrase().getBytes());

        Session session;
        if (worker.getAuthCredentials().getPort() == null)
            session = jsch.getSession(worker.getAuthCredentials().getUser(), worker.getIp(), 22);
        else session = jsch.getSession(worker.getAuthCredentials().getUser(), worker.getIp(), worker.getAuthCredentials().getPort());

        //session.setPassword(password);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        boolean connected = false;
        int timeout = 12;
        while (!connected && timeout > 0) {
            try {
                session.connect();
                connected = true;
            }
            catch (Exception e) {
                log.debug("Couldnt connect yet: trying again in 5 seconds...");
                timeout--;
                Thread.sleep(5 * 1000);
            }
        }
        if (!connected) throw new ConnectException("Connection timed out. Could not reach worker.");
        return session;
    }
}
