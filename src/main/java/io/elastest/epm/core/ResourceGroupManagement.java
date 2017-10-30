package io.elastest.epm.core;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.repository.ResourceGroupRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceGroupManagement {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private PoPManagement poPManagement;

  @Autowired private NetworkManagement networkManagement;

  @Autowired private VduManagement vduManagement;

  @Autowired private ResourceGroupRepository resourceGroupRepository;

  public ResourceGroup deployResourceGroup(ResourceGroup resourceGroup)
      throws AdapterException, BadRequestException, NotFoundException, AllocationException {
    log.info("Deploying Resource Group: " + resourceGroup);
    int groupId = (int) (Math.random() * 1000000);
    try {
      for (Network net : resourceGroup.getNetworks()) {
        net.setName(resourceGroup.getName() + "_" + net.getName() + "_" + groupId);
        net.setPoPName(resourceGroup.getName() + "_" + net.getPoPName() + "_" + groupId);
        networkManagement.createNetwork(net);
      }
      for (VDU vdu : resourceGroup.getVdus()) {
        vdu.setName(resourceGroup.getName() + "_" + vdu.getName() + "_" + groupId);
        vdu.setNetName(resourceGroup.getName() + "_" + vdu.getNetName() + "_" + groupId);
        vdu.setPoPName(resourceGroup.getName() + "_" + vdu.getPoPName() + "_" + groupId);
        vduManagement.deployVdu(vdu);
      }
    } catch (Exception exc) {
      log.error(exc.getMessage(), exc);
      log.error("Rollback allocation of resource group...");
      for (VDU vdu : resourceGroup.getVdus()) {
        if (vdu.getId() != null) {
          try {
            vduManagement.terminateVdu(vdu.getId());
          } catch (TerminationException e) {
            log.warn(e.getMessage());
          }
        }
      }
      for (Network net : resourceGroup.getNetworks()) {
        if (net.getId() != null) {
          networkManagement.deleteNetwork(net.getId());
        }
      }
      throw exc;
    }
    resourceGroupRepository.save(resourceGroup);
    log.info("Deployed Resource Group: " + "_" + resourceGroup);
    return resourceGroup;
  }

  public void terminateResourceGroup(String resourceGroupId)
      throws AdapterException, NotFoundException, TerminationException {
    log.info("Terminating Resource Group: " + resourceGroupId);
    ResourceGroup resourceGroup = resourceGroupRepository.findOne(resourceGroupId);
    for (VDU vdu : resourceGroup.getVdus()) {
      vduManagement.terminateVdu(vdu.getId());
    }
    for (Network net : resourceGroup.getNetworks()) {
      networkManagement.deleteNetwork(net.getId());
    }
    resourceGroupRepository.delete(resourceGroup.getId());
    log.info("Terminated Resource Group: " + "_" + resourceGroupId);
  }

  public ResourceGroup getResourceGroupById(String resourceGroupId) throws NotFoundException {
    log.info("Retrieving Resource Group: " + resourceGroupId);
    ResourceGroup resourceGroup = resourceGroupRepository.findOne(resourceGroupId);
    if (resourceGroup == null) {
      throw new NotFoundException("Not found Resource Group: " + resourceGroupId);
    }
    log.info("Found Resource Group: " + resourceGroup);
    return resourceGroup;
  }

  public List<ResourceGroup> getAllResourceGroups() {
    log.info("Listing all Resource Groups...");
    Iterable<ResourceGroup> resourceGroups = resourceGroupRepository.findAll();
    List<ResourceGroup> resourceGroupList = new ArrayList<>();
    resourceGroups.iterator().forEachRemaining(resourceGroupList::add);
    log.info("Listed all Resource Groups: " + resourceGroups);
    return resourceGroupList;
  }

  public ResourceGroup updateResourceGroup(String resourceGroupId, ResourceGroup resourceGroup) {
    throw new NotImplementedException();
  }
}
