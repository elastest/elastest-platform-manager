package io.elastest.epm.repository;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;

public interface PoPRepositoryCustom {

  PoP findPoPForType(String type) throws NotFoundException;
}
