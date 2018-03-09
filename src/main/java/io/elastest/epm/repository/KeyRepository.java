package io.elastest.epm.repository;

import io.elastest.epm.model.Key;
import org.springframework.data.repository.CrudRepository;

public interface KeyRepository extends CrudRepository<Key, String> {

  Key findOneByName(String name);
}
