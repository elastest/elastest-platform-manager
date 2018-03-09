package io.elastest.epm.repository;

import io.elastest.epm.model.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, String> {

  Worker findOneByIp(String ip);
}
