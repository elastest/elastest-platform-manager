# Installation 

The Elastest Platform Manager provides two types of installations:

* [Running the EPM](#running-elastest-platform-manager)
* [Running full stack](#running-full-stack) 

The Docker image of the EPM is hosted on [Docker Hub][docker_hub] and available [here][docker_hub_epm].

## Running Elastest Platform Manager

This guide shows how to run the EPM with Docker. This installation does not provide monitoring and log forwarding of instances. If you are interested in those features, you need to go for the Full Stack Deployment 

### Prerequisites

In order to run the EPM the following requirements must be fullfilled:

* Docker installed. [Installation Guide][docker_installation_guide]
* Docker Compose installed (recommended). [Installation Guide][docker_compose_installation_guide]
* Git installed (recommended). [Installation Guide][git_installation_guide]

### How to run EPM

1. Pulling the [EPM image][docker_hub_epm] and [EPM Docker Compose Adapter Image][docker_hub_epm_compose_adapter] from Docker Hub:

    ```bash
    docker pull elastest/epm && \
    docker pull elastest/epm-adapter-docker-compose && \
    docker pull elastest/epm-adapter-docker && \
    docker pull elastest/epm-adapter-ansible
    ```

2. There are two options for deploying them - run the images seperately or deploy them using a compose file:
    1. Running the images seperately:
    
        ```bash
        docker run -p 8180:8180 -d -v /var/run/docker.sock:/var/run/docker.sock elastest/epm && \
        docker run -p 50051:50051 -d -v /var/run/docker.sock:/var/run/docker.sock elastest/epm-adapter-docker-compose && \
        docker run -p 50053:50053 -d -v /var/run/docker.sock:/var/run/docker.sock elastest/epm-adapter-docker && \
        docker run -p 50052:50052 -d elastest/epm-adapter-ansible
        ```
        **Note** In this case you have to register the Docker Compose Adapter to the EPM before being able to use it.
    2. Running both components using Docker Compose (Recommended)
        
        ```bash
        git clone https://github.com/elastest/elastest-platform-manager.git && \
        cd elastest-platform-manager && \
        docker-compose -f docker-compose-epm.yml up
        ```
**Note** port '8180' must be forwarded to the host machine in order be reach the API

**Note** '/var/run/docker.sock' must be shared in order to allow the EPM to use the local docker environment

**Note** Starting the EPM and the Adapter using docker compose will let the adapter auto register to the EPM, so no further defining the PoP is needed.

## Running Full Stack

The full stack deployment allows monitoring of instances (via dockbeat) and collecting logs (via logstash). Hence, the following components must be installed:

* [dockbeat] -> monitoring Docker containers
* [elasticsearch] -> search and analytics engine
* [rabbitmq] -> messaging system
* [logstash] -> data processing pipeline
* [kibana] -> visualize data of elasticsearch

### Prerequisites

In order to run the EPM the following requirements must be cosidered:

* Git. [Installation Guide][git_installation_guide] 
* Docker compose. [Installation Guide][docker_compose_installation_guide]

### How to run Full Stack

1. Cloning the EPM from GitHub:
    ```bash
    git clone https://github.com/elastest/elastest-platform-manager.git
    ```

2. Running docker compose:

    ```bash
    docker-compose -f docker-compose-ci.yml up 
    ```

**Note**: Port forwarding and sharing of volumes is already done within the docker-compose file.

**Note**: Kibana is reachable at localhost:5601


[docker_hub]: https://hub.docker.com/
[docker_hub_epm]: https://hub.docker.com/r/elastest/epm/ 
[docker_hub_epm_compose_adapter]: https://hub.docker.com/r/elastest/epm-adapter-docker-compose/
[docker_installation_guide]: https://docs.docker.com/engine/installation/
[docker_compose_installation_guide]: https://docs.docker.com/compose/install/
[git_installation_guide]: https://git-scm.com/downloads
[dockbeat]: https://www.elastic.co/blog/dockbeat-a-new-addition-to-the-beats-community
[elasticsearch]: https://www.elastic.co/products/elasticsearch
[rabbitmq]: https://www.rabbitmq.com/
[logstash]: https://www.elastic.co/products/logstash
[kibana]: https://www.elastic.co/products/kibana
