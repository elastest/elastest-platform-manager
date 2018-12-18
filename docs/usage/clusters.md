# Clusters

The EPM currently supports the creation of Kubernetes clusters.

There are two options for creating a Kubernetes Cluster using the EPM:
1) Starting a Kubernetes Cluster on OpenStack 
2) Starting a Kubernetes Cluster on already existing Virtual Machines

## Starting a Kubernetes Cluster on OpenStack

1. Start any given number of Virtual Machines using an ansible package on Open Stack as shown [here](package)
2. After the Package is started the EPM will return a Resource Group as JSON - you will need the ID of the Resource Group
and the ID of the VDU which will become the Master Node
3.  Install and Register the Cluster

    3.1. Using CURL Run the following command:
```bash

curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"resourceGroupId":"<RESOURCE_GROUP_ID>", "masterId":"<VDU_ID>", "type":["kubernetes"]}' localhost:8180/v1/cluster/create
```
    
   3.2. Using the python client
   
```python
import time
import epm_client
from epm_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
cluster_api = epm_client.ClusterApi()
package_api = epm_client.PackageApi()
ansible_package = package_api.receive_package(file='resources/ansible-package.tar')

cluster_from_resource_group = ClusterFromResourceGroup(resource_group_id=ansible_package.id, type=["kubernetes"], master_id=ansible_package.vdus[0].id)

try: 
    # Creates a new cluster.
    api_response = cluster_api.create_cluster(cluster_from_resource_group)
    pprint(api_response)
except ApiException as e:
    print "Exception when calling ClusterApi->create_cluster: %s\n" % e
```

   3.3. Using the Java Client
   
```java
// Import classes:
import io.elastest.epm.client.ApiException;
import io.elastest.epm.client.api.ClusterApi;
import io.elastest.epm.client.api.PackageApi;


private final PackageApi packageApi = new PackageApi();
private final ClusterApi clusterApi = new ClusterApi();

File ansible = new File("src/test/resources/ansible-package.tar");
ResourceGroup ansibleRG = packageApi.receivePackage(ansible);

ClusterFromResourceGroup clusterFromResourceGroup = new ClusterFromResourceGroup();
clusterFromResourceGroup.setResourceGroupId(ansible2RG.getId());
clusterFromResourceGroup.setMasterId(ansible2RG.getVdus().get(0).getId());
clusterFromResourceGroup.addTypeItem("kubernetes");

try {
    Cluster cluster = clusterApi.createCluster(clusterFromResourceGroup);
    System.out.println(cluster);
} catch (ApiException e) {
    System.err.println("Exception when calling WorkerApi#createWorker");
    e.printStackTrace();
}

```

This will install everything required on the Virtual Machines and configure the Kubernetes Cluster.
 When the process finished the EPM will return the Cluster information as JSON. 

## Adding a new node to the Cluster

After launching a Kubernetes Cluster successfuly, there is the option to add a new node. This can be done using either an 
already registered worker or a VDU. 

This can be done in the following way using a CURL request: 

```bash
curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"resourceGroupId":"<RESOURCE_GROUP_ID>", "masterId":"<VDU_ID>", "type":["kubernetes"]}' localhost:8180/v1/cluster/create
```

Python Client Example:

```python
import time
import epm_client
from epm_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
cluster_api = epm_client.ClusterApi()
package_api = epm_client.PackageApi()
ansible_package = package_api.receive_package(file='resources/ansible-package.tar')

#cluster = cluster_api.create_cluster(cluster_from_resource_group=cluster_from_resource_group) 

try: 
    # Register the first VDU of the Ansible Resource Group as a Worker part of the Kubernetes Cluster
    cluster_api.add_worker(id=cluster.id, machine_id=ansible_package.vdus[0].id)
    pprint(api_response)
except ApiException as e:
    print "Exception when calling ClusterApi->create_cluster: %s\n" % e
```

Java Client Example:

```java
// Import classes:
import io.elastest.epm.client.ApiException;
import io.elastest.epm.client.api.ClusterApi;
import io.elastest.epm.client.api.PackageApi;


private final ClusterApi clusterApi = new ClusterApi();
private final PackageApi packageApi = new PackageApi();

File ansible = new File("src/test/resources/ansible-package.tar");
ResourceGroup ansibleRG = packageApi.receivePackage(ansible);

Cluster cluster = new Cluster(); // Replace with return value when creating a cluster

try {
    // Register the first VDU of the Ansible Resource Group as a Worker part of the Kubernetes Cluster
    clusterApi.addWorker(cluster.getId(), ansibleRG.getVdus().get(0).getId());
} catch (ApiException e) {
    System.err.println("Exception when calling WorkerApi#createWorker");
    e.printStackTrace();
}

```

[adapters]: adapters.md
[workers]: workers.md
[workers]: clusters.md
[package]: package.md
[runtime]: runtime.md