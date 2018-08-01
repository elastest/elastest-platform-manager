package io.elastest.epm.pop.adapter;

import com.google.gson.Gson;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.generated.MetadataEntry;
import io.elastest.epm.pop.generated.OperationHandlerGrpc;
import io.elastest.epm.pop.generated.ResourceGroupProto;
import io.elastest.epm.repository.AdapterRepository;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.VduRepository;

import java.io.*;
import java.util.Map;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.kamranzafar.jtar.TarEntry;
import org.kamranzafar.jtar.TarOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

@Component
public class Utils {

    @Autowired
    private AdapterRepository adapterRepository;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private VduRepository vduRepository;

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public static File compressFileToTar(File file) throws IOException {
        File temp = File.createTempFile("archive", ".tar");
        temp.deleteOnExit();
        FileOutputStream fileOutputStream = new FileOutputStream(temp);
        TarOutputStream out = new TarOutputStream(new BufferedOutputStream(fileOutputStream));

        // Files to tar
        File[] filesToTar = new File[1];
        filesToTar[0] = file;

        for (File f : filesToTar) {
            out.putNextEntry(new TarEntry(f, f.getName()));
            BufferedInputStream origin = new BufferedInputStream(new FileInputStream(f));

            int count;
            byte data[] = new byte[2048];
            while ((count = origin.read(data)) != -1) {
                out.write(data, 0, count);
            }

            out.flush();
            origin.close();
        }

        out.close();
        fileOutputStream.close();
        return temp;
    }

    public ResourceGroup parseRGProto(ResourceGroupProto rg, PoP pop) {
        ResourceGroup resourceGroup = new ResourceGroup();
        resourceGroup.setName(rg.getName());

        for (io.elastest.epm.pop.generated.Network networkCompose : rg.getNetworksList()) {
            Network network = new Network();
            network.setName(networkCompose.getName());
            network.setCidr(networkCompose.getCidr());
            network.setPoPName(pop.getName());
            network.setNetworkId(networkCompose.getNetworkId());
            networkRepository.save(network);
            resourceGroup.addNetworksItem(network);
        }

        for (io.elastest.epm.pop.generated.VDU vduCompose : rg.getVdusList()) {

            VDU vdu = new VDU();
            vdu.setName(vduCompose.getName());
            vdu.setImageName(vduCompose.getImageName());
            vdu.setComputeId(vduCompose.getComputeId());
            vdu.setNetName(vduCompose.getNetName());
            vdu.setPoPName(pop.getName());
            vdu.setIp(vduCompose.getIp());
            for (MetadataEntry metadataEntryCompose : vduCompose.getMetadataList()) {
                KeyValuePair kvp =
                        new KeyValuePair(metadataEntryCompose.getKey(), metadataEntryCompose.getValue());
                vdu.addMetadataItem(kvp);
            }
            vduRepository.save(vdu);
            resourceGroup.addVdusItem(vdu);
        }

        return resourceGroup;
    }

    public static Map<String, Object> extractMetadata(InputStream p)
            throws ArchiveException, IOException {
        ArchiveInputStream t = new ArchiveStreamFactory().createArchiveInputStream("tar", p);

        Map<String, Object> values = null;
        TarArchiveEntry entry = (TarArchiveEntry) t.getNextEntry();
        while (entry != null) {
            if (entry.getName().toLowerCase().equals("metadata.yaml")) {
                byte[] content = new byte[(int) entry.getSize()];
                t.read(content, 0, content.length);
                Yaml yaml = new Yaml();

                values = yaml.load(new String(content));
                break;
            }
            entry = (TarArchiveEntry) t.getNextEntry();
        }
        t.close();

        return values;
    }

    public static ResourceGroup extractResourceGroup(InputStream p)
            throws ArchiveException, IOException {
        ArchiveInputStream t = new ArchiveStreamFactory().createArchiveInputStream("tar", p);

        Map<String, Object> values = null;
        TarArchiveEntry entry = (TarArchiveEntry) t.getNextEntry();
        while (entry != null) {
            if (entry.getName().toLowerCase().contains(".json")) {
                Gson gson = new Gson();
                byte[] content = new byte[(int) entry.getSize()];
                t.read(content, 0, content.length);
                ResourceGroup resourceGroup = gson.fromJson(new String(content), ResourceGroup.class);
                return resourceGroup;
            }
        }

        return null;
    }

    public Adapter getAdapter(String type) {
        return adapterRepository.findFirstByType(type);
    }

    public Adapter getAdapterSpecific(PoP poP) throws NotFoundException {
        String type = extractTypeFromPoP(poP);
        if (type.equals("docker-compose"))
            return adapterRepository.findAdapterForTypeAndIp(extractTypeFromPoP(poP), poP.getInterfaceEndpoint());
        else return adapterRepository.findFirstByType(type);
    }

    public OperationHandlerGrpc.OperationHandlerBlockingStub getAdapterClient(Adapter adapter) throws NotFoundException {
        if (adapter == null) {
            throw new NotFoundException("No * adapter registered! Please start the docker adapter and provide the " +
                    "needed information to be able to deploy docker packages.");
        }
        String ip = adapter.getEndpoint().split(":")[0];
        int port = Integer.parseInt(adapter.getEndpoint().split(":")[1]);
        ManagedChannelBuilder<?> channelBuilder =
                ManagedChannelBuilder.forAddress(ip, port).usePlaintext(true);
        ManagedChannel channel = channelBuilder.build();
        return OperationHandlerGrpc.newBlockingStub(channel);
    }

    public String extractTypeFromPoP(PoP poP) {
        String type = null;
        for (KeyValuePair kvp : poP.getInterfaceInfo()) {
            if (kvp.getKey().equals("type")) {
                type = kvp.getValue();
                break;
            }
        }
        return type;
    }
}
