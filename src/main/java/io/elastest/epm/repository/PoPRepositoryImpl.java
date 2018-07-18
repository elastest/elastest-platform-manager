package io.elastest.epm.repository;

import io.elastest.epm.core.PlacementManager;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PoPRepositoryImpl implements PoPRepositoryCustom {

    @Autowired
    private PlacementManager placementManager;

    @Override
    @Transactional
    public PoP findPoPForType(String type) throws NotFoundException {
        return placementManager.choosePoPByType(type);
    }
}
