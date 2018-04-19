package io.elastest.epm.repository;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import org.springframework.data.repository.CrudRepository;

public interface AdapterRepository extends CrudRepository<Adapter, String>, AdapterRepositoryCustom {

    Adapter findFirstByType(String type);
}
