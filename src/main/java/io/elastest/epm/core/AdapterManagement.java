package io.elastest.epm.core;

import io.elastest.epm.api.AdapterHandler;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class AdapterManagement {

    @Autowired
    private AdapterHandler adapterHandler;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private Server server;

    public void init() throws IOException, InterruptedException {
        this.start();
        this.blockUntilShutdown();
    }

    private void start() throws IOException {
    /* The port on which the server should run */
        int port = 50050;
        server = ServerBuilder.forPort(port)
                .addService(adapterHandler)
                .build()
                .start();
        log.info("Started listening for adapters at: " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                AdapterManagement.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
