package io.elastest.epm.repository;

import io.elastest.epm.model.ResourceGroup;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ResourceGroupRepository extends CrudRepository<ResourceGroup, String> {
  ResourceGroup findOneByName(String name);

  List<ResourceGroup> findByName(String name);
}
