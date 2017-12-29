[![License badge](https://img.shields.io/badge/license-Apache2-orange.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Docker badge](https://img.shields.io/docker/pulls/elastest/epm.svg)](https://hub.docker.com/r/elastests/epm/)

<!-- Elastest logo -->
[![][ElasTest Logo]][ElasTest]

Copyright © 2017-2019 [ElasTest]. Licensed under [Apache 2.0 License].

elastest/epm
==============================

What is ElasTest Platform Manager?
==============================

The ElasTest Platform Manager is the interface between the ElasTest testing components (e.g. TORM, Test Support Services, etc.) and the cloud infrastructure where ElasTest is deployed. Hence, this Platform Manager abstracts the cloud services so that ElasTest becomes fully agnostic to them and provide this abstraction via Software Development Toolkits (SDK) or REST APIs to the northbound consumers (i.e. the TORM). The ElasTest Platform Manager enabling ElasTest to be deployed and to execute seamlessly in the target cloud infrastructure that the consortium considers as appropriate (e.g. OpenStack, CloudStack, Mantl, AWS, etc.).

# Supported tags and respective `Dockerfile` links
-	[`0.5.0` (*1.0/Dockerfile*)](https://github.com/elastest/elastest-platform-manager/blob/0.5.0/docker/elastest-platform-manager/Dockerfile)

# Quick reference

-	**Where to get help**:  

	[the ElastTest mailing list][ElasTest Public Mailing List], [the Elastest Slack][ElasTest Slack], or [Stack Overflow][StackOverflow]

-	**Where to file issues**:  

	Issues and bug reports should be posted to the [GitHub ElasTest Bugtracker].

-	**Maintained by**:  

	[the ElasTest community](https://github.com/elastest)

-	**Published image artifact details**:

	(image metadata, transfer size, etc).

-	**Source of this description**:  

	[docs repo's `template/` directory](https://github.com/elastest/elastest-platform-manager/blob/master/docs/Docker-epm.md) ([history](https://github.com/elastest/epm/commits/master/docs/Docker-epm.md))

-	**Supported Docker versions**:  

	[the latest release](https://github.com/docker/docker/releases/latest) (down to 17.03.1 on a best-effort basis)

# What's on this image?


The EPM offers the following features:

* Supports the following Virtualized Infrastructures:
    * Docker (managing Containers, Networks and Images)
    * Docker Compose (managing Services and Networks, defined in Docker Compose files)
* Runtime operations (download/upload files; execute commands; start/stop instances)
* Forwarding of logs via logstash (only supported in full stack deployment)
* Monitoring of containers via dockbeat (only supported in full stack deployment)

# How to use this image


```bash
docker run -p 8180:8180 -v /var/run/docker.sock:/var/run/docker.sock elastest/epm
```

Additional information on how to run the EPM can be found [here][installation_guide].

How to use the EPM in order to manager virtual resources is described [here][usage_guide].

The EPM implements PoP Adapters to be able to interact with different kinds of Cloud Environments. Documentation can be found [here][adapters_guide].

The EPM offers SDKs for developers in order to integrate with other components. Documentation can be found [here][sdk_guide].

The Development Guide can be found [here][development_guide].

The following links gives you an overview and descriptions of the API exposed by the EPM:

* [Full API descriptions][api_online]
* [API Overview][api_overview]
* [API Definitions][api_definitions]
* [API paths][api_paths]

## Dependencies (other containers or tools)


none


## Integration with other containers or tools)


none

[Apache 2.0 License]: http://www.apache.org/licenses/LICENSE-2.0
[ElasTest]: http://elastest.io/
[ElasTest Logo]: http://elastest.io/images/logos_elastest/elastest-logo-gray-small.png
[ElasTest Twitter]: https://twitter.com/elastestio
[GitHub ElasTest Group]: https://github.com/elastest
[GitHub ElasTest Bugtracker]: https://github.com/elastest/bugtracker
[ElasTest Public Mailing List]: https://groups.google.com/forum/#!forum/elastest-users
[StackOverflow]: http://stackoverflow.com/questions/tagged/elastest
[ElasTest Slack]: elastest.slack.com
[installation_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/installation.md
[usage_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/usage.md
[sdk_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/sdks.md
[adapters_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/adapters.md
[api_online]: http://elastest.io/docs/api/epm/
[api_overview]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/api/overview.md
[api_definitions]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/api/definitions.md
[api_paths]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/api/paths.md
[development_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/development.md
