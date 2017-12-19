package io.elastest.epm.repository;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PoPRepositoryImpl implements PoPRepositoryCustom {

  @Autowired private PoPRepository poPRepository;

  @Override
  @Transactional
  public PoP findPoPForType(String type) throws NotFoundException {
    PoP composePoP = null;
    Iterable<PoP> pops = poPRepository.findAll();
    for (PoP pop : pops) {
      for (KeyValuePair kvp : pop.getInterfaceInfo()) {
        if (kvp.getKey().equals("type") && kvp.getValue().equals(type)) {
          composePoP = pop;
          break;
        }
      }
    }

    if (composePoP == null || composePoP.getInterfaceEndpoint() == null)
      throw new NotFoundException("No " + type + " pop was registered!");
    return composePoP;
  }
}
