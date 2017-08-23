
<a name="definitions"></a>
## Definitions

<a name="event"></a>
### Event
An event contains certain life cycle information of the VDU at a specific time.


|Name|Description|Schema|
|---|---|---|
|**description**  <br>*required*|**Example** : `"testEvent1"`|string|
|**id**  <br>*optional*|**Example** : `"1234-abcd"`|string|
|**timestamp**  <br>*required*||string (string)|


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
|**id**  <br>*optional*|**Example** : `"1234-abcd"`|string|
|**name**  <br>*required*|**Example** : `"testNetwork1"`|string|
|**networkId**  <br>*required*|**Example** : `"1234-abcd"`|string|
|**poPName**  <br>*required*||string|


<a name="pop"></a>
### PoP
This entity contains information about the Point-of-Presence (PoP)


|Name|Description|Schema|
|---|---|---|
|**accessInfo**  <br>*required*|Authentication credentials for accessing the PoP. Examples may include those to support different authentication schemes, e.g. OAuth, Token, etc.|< [KeyValuePair](#keyvaluepair) > array|
|**id**  <br>*optional*|Identifier of the PoP  <br>**Example** : `"1234-abcd"`|string|
|**interfaceEndpoint**  <br>*required*|Information about the interface endpoint. An example is a URL.  <br>**Example** : `"localhost"`|string|
|**interfaceInfo**  <br>*required*|Information about the interface(s) to the PoP, including PoP provider type, API version, and protocol type.  <br>**Example** : `"[{&quot;key&quot;:&quot;type&quot;,&quot;value&quot;:&quot;docker&quot;}]"`|< [KeyValuePair](#keyvaluepair) > array|
|**name**  <br>*required*|Human-readable identifier of this PoP information element  <br>**Example** : `"testPoPName"`|string|


<a name="resourcegroup"></a>
### ResourceGroup
A Resource Group defines a bundle of VDUs and virtual networks which belongs together. It includes also the Point-of-Presences where the virtual resources have to be allocated.


|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|**Example** : `"1234-abcd"`|string|
|**name**  <br>*required*|**Example** : `"testResourceGroupName1"`|string|
|**networks**  <br>*optional*||< [Network](#network) > array|
|**pops**  <br>*optional*||< [PoP](#pop) > array|
|**vdus**  <br>*required*||< [VDU](#vdu) > array|


<a name="vdu"></a>
### VDU
A Virtual Deployment Unit (VDU) describes the capabilities of virtualized computing (Containers, VMs) and networking resources.


|Name|Description|Schema|
|---|---|---|
|**computeId**  <br>*required*|**Example** : `"1234-abcd"`|string|
|**events**  <br>*optional*||< [Event](#event) > array|
|**id**  <br>*optional*|**Example** : `"1234-abcd"`|string|
|**imageName**  <br>*required*|**Example** : `"testImage1"`|string|
|**ip**  <br>*required*|**Example** : `"172.0.0.1"`|string|
|**metadata**  <br>*optional*||< [KeyValuePair](#keyvaluepair) > array|
|**name**  <br>*required*|**Example** : `"testVdu1"`|string|
|**netName**  <br>*required*|**Example** : `"testNetworkName"`|string|
|**poPName**  <br>*required*||string|
|**status**  <br>*optional*||enum (initializing, initialized, deploying, deployed, running, undeploying, undeployed, error)|



