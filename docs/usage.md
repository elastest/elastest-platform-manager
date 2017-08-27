# Usage

The following section give a brief overview of basic operation which can be executed via the ReSTful API. 

**Note**: Other examples can be found in the testing classes of the [SDKs][sdks]

## How to allocate resources
This section gives a short overview how to use the ElasTest Platform Manager and issue requests over the ReSTful API in order to:
* register a new PoP (Point-of-Presence) to be used to allocate virtual resources
* create networks where the virtual compute resources will be connected to
* allocate compute resources

The API expects json files to be passed. The json files used in the following examples are located the folder 'json' of this project.

### Register a new PoP

In the following example it is assumed that Docker runs locally on the machine where the ElasTest Platform Manager is running. To register this PoP you can use the following command:

```
curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"name": "docker-local", "interfaceEndpoint": "unix:///var/run/docker.sock"}' localhost:8180/v1/pop
```

This command will create a new PoP with name 'docker-local' which is used afterwards for allocating virtual resources.

**Note**: By default, this PoP will be registered automatically when starting the EPM. 

### Create a new network
 
Once the PoP is registered, you can execute the following command in order to create a new network with the defined name and CIDR.

```
curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"name":"testNetwork123", "cidr": "192.168.4.1/24", "poPName":"docker-local"}' localhost:8180/v1/network
``` 

**Note**: If you define a CIDR, keep in mind that every network needs a different CIDR in order to function properly.

### Create a virtual compute instance

After registering a PoP and creating a network, you can allocate virtual compute resource with the following command:

```
curl -i -X POST localhost:8180/v1/vdu -H "Content-Type: application/json" -H "Accept: application/json" -d \
    '{
        "name": "testContainer", "imageName": "elastest/epm:latest", 
        "netName": "testNetwork123", 
        "poPName": "docker-local", 
        "metadata": [
            {
                "key": "LOGSTASH_ADDRESS",
                "value": "tcp://localhost:5000"
            }, 
            {
                "key": "VOLUME",
                "value": "/var/run/docker.sock:/var/run/docker.sock"
            }, 
            {
                "key": "PORT_BINDING",
                "value": "8181:8180/tcp"
            },
            {
                "key": "ENVIRONMENT_VARIABLE",
                "value": "TEST=test"} 
        ]
    }'
```

In this example it will be created a docker container with name 'testContainer' with image 'elastest/epm' connected to the previously created network 'testNetwork123'. In addition, in the metadata you can find the configuration for environment variables, logstash, volumes and ports.

**Note**: The image with the name 'elastest/epm' (ID can be used as well) will pulled on demand if it does not available yet. This requires some additional time when launching it the first time.

**Note**: Forwarding of logs is possible only if logstash is reachable/available. If it is not available, the instance cannot be launched since it fails while connecting. In this case the metadata value for 'LOGSTASH_ADDRESS' must not be passed.

**Note**: Additional configurations can be passed via the metadata. In the example you can find the docker-specific configuration for:

* volumes: 'VOLUME' can be used in order to attach volumes to the container. The value follows the same definition as done in docker: "<HOST_PATH>:<CONTAINER_PATH>"
* ports: 'PORT_BINDING' can be used in order to forward traffic from the host machine to the container. The value follows the same definition as done in docker: "<HOST_PORT>:<CONTAINER_PORT>/<PROTOCOL>"
* environment variables: 'ENVIRONMENT_VARIABLE' can be used in order to set and use environment variables inside container. The value follows this definition: "<ENVIRONMENT_VARIABLE_NAME>=<ENVIRONMENT_VARIABLE_VALUE>" 

Each of those parameters can be defined several times in order to allow several volumes attached, port bindings configured and environment variables passed.

## Listing resources

* List PoPs: 
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/pop
```

* List Networks:
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/network
```

* List VDUs:
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/vdu
```

* List Resource Groups:
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/resourceGroup
```

## Executing Runtime Operations

* Download a file from an instance:
```bash
curl -X GET -H "accept: multipart/form-data" -H "content-type: multipart/form-data" -d {"filePath":"/PATH_TO_FILE“} "http://localhost:8180/v1/runtime/{VDU_ID}/file“
```

* Upload a file from the given path:
```bash
curl -X POST -H "accept: application/json" -H "content-type: application/json" -d "{ "remotePath": "{PATH_ON_INSTANCE}", "hostPath": "{PATH_ON_HOST}"}" "http://localhost:8180/v1/runtime/{VDU_ID}/path"
```

* Upload a file that is passed within the request:
```bash
curl -X POST -H "content-type: multipart/form-data" -F "remotePath=/" -F "file=@{PATH_TO_FILE}“ "http://localhost:8180/v1/runtime/{VDU_ID}/file“
```

* Execute a command on the instance:
```bash
curl -X PUT -H "accept: application/json" -H "content-type: application/json" -d {"command":"ls","awaitCompletion":"true“} "http://localhost:8180/v1/runtime/{VDU_ID}/action/execute“
```

* Start instance
```bash
curl -X PUT "http://localhost:8180/v1/runtime/{VDU_ID}/action/start"
```

* Stop instance
```bash
curl -X PUT "http://localhost:8180/v1/runtime/{VDU_ID}/action/stop"
```

## Json examples

In the following you can find some descriptors how a json for a certain body to be passed has to be defined:

* [PoP][json_pop]
* [Network][json_network]
* [VDU][json_vdu]
* [Resource Group][json_resourcegroup] 
* [Command execution][json_command]

## TOSCA example

In the following you can find a valid TOSCA template:

* [TOSCA template][tosca_template]

[sdks]: sdks.md
[json_network]: ../descriptors/json/network.json
[json_vdu]: ../descriptors/json/vdu.json
[json_pop]: ../descriptors/json/pop.json
[json_resourcegroup]: ../descriptors/json/resource_group.json
[json_command]: ../descriptors/json/command.json
[tosca_template]: ../descriptors/tosca/service_template.yaml