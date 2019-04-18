package io.elastest.epm.api.utils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.PullImageResultCallback;
import com.google.protobuf.ByteString;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.model.Key;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.Worker;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.InstallMessage;
import io.elastest.epm.pop.generated.MetadataEntry;
import io.elastest.epm.pop.generated.OperationHandlerGrpc;
import io.elastest.epm.pop.generated.StringResponse;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.properties.KeystoneProperties;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    Utils utils;
    @Autowired
    private KeystoneProperties keystoneProperties;
    @Autowired
    private DockerProperties dockerProperties;

    @Value("${et.public.host}")
    private String epmIp;

    private static final Logger log = LoggerFactory.getLogger(AdapterLauncher.class);

    public String startAdapter(String workerId, String type) throws NotFoundException, JSchException, SftpException, IOException, InterruptedException {
        Worker worker = workerRepository.findOne(workerId);
        if (worker == null) throw new NotFoundException("No worker with id: " + workerId + " registered.");

        return startAdapter(worker, type);
    }

    public String startAdapter(Worker worker, String type) throws NotFoundException, JSchException, SftpException, IOException, InterruptedException {

        if (keyRepository.findOneByName(worker.getAuthCredentials().getKey()) == null)
            throw new NotFoundException("The key was not found!");

        switch (type) {
            default:
                return "Available adapters setups: docker-compose, docker, ansible";
            case "docker":
                startAdapter(
                        worker, keyRepository.findOneByName(worker.getAuthCredentials().getKey()), type);
                return "Adapter started.";
            case "ansible":
                return "Not implemented yet.";
            case "docker-compose":
                startAdapter(
                        worker, keyRepository.findOneByName(worker.getAuthCredentials().getKey()), type);
                return "Adapter started.";
        }
    }

    private void startAdapter(Worker worker, Key key, String type)
            throws JSchException, IOException, SftpException, InterruptedException, NotFoundException {

        Session session = sshHelper.createSession(worker, key);

        InputStream compose = new FileInputStream("configuration_scripts/docker-compose-adapters.yml");
        sshHelper.sendFile(session, compose, "docker-compose.yml");

        InputStream installationIs;

        switch (type) {
            case "docker-compose":
                Adapter adapter = utils.getAdapter("ansible");

                OperationHandlerGrpc.OperationHandlerBlockingStub client =
                        utils.getAdapterClient(adapter, "ansible");
                List<MetadataEntry> metadataEntries = new ArrayList<>();
                if(keystoneProperties.isEnabled()) {
                    metadataEntries.add(MetadataEntry.newBuilder().setKey("KEYSTONE_ENDPOINT").setValue(keystoneProperties.getEndpoint()).build());
                    metadataEntries.add(MetadataEntry.newBuilder().setKey("KEYSTONE_PORT").setValue(keystoneProperties.getPort()).build());
                    metadataEntries.add(MetadataEntry.newBuilder().setKey("KEYSTONE_VERSION").setValue(keystoneProperties.getVersion()).build());
                    metadataEntries.add(MetadataEntry.newBuilder().setKey("KEYSTONE_USERNAME").setValue(keystoneProperties.getUsername()).build());
                    metadataEntries.add(MetadataEntry.newBuilder().setKey("KEYSTONE_PASSWORD").setValue(keystoneProperties.getPassword()).build());
                    metadataEntries.add(MetadataEntry.newBuilder().setKey("KEYSTONE_DOMAIN").setValue(keystoneProperties.getDomain()).build());
                    metadataEntries.add(MetadataEntry.newBuilder().setKey("KEYSTONE_ENABLED").setValue(String.valueOf(keystoneProperties.isEnabled())).build());
                }

                InstallMessage addNodeMessage =
                        InstallMessage.newBuilder()
                                .setType("docker-compose")
                                .setMasterIp(worker.getIp())
                                .addNodesIp(epmIp)
                                .setKey(io.elastest.epm.pop.generated.Key.newBuilder().setKey(ByteString.copyFromUtf8(key.getKey())).build())
                                .addAllMetadata(metadataEntries)
                                .build();
                StringResponse s = client.createCluster(addNodeMessage);
                int status = Integer.parseInt(s.getResponse());
                if (status == 0) {
                    PoP composePop = new PoP();
                    composePop.setInterfaceEndpoint(worker.getIp());
                    composePop.setName("compose-" + worker.getIp());
                    composePop.addInterfaceInfoItem(new KeyValuePair("type", "docker-compose"));
                    poPRepository.save(composePop);
                }
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

    public Adapter launchRandomDockerComposeAdapter() throws AdapterException {
        Iterable<Worker> workers = workerRepository.findAll();
        if( workers.iterator().hasNext() ) {
            Worker worker = workers.iterator().next();
            try {
                startAdapter(worker, keyRepository.findOneByName(worker.getAuthCredentials().getKey()), "docker-compose");
                log.info("Waiting for adapter to register...");
                Adapter adapter = null;
                for(int i=0; i < 10; i++) {
                    if ((adapter = utils.getAdapter("docker-compose")) != null) {
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(6);
                    } catch (InterruptedException e) {
                        log.error("Waiting for adapter interrupted: " + e.getLocalizedMessage());
                    }
                }
                return adapter;
            } catch (JSchException | IOException | InterruptedException | SftpException | NotFoundException e) {
                throw new AdapterException(e.getLocalizedMessage());
            }
        }
        else {
            throw new AdapterException("No available worker found!");
        }
    }

    public Adapter launchLocalAnsibleAdapter() throws AdapterException {
        log.info("docker address: " + dockerProperties.getRegistration().getAddress());
        DockerClientConfig dockerClientConfig =
                DefaultDockerClientConfig.createDefaultConfigBuilder()
                        .withDockerHost(dockerProperties.getRegistration().getAddress())
                        .build();
        DockerClient dockerClient = DockerClientBuilder.getInstance(dockerClientConfig).build();
        //dockerClient.listContainersCmd().withShowAll(true).exec();
        String containerName = "elastest-epm-adapter-ansible";
        log.info("Allocating container " + containerName);

        String imageId = "elastest/epm-adapter-ansible";
        if (!imageExists()) {
            try {
                log.info("Pulling image " + imageId);
                dockerClient.pullImageCmd(imageId)
                        .exec(new PullImageResultCallback()).awaitSuccess();
            } catch (Exception e) {
                log.error("Not Found image " + imageId + " -> " + e.getMessage());
                throw new AdapterException("Not found image " + imageId + " -> " + e.getMessage());
            }
        }

        CreateContainerResponse createdContainer = null;
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        String containerId = null;
        for( Container container : containers ) {
            log.info("container name: " + Arrays.toString(container.getNames()));
            if (container.getNames().length > 0 && (container.getNames()[0].equals("elastest-epm-adapter-ansible") || container.getNames()[0].equals("/elastest-epm-adapter-ansible"))) {
                containerId = container.getId();
            }
        }
        if (containerId != null) {
            log.info("Stopping and removing container with id: " + containerId);
            dockerClient.removeContainerCmd(containerId).withForce(true).exec();
        }
        try {
            log.info("Creating Container...");
            List<String> env = new ArrayList<>();
            env.add("EPM_IP=" + epmIp);
            env.add("ADAPTER_IP=" + epmIp);
            createdContainer =
                    dockerClient
                            .createContainerCmd(imageId)
                            .withName(containerName)
                            .withPortBindings(PortBinding.parse("50052:50052"))
                            .withEnv()
                            .withEnv(env)
                            .exec();
            log.debug("Created Container: " + createdContainer);
            log.debug("Starting Container: " + createdContainer.getId());
            dockerClient.startContainerCmd(createdContainer.getId()).exec();
            log.debug("Started Container: " + createdContainer.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AdapterException(e.getMessage(), createdContainer.getId());
        }
        Container deployedContainer = null;
        for (Container container : dockerClient.listContainersCmd().withShowAll(true).exec()) {
            if (container.getId().equals(createdContainer.getId())) {
                deployedContainer = container;
            }
        }
        log.info("Allocated container " + deployedContainer);
        log.info("Waiting for adapter to register...");
        Adapter adapter = null;
        for(int i=0; i < 10; i++) {
            if ((adapter = utils.getAdapter("ansible")) != null) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                log.error("Waiting for adapter interrupted: " + e.getLocalizedMessage());
            }
        }
        return adapter;
    }

    private boolean imageExists() throws AdapterException {
        DockerClientConfig dockerClientConfig =
                DefaultDockerClientConfig.createDefaultConfigBuilder()
                        .withDockerHost(dockerProperties.getRegistration().getAddress())
                        .build();
        DockerClient client = DockerClientBuilder.getInstance(dockerClientConfig).build();
        String imageId = "elastest/epm-adapter-ansible";
        log.debug("Checking if image " + imageId + " exists");
        List<Image> availableImages = client.listImagesCmd().exec();
        for (Image image : availableImages) {
            if (image.getId().equals(imageId)) {
                log.debug("Found image " + imageId);
                return true;
            }
            if (image.getRepoTags() != null) {
                for (String tag : image.getRepoTags()) {
                    if (tag.equals(imageId) || tag.equals(imageId + ":latest")) {
                        log.debug("Found image " + imageId);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
