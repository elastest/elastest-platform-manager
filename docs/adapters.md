# Adpaters

The EPM offers Adapters to support different Cloud Environments.

* Docker Adapter
* Docker Compose Adapter

## Docker Adapter

The Docker Adapter is implemented directly inside the EPM. It uses the internal PoP, Network and VDU models of the EPM to deploy docker containers. 
It also implements Runtime operations, which are called from the Runtime Management component of the EPM.

## Docker Compose Adapter

The Docker Compose Adapter is implemented seperately. It connects to the EPM using the Google RPC framework. 
Source code and documentation can be found [here][epm-adapter-docker-compose].


[epm-adapter-docker-compose]: https://github.com/mpauls/epm-adapter-docker-compose
