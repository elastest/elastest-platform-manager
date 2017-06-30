package io.elastest.epm.repository;

import io.elastest.epm.model.PoP;
import org.springframework.data.repository.CrudRepository;

public interface PoPRepository extends CrudRepository<PoP, String>, PoPRepositoryCustom {

  PoP findOneByName(String name);
}
