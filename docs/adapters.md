# Adpaters

The EPM offers Adapters to support different Cloud Environments.

* Docker Adapter
* Docker-Compose Adapter
* Ansible Adapter

## Docker Adapter

The Docker Adapter is implemented as a seperate Spring Boot Application. It uses the internal PoP, Network and VDU models of the EPM to deploy docker containers. 
It also implements Runtime operations, which are called from the Runtime Management component of the EPM. More information
about the docker adapter can be found [here][epm-adapter-docker].

## Docker-Compose Adapter

The Docker-Compose Adapter is implemented seperately. It connects to the EPM using the Google RPC framework. 
Source code and documentation can be found [here][epm-adapter-docker-compose].

## Ansible Adapter

The Ansible Adapter is still under development and currently only supports launching Virtual Machines in OpenStack. 
The Virtual Resources are defined using plays and are packaged together with a metadata file and optionally a public
key. The source code and more detailed information can be found [here][epm-adapter-ansible] 

## Starting the adapters

There are multiple ways available to start the adapters. In links above you will find information about how to start them
directly from the source code or in a docker container. If you want to start them all together with the EPM run the following
command inside the root directory of the EPM:

```bash
docker-compose -f docker-compose-epm.yml up
```

[epm-adapter-docker-compose]: https://github.com/mpauls/epm-adapter-docker-compose
[epm-adapter-docker]: https://github.com/mpauls/epm-adapter-docker
[epm-adapter-ansible]: https://github.com/mpauls/epm-adapter-ansible
