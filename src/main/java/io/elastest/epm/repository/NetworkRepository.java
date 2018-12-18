package io.elastest.epm.repository;

import io.elastest.epm.model.Network;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface NetworkRepository extends CrudRepository<Network, String> {
    Network findOneByName(String name);

    Network findOneByNameAndPoPName(String name, String popName);

    List<Network> findByName(String name);
}
