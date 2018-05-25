
<a name="definitions"></a>
## Definitions

<a name="adapter"></a>
### Adapter

|Name|Description|Schema|
|---|---|---|
|**endpoint**  <br>*required*|The endpoint where the Adapter is reachable.  <br>**Example** : `"localhost:50052"`|string|
|**id**  <br>*optional*|Identifier for the Adapter.|string|
|**type**  <br>*required*|The type of virtualization technology, that the adapter is designed to connect to.  <br>**Example** : `"docker-compose"`|string|


<a name="event"></a>
### Event
An event contains certain life cycle information of the VDU at a specific time.


|Name|Description|Schema|
|---|---|---|
|**description**  <br>*required*|**Example** : `"testEvent1"`|string|
|**id**  <br>*optional*|**Example** : `"1234-abcd"`|string|
|**timestamp**  <br>*required*|The recorded time of the Event.|string (string)|


<a name="key"></a>
### Key
A private key for executing commands on a worker.


|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|The identifier of the Key|string|
|**key**  <br>*required*|This is the key itself as String.|string|
|**name**  <br>*required*|The name of the key. This will be used for referencing the Key in a Worker.  <br>**Example** : `"key1"`|string|


<a name="keyvaluepair"></a>
### KeyValuePair
This entity is a Key-Value pair for storing metadata contained in other entities


|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|**Example** : `"1234-abcd"`|string|
|**key**  <br>*required*|**Example** : `"testKey1"`|string|
|**value**  <br>*required*|**Example** : `"testValue1"`|string|


<a name="network"></a>
### Network
This entity defines the network connectivity and details where the VDUs are connected to.


|Name|Description|Schema|
|---|---|---|
|**cidr**  <br>*required*|**Example** : `"192.168.1.1/24"`|string|
|**id**  <br>*optional*|The identifier of the Network in the EPM.  <br>**Example** : `"1234-abcd"`|string|
|**name**  <br>*required*|The name of the network, this should correspond to the name of the network in the virtualization technology.  <br>**Example** : `"testNetwork1"`|string|
|**networkId**  <br>*required*|The id of the Network in the virtualization technology.  <br>**Example** : `"1234-abcd"`|string|
|**poPName**  <br>*required*|The PoP where the Network was created.|string|


<a name="pop"></a>
### PoP
This entity contains information about the Point-of-Presence (PoP)


|Name|Description|Schema|
|---|---|---|
|**accessInfo**  <br>*required*|Authentication credentials for accessing the PoP. Examples may include those to support different authentication schemes, e.g. OAuth, Token, etc.|< [KeyValuePair](#keyvaluepair) > array|
|**id**  <br>*optional*|Identifier of the PoP|string|
|**interfaceEndpoint**  <br>*required*|Information about the interface endpoint. An example is a URL.  <br>**Example** : `"localhost"`|string|
|**interfaceInfo**  <br>*required*|Information about the interface(s) to the PoP, including PoP provider type, API version, and protocol type.  <br>**Example** : `"[{&quot;key&quot;:&quot;type&quot;,&quot;value&quot;:&quot;docker&quot;}]"`|< [KeyValuePair](#keyvaluepair) > array|
|**name**  <br>*required*|Human-readable identifier of this PoP information element  <br>**Example** : `"testPoPName"`|string|
|**status**  <br>*optional*||enum (configure, active, inactive)|


<a name="resourcegroup"></a>
### ResourceGroup
A Resource Group defines a bundle of VDUs and virtual networks which belongs together. It includes also the Point-of-Presences where the virtual resources have to be allocated.


|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|The identifier of the Resource Group in the EPM.|string|
|**name**  <br>*required*|The name of the Resource Group.  <br>**Example** : `"testResourceGroupName1"`|string|
|**networks**  <br>*optional*||< [Network](#network) > array|
|**vdus**  <br>*required*|The VDUs of which this Resource Group consists of.|< [VDU](#vdu) > array|


<a name="vdu"></a>
### VDU
A Virtual Deployment Unit (VDU) describes the capabilities of virtualized computing (Containers, VMs) and networking resources.


|Name|Description|Schema|
|---|---|---|
|**computeId**  <br>*required*|The identifier of the deployed VDU in the virtualization technology.|string|
|**events**  <br>*optional*|A list of events recorded for this VDU.|< [Event](#event) > array|
|**id**  <br>*optional*|The identifier of the VDU in the EPM.|string|
|**imageName**  <br>*required*|The name of the image used for the VDU.  <br>**Example** : `"testImage1"`|string|
|**ip**  <br>*required*|The IP assigned to the VDU.  <br>**Example** : `"172.0.0.1"`|string|
|**metadata**  <br>*optional*|More detailed information about the VDU in a Key-Value pair format.|< [KeyValuePair](#keyvaluepair) > array|
|**name**  <br>*required*|The name of the VDU.  <br>**Example** : `"testVdu1"`|string|
|**netName**  <br>*required*|The name of the network to which the VDU is associated with.  <br>**Example** : `"testNetworkName"`|string|
|**poPName**  <br>*required*|The name of the PoP where the VDU is deployed.|string|
|**status**  <br>*optional*||enum (initializing, initialized, deploying, deployed, running, undeploying, undeployed, error)|


<a name="worker"></a>
### Worker
A worker object for registering a machine where adapters can be deployed.


|Name|Description|Schema|
|---|---|---|
|**epmIp**  <br>*required*|This is the IP where the EPM is reachable for the Worker. This is needed because the Worker has to be able to reach the EPM for registering adapters.|string|
|**id**  <br>*optional*|Identifier for the Adapter.|string|
|**ip**  <br>*required*|The IP where the Worker is reachable. The EPM will try to ssh in to the Worker at this IP.|string|
|**keyname**  <br>*required*|The name of the Key, which the EPM will use for ssh in to the Worker. This refers to the name provided when uploading the Key to the EPM.  <br>**Example** : `"key1"`|string|
|**passphrase**  <br>*required*|This is the Passphrase of the Key provided for connecting to the Worker.|string|
|**password**  <br>*optional*|This is the password of the user, which can be left blank if no password is needed.|string|
|**user**  <br>*required*|This is the user, which the EPM will use when trying to ssh in to the Worker.  <br>**Example** : `"ubuntu"`|string|



