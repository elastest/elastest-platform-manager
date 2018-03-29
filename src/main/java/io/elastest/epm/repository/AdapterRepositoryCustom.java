package io.elastest.epm.repository;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;

public interface AdapterRepositoryCustom {

    Adapter findAdapterForTypeAndIp(String type, String ip) throws NotFoundException;
}
