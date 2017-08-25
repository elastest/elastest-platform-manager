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

### How to run EPM

1. Pulling the [EPM image][docker_hub_epm] from Docker Hub:

    ```bash
    docker pull elastest/epm
    ```

2. Running the image:

    ```bash
    docker run -p 8180:8180 -v /var/run/docker.sock:/var/run/docker.sock elastest/epm
    ```

**Note** port '8180' must be forwarded to the host machine in order be reach the API

**Note** '/var/run/docker.sock' must be shared in order to allow the EPM to use the local docker environment

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
[docker_installation_guide]: https://docs.docker.com/engine/installation/
[docker_compose_installation_guide]: https://docs.docker.com/compose/install/
[git_installation_guide]: https://git-scm.com/downloads
[dockbeat]: https://www.elastic.co/blog/dockbeat-a-new-addition-to-the-beats-community
[elasticsearch]: https://www.elastic.co/products/elasticsearch
[rabbitmq]: https://www.rabbitmq.com/
[logstash]: https://www.elastic.co/products/logstash
[kibana]: https://www.elastic.co/products/kibana
