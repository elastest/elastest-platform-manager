# Usage

The following section give a brief overview of basic operation which can be executed via the ReSTful API. 

**Note**: Other examples can be found in the testing classes of the [SDKs][sdks]

## Getting Started

To get started with the EPM and its internal model and runtime operations you can follow this [tutorial][local_docker].
It will go through the whole internal model of the EPM and the runtime operations, but does not require any other 
component so can be started right away.

## Adapters

The EPM works together with different adapters, so that it is able to communicate and execute on a number of different 
Virtual Environments. More information about the adapters and how to start them can be found [here](adapters).

## Workers

The EPM supports the use of remote workers by providing ways to manage them and make them *ready* for deploying 
virtual resources on top of them. This [page][workers] explains how to register and configure a worker to be made usable
by the EPM and its components.

## Creating and Launching a package

After providing the necessary environment and starting the chosen adapter the user can start Virtual Resources by 
uploading a package to the EPM. More information about how to build and launch packages can be found [here][package].

## Runtime Operations

Finally after the Virtual Resources have been created they can be managed during runtime as explained [here][runtime].

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
[local_docker]: usage/local_docker.md
[adapters]: adapters.md
[workers]: usage/workers.md
[package]: usage/package.md
[package]: usage/runtime.md
