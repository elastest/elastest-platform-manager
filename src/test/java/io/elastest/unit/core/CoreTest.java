package io.elastest.unit.core;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import io.elastest.epm.core.NetworkManagement;
import io.elastest.epm.core.PoPManagement;
import io.elastest.epm.core.ResourceGroupManagement;
import io.elastest.epm.core.VduManagement;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.GenericAdapter;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.messages.compute.AllocateComputeRequest;
import io.elastest.epm.pop.messages.compute.AllocateComputeResponse;
import io.elastest.epm.pop.messages.compute.UpdateComputeRequest;
import io.elastest.epm.pop.messages.compute.UpdateComputeResponse;
import io.elastest.epm.pop.messages.network.AllocateNetworkRequest;
import io.elastest.epm.pop.messages.network.AllocateNetworkResponse;
import io.elastest.epm.pop.messages.network.QueryNetworkRequest;
import io.elastest.epm.pop.messages.network.QueryNetworkResponse;
import io.elastest.epm.pop.model.common.OperationalState;
import io.elastest.epm.pop.model.compute.VirtualCompute;
import io.elastest.epm.pop.model.network.IpAddress;
import io.elastest.epm.pop.model.network.NetworkSubnet;
import io.elastest.epm.pop.model.network.VirtualNetwork;
import io.elastest.epm.pop.model.network.VirtualNetworkInterface;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.repository.*;
import io.elastest.unit.MockedConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {CoreTest.class, MockedConfig.class})
public class CoreTest {

  private final Logger log = LoggerFactory.getLogger(CoreTest.class);
  @Autowired private ConfigurableApplicationContext context;

  @Autowired Network network;
  @Autowired PoP pop;
  @Autowired VDU vdu;
  @Autowired ResourceGroup resourceGroup;
  @Autowired Adapter adapter;
  @Autowired Key key;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    log.info("Starting CoreTest");
  }

  @Test
  public void beanTest() {
    for (String s : context.getBeanDefinitionNames()) log.info(s);
  }

  @Bean
  PoPManagement poPManagement() {
    return new PoPManagement();
  }

  @Bean
  VduManagement vduManagement() {
    return new VduManagement();
  }

  @Bean
  NetworkManagement networkManagement() {
    return new NetworkManagement();
  }

  @Bean
  ResourceGroupManagement resourceGroupManagement() {
    return new ResourceGroupManagement();
  }

  @Bean
  List<Network> networks() {
    List<Network> networks = new ArrayList<>();
    networks.add(network);
    return networks;
  }

  @Bean
  List<ResourceGroup> resourceGroups() {
    List<ResourceGroup> resourceGroups = new ArrayList<>();
    resourceGroups.add(resourceGroup);
    return resourceGroups;
  }

  @Bean
  NetworkRepository networkRepository() {
    NetworkRepository networkRepository = mock(NetworkRepository.class);

    //    List<Network> networks = new ArrayList<>();
    //    networks.add(network);

    when(networkRepository.findOneByName(network.getName())).thenReturn(network);
    when(networkRepository.findOneByName(any())).thenReturn(network);
    when(networkRepository.save(network)).thenReturn(network);
    when(networkRepository.findByName(network.getName())).thenReturn(networks());
    when(networkRepository.findAll()).thenReturn(networks());
    when(networkRepository.findOne(network.getId())).thenReturn(network);
    doNothing().when(networkRepository).delete(network.getId());
    return networkRepository;
  }

  @Bean
  ResourceGroupRepository resourceGroupRepository() {
    ResourceGroupRepository resourceGroupRepository = mock(ResourceGroupRepository.class);

    //    List<Network> networks = new ArrayList<>();
    //    networks.add(network);

    when(resourceGroupRepository.findOneByName(resourceGroup.getName())).thenReturn(resourceGroup);
    when(resourceGroupRepository.save(resourceGroup)).thenReturn(resourceGroup);
    when(resourceGroupRepository.findByName(resourceGroup.getName())).thenReturn(resourceGroups());
    when(resourceGroupRepository.findAll()).thenReturn(resourceGroups());
    when(resourceGroupRepository.findOne(resourceGroup.getId())).thenReturn(resourceGroup);
    doNothing().when(resourceGroupRepository).delete(resourceGroup.getId());
    return resourceGroupRepository;
  }

  @Bean
  PoPRepository poPRepository() {
    PoPRepository poPRepository = mock(PoPRepository.class);

    List<PoP> pops = new ArrayList<>();
    pops.add(pop);
    when(poPRepository.findOneByName(pop.getName())).thenReturn(pop);
    when(poPRepository.save(pop)).thenReturn(pop);
    when(poPRepository.findAll()).thenReturn(pops);
    when(poPRepository.findOne(pop.getId())).thenReturn(pop);
    doNothing().when(poPRepository).delete(pop.getId());

    return poPRepository;
  }

    @Bean
    KeyRepository keyRepository() {
        KeyRepository keyRepository = mock(KeyRepository.class);

        List<Key> keys = new ArrayList<>();
        keys.add(key);
        when(keyRepository.findOneByName(key.getName())).thenReturn(key);
        when(keyRepository.save(key)).thenReturn(key);
        when(keyRepository.findAll()).thenReturn(keys);
        when(keyRepository.findOne(key.getId())).thenReturn(key);
        doNothing().when(keyRepository).delete(key.getId());

        return keyRepository;
    }

  @Bean
  AdapterRepository adapterRepository() {
    AdapterRepository adapterRepository = mock(AdapterRepository.class);
    List<Adapter> adapters = new ArrayList<>();
    adapters.add(adapter);

    when(adapterRepository.save(adapter)).thenReturn(adapter);
    when(adapterRepository.findAll()).thenReturn(adapters);
    when(adapterRepository.findOne(pop.getId())).thenReturn(adapter);
    doNothing().when(adapterRepository).delete(pop.getId());

    return adapterRepository;
  }

  @Bean
  AdapterRepositoryImpl adapterRepositoryImpl() {
    AdapterRepositoryImpl adapterRepositoryImpl = new AdapterRepositoryImpl();
    return adapterRepositoryImpl;
  }

  @Bean
  VduRepository vduRepository() {
    VduRepository vduRepository = mock(VduRepository.class);

    List<VDU> vdus = new ArrayList<>();
    vdus.add(vdu);

    when(vduRepository.save(vdu)).thenReturn(vdu);
    when(vduRepository.findAll()).thenReturn(vdus);
    when(vduRepository.findOne(vdu.getId())).thenReturn(vdu);
    doNothing().when(vduRepository).delete(vdu.getId());
    return vduRepository;
  }

  @Bean
  VirtualNetwork virtualNetwork() {
    VirtualNetwork virtualNetwork = new VirtualNetwork();
    virtualNetwork.setOperationalState(OperationalState.enabled);

    virtualNetwork.setNetworkResourceName(network.getName());
    virtualNetwork.setNetworkResourceId(network.getNetworkId());
    Set<KeyValuePair> metadata = new HashSet<>();
    virtualNetwork.setMetadata(metadata);

    List<NetworkSubnet> subnets = new ArrayList<>();
    NetworkSubnet subnet = new NetworkSubnet();
    List<KeyValuePair> metadataSubnet = new ArrayList<>();
    subnet.setOperationalState(OperationalState.enabled);
    IpAddress gateway = new IpAddress();
    gateway.setAddress("mocked_gateway_ip");
    subnet.setGatewayIp(gateway);
    metadataSubnet.add(new KeyValuePair("MOCKED_KEY", "MOCKED_KEY"));
    metadataSubnet.add(new KeyValuePair("CIDR", network.getCidr()));
    subnet.setMetadata(metadataSubnet);
    subnets.add(subnet);

    virtualNetwork.setSubnet(subnets);
    return virtualNetwork;
  }

  @Bean
  @Qualifier("mocked_genericAdapter")
  GenericAdapter genericAdapter() throws AdapterException {
    GenericAdapter genericAdapter = mock(GenericAdapter.class);

    return genericAdapter;
  }

  @Bean
  Utils utils() {
    return new Utils();
  }

  @Bean
  @Qualifier("mocked_dockerAdapter")
  DockerAdapter dockerAdapter() throws AdapterException {
    DockerAdapter dockerAdapter = mock(DockerAdapter.class);

    QueryNetworkResponse queryNetworkResponse = new QueryNetworkResponse();
    List<VirtualNetwork> virtualNetworks = new ArrayList<>();
    virtualNetworks.add(virtualNetwork());
    queryNetworkResponse.setQueryResult(virtualNetworks);
    when(dockerAdapter.queryVirtualisedNetworkResource(
        any(QueryNetworkRequest.class), any(PoP.class)))
        .thenReturn(queryNetworkResponse);

    AllocateNetworkResponse allocateNetworkResponse = new AllocateNetworkResponse();
    allocateNetworkResponse.setNetworkData(virtualNetwork());
    when(dockerAdapter.allocateVirtualisedNetworkResource(
        any(AllocateNetworkRequest.class), any(PoP.class)))
        .thenReturn(allocateNetworkResponse);

    VirtualCompute virtualCompute = new VirtualCompute();
    virtualCompute.setComputeId(vdu.getComputeId());
    virtualCompute.setComputeName(vdu.getName());
    virtualCompute.setVcImageId(vdu.getImageName());

    Set<VirtualNetworkInterface> virtualNetworkInterfaces = new HashSet<>();
    VirtualNetworkInterface virtualNetworkInterface = new VirtualNetworkInterface();
    virtualNetworkInterface.setNetworkId(network.getNetworkId());
    Set<IpAddress> ipAddresses = new HashSet<>();
    IpAddress ipAddress = new IpAddress();
    ipAddress.setAddress("mocked_ip_address");
    ipAddresses.add(ipAddress);
    virtualNetworkInterface.setIpAddress(ipAddresses);
    virtualNetworkInterface.setMetadata(new HashSet<>());
    virtualNetworkInterfaces.add(virtualNetworkInterface);
    virtualCompute.setVirtualNetworkInterface(virtualNetworkInterfaces);

    AllocateComputeResponse allocateComputeResponse = new AllocateComputeResponse();

    allocateComputeResponse.setComputeData(virtualCompute);
    when(dockerAdapter.allocateVirtualisedComputeResource(
        any(AllocateComputeRequest.class), any(PoP.class)))
        .thenReturn(allocateComputeResponse);

    UpdateComputeResponse updateComputeResponse = new UpdateComputeResponse();
    updateComputeResponse.setComputeData(virtualCompute);
    when(dockerAdapter.updateVirtualisedComputeResource(
        any(UpdateComputeRequest.class), any(PoP.class)))
        .thenReturn(updateComputeResponse);

    return dockerAdapter;
  }

  @Bean
  DockerProperties dockerProperties() {
    DockerProperties dockerProperties = new DockerProperties();
    DockerProperties.LogStash logStash = new DockerProperties.LogStash();
    logStash.setAddress("mocked_address");
    logStash.setEnabled(false);
    dockerProperties.setLogStash(logStash);
    DockerProperties.Registration registration = new DockerProperties.Registration();
    registration.setName("mocked_docker");
    registration.setAuto(true);
    registration.setAddress("mocked_docekr_address");
    dockerProperties.setRegistration(registration);
    return dockerProperties;
  }
}
