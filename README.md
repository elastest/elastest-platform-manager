[![][ElasTest Logo]][ElasTest]

Copyright © 2017-2019 [ElasTest]. Licensed under [Apache 2.0 License].

elastest-platform-manager (epm)
==============================

ElasTest Platform Manager.

# What is ElasTest

This repository is part of [ElasTest], which is an open source elastic platform
aimed to simplify end-to-end testing. ElasTest platform is based on three
principles: i) Test orchestration: Combining intelligently testing units for
creating a more complete test suite following the “divide and conquer” principle.
ii) Instrumentation and monitoring: Customizing the SuT (Subject under Test)
infrastructure so that it reproduces real-world operational behavior and allowing
to gather relevant information during testing. iii) Test recommendation: Using machine
learning and cognitive computing for recommending testing actions and providing
testers with friendly interactive facilities for decision taking.

# Documentation

The [ElasTest] project provides detailed documentation including tutorials,
installation and development guide.

## How to use it
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

### Create a new network
 
Once the PoP is registered, you can execute the following command in order to create a new network with the defined name and CIDR.

```
curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"name":"testNetwork123", "cidr": "192.168.4.1/24", "poPName":"docker-local"}' localhost:8180/v1/network
``` 

### Create a virtual compute instance

After registering a PoP and creating a network, you can allocate virtual compute resource with the following command:

```
curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"name": "testContainer", "imageName": "elastest/elastest-platform-manager:latest", "netName": "testNetwork123", "poPName": "docker-local", "metadata": [{"key": "LOGSTASH_ADDRESS","value": "tcp://localhost:5000"}]}' localhost:8180/v1/vdu
```

In this example it will be created a docker container with name 'testContainer' with image 'ubuntu' connected to the previously created network 'testNetwork123'. 

Note: The image with the name 'ubuntu' (ID can be used as well) must already exist.

# Source
Source code for other ElasTest projects can be found in the [GitHub ElasTest
Group].

# News
Follow us on Twitter @[ElasTest Twitter].

# Contribution policy
You can contribute to the ElasTest community through bug-reports, bug-fixes,
new code or new documentation. For contributing to the ElasTest community,
you can use the issue support of GitHub providing full information about your
contribution and its value. In your contributions, you must comply with the
following guidelines

* You must specify the specific contents of your contribution either through a
  detailed bug description, through a pull-request or through a patch.
* You must specify the licensing restrictions of the code you contribute.
* For newly created code to be incorporated in the ElasTest code-base, you
  must accept ElasTest to own the code copyright, so that its open source
  nature is guaranteed.
* You must justify appropriately the need and value of your contribution. The
  ElasTest project has no obligations in relation to accepting contributions
  from third parties.
* The ElasTest project leaders have the right of asking for further
  explanations, tests or validations of any code contributed to the community
  before it being incorporated into the ElasTest code-base. You must be ready
  to addressing all these kind of concerns before having your code approved.

# Licensing and distribution
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


[Apache 2.0 License]: http://www.apache.org/licenses/LICENSE-2.0
[ElasTest]: http://elastest.io/
[ElasTest Logo]: http://elastest.io/images/logos_elastest/elastest-logo-gray-small.png
[ElasTest Twitter]: https://twitter.com/elastestio
[GitHub ElasTest Group]: https://github.com/elastest
[Bugtracker]: https://github.com/elastest/bugtracker
