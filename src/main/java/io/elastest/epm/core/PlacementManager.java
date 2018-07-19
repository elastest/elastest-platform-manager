package io.elastest.epm.core;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.repository.PoPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlacementManager {

    @Autowired
    private PoPRepository poPRepository;

    public PoP choosePoPByType(String type) throws NotFoundException {
        /*
        In the future a better automatic placement will be integrated into this code
         */
        PoP poP = null;
        Iterable<PoP> pops = poPRepository.findAll();
        for (PoP pop : pops) {
            for (KeyValuePair kvp : pop.getInterfaceInfo()) {
                if (kvp.getKey().equals("type") && kvp.getValue().equals(type)) {
                    poP = pop;
                    break;
                }
            }
        }

        if (poP == null || poP.getInterfaceEndpoint() == null)
            throw new NotFoundException("No " + type + " pop was registered!");
        return poP;
    }
}
