package io.elastest.epm.repository;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.model.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AdapterRepositoryImpl implements AdapterRepositoryCustom {

    @Autowired
    private AdapterRepository adapterRepository;

    @Override
    @Transactional
    public Adapter findAdapterForTypeAndIp(String type, String ip) throws NotFoundException {
        Adapter output = null;
        Iterable<Adapter> adapters = adapterRepository.findAll();
        for (Adapter adapter : adapters) {
            if (adapter.getType().equals(type) && adapter.getEndpoint().contains(ip)){
                output = adapter;
                break;
            }
        }

        if (output == null)
            throw new NotFoundException("No " + type + " adapter was registered!");
        return output;
    }
}
