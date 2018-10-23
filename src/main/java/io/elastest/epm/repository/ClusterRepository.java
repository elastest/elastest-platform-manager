package io.elastest.epm.repository;

import io.elastest.epm.model.Cluster;
import io.elastest.epm.model.Worker;
import org.springframework.data.repository.CrudRepository;

public interface ClusterRepository extends CrudRepository<Cluster, String> {
}
