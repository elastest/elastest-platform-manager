package io.elastest.epm.api;

import io.elastest.epm.model.Adapter;
import io.elastest.epm.pop.generated.AdapterHandlerGrpc;
import io.elastest.epm.pop.generated.AdapterProto;
import io.elastest.epm.pop.generated.Empty;
import io.elastest.epm.pop.generated.ResourceIdentifier;
import io.elastest.epm.repository.AdapterRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdapterHandler extends AdapterHandlerGrpc.AdapterHandlerImplBase {

    @Autowired
    private AdapterRepository adapterRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void registerAdapter(AdapterProto request, StreamObserver<ResourceIdentifier> responseObserver) {

        Adapter adapter = new Adapter();
        adapter.setEndpoint(request.getEndpoint());
        adapter.setType(request.getType());
        adapterRepository.save(adapter);

        log.debug(String.valueOf(adapter));

        ResourceIdentifier resourceIdentifier = ResourceIdentifier.newBuilder().setResourceId(adapter.getId()).build();
        responseObserver.onNext(resourceIdentifier);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteAdapter(ResourceIdentifier request, StreamObserver<Empty> responseObserver) {

        if(adapterRepository.findOne(request.getResourceId()) != null){
            log.debug("Removing adapter: " + request.getResourceId());
            adapterRepository.delete(request.getResourceId());
        }
        else{
            log.debug("Adapter with id: " + request.getResourceId() + " not found.");
        }

        Empty reply = Empty.newBuilder().build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
