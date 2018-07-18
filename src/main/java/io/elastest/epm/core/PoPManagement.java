package io.elastest.epm.core;

import com.google.common.collect.Lists;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.Network;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.VirtualisedNetworkResourceManagementInterface;
import io.elastest.epm.pop.messages.network.QueryNetworkRequest;
import io.elastest.epm.pop.messages.network.QueryNetworkResponse;
import io.elastest.epm.pop.model.common.Filter;
import io.elastest.epm.pop.model.network.NetworkSubnet;
import io.elastest.epm.pop.model.network.VirtualNetwork;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoPManagement {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PoPRepository poPRepository;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private VirtualisedNetworkResourceManagementInterface adapter;

    @Autowired
    private DockerProperties dockerProperties;

    @PostConstruct
    private void init() {
        if (dockerProperties.getRegistration().isAuto()) {
            PoP pop = new PoP();
            pop.setName(dockerProperties.getRegistration().getName());
            pop.setInterfaceEndpoint(dockerProperties.getRegistration().getAddress());
            KeyValuePair keyValuePair = new KeyValuePair();
            keyValuePair.setKey("type");
            keyValuePair.setValue("docker");
            List<KeyValuePair> keyValuePairs = new ArrayList<>();
            keyValuePairs.add(keyValuePair);
            pop.setInterfaceInfo(keyValuePairs);
            try {
                this.registerPoP(pop);
            } catch (Exception e) {
                log.error(
                        "Unable to register default Docker environment automatically -> " + e.getMessage());
            }
        }
    }

    public PoP registerPoP(PoP poP) throws AdapterException {
        log.info("Registering new PoP: " + poP);
        PoP existingPoP = poPRepository.findOneByName(poP.getName());
        if (existingPoP != null) {
            log.warn("PoP is already registered -> " + existingPoP);
            poP = existingPoP;
            return poP;
        }
        //    networkRepository.save(retrieveNetworksFromPoP(poP));
        poPRepository.save(poP);
        log.info("Registered PoP: " + poP);
        return poP;
    }

    public void unregisterPoP(String poPInfoId) throws NotFoundException {
        log.info("Unregistering PoP: " + poPInfoId);
        PoP poP = poPRepository.findOne(poPInfoId);
        if (poP != null) {
            Iterable<Network> networks = networkRepository.findAll();
            for (Network network : networks) {
                if (network.getPoPName().equals(poP.getName())) networkRepository.delete(network);
            }
            poPRepository.delete(poP);
        } else {
            throw new NotFoundException("Not found PoP " + poPInfoId);
        }
        log.info("Unregistered PoP: " + poPInfoId);
    }

    public PoP updatePoP(String poPInfoId, PoP poP) {
        log.info("Updating PoP: " + poPInfoId);
        poP.setId(poPInfoId);
        poPRepository.save(poP);
        log.info("Updated PoP: " + poP);
        return poP;
    }

    public List<PoP> getAllPoPs() {
        log.info("Listing all PoPs...");
        List<PoP> allPoPs = Lists.newArrayList(poPRepository.findAll());
        log.info("Listed all PoPs: " + allPoPs);
        return allPoPs;
    }

    public PoP getPoPById(String id) {
        log.info("Getting PoP: " + id);
        PoP poP = poPRepository.findOne(id);
        log.info("Got PoP: " + poP);
        return poP;
    }

    public List<Network> retrieveNetworksFromPoP(PoP poP) throws AdapterException {
        log.info("Retrieving networks from PoP " + poP);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                virtualNetwork -> ((VirtualNetwork) virtualNetwork).getNetworkResourceName().matches(".*"));
        Filter filterNetwork = new Filter();
        filterNetwork.setPredicates(predicates);
        QueryNetworkRequest queryNetworkRequest = new QueryNetworkRequest();
        queryNetworkRequest.setQueryNetworkFilter(filterNetwork);
        QueryNetworkResponse queryNetworkResponse =
                adapter.queryVirtualisedNetworkResource(queryNetworkRequest, poP);
        List<Network> networks = new ArrayList<>();
        for (VirtualNetwork virtualNetwork : queryNetworkResponse.getQueryResult()) {
            log.debug("Translate VirtualNetwork: " + virtualNetwork);
            Network network = new Network();
            network.setName(virtualNetwork.getNetworkResourceName());
            network.setNetworkId(virtualNetwork.getNetworkResourceId());
            network.setPoPName(poP.getName());
            for (NetworkSubnet subnet : virtualNetwork.getSubnet()) {
                for (KeyValuePair metadata : subnet.getMetadata()) {
                    if (metadata.getKey().equals("CIDR")) {
                        network.setCidr(metadata.getValue());
                        break;
                    }
                }
            }
            networks.add(network);
        }
        log.info("Retrieved networks from PoP " + poP + " -> " + networks);
        return networks;
    }

    public PoP getPoPByName(String poPName) {
        log.info("Getting PoP: " + poPName);
        PoP poP = poPRepository.findOneByName(poPName);
        log.info("Got PoP: " + poP);
        return poP;
    }
}
