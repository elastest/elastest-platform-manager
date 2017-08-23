
<a name="paths"></a>
## Paths

<a name="createnetwork"></a>
### Creates a new network.
```
POST /network
```


#### Description
Creates a new network in the target cloud environment with the given CIDR.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**body**  <br>*required*|Defintion of a Network which has to be created on a certain PoP|[Network](#network)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Network creation OK|[Network](#network)|
|**201**|Created|No Content|
|**400**|Bad Request|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|string|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* Network


<a name="getallnetworks"></a>
### Returns all existing networks.
```
GET /network
```


#### Description
Returns all networks with all details.


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|< [Network](#network) > array|


#### Produces

* `application/json`


#### Tags

* Network


<a name="getnetworkbyid"></a>
### Returns a network.
```
GET /network/{id}
```


#### Description
Returns the network with the given ID. Returns all its details.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of Network|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|[Network](#network)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Network not found|string|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* Network


<a name="deletenetwork"></a>
### Deletes a network.
```
DELETE /network/{id}
```


#### Description
Deletes the network that matches with a given ID.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of Network|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Network deleted successfully|string|
|**204**|No Content|No Content|
|**400**|Bad Request|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Network not found|string|


#### Tags

* Network


<a name="updatenetwork"></a>
### Updates a Network.
```
PATCH /network/{id}
```


#### Description
Updates an existing Network.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of Network|string|
|**Body**|**body**  <br>*required*|Network that needs to be updated.|[Network](#network)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Network Update OK|[Network](#network)|
|**204**|No Content|No Content|
|**400**|Bad Request|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Network not found|string|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* Network


<a name="registerpop"></a>
### Registers a new PoP
```
POST /pop
```


#### Description
Registers a new Point-of-Presence represented by a PoP


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**body**  <br>*required*|Defintion of a PoP which defines a Point-of-Presence used to host resources|[PoP](#pop)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|PoP registration OK|[PoP](#pop)|
|**400**|Bad Request|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|string|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* PoP


<a name="getallpops"></a>
### Returns all PoPs.
```
GET /pop
```


#### Description
Returns all PoPs with all its details.


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|< [PoP](#pop) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `application/json`


#### Tags

* PoP


<a name="getpopbyid"></a>
### Returns a PoP.
```
GET /pop/{id}
```


#### Description
Returns the PoP with the given ID. Returns all its details.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of PoP|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|[PoP](#pop)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|PoP not found.|string|


#### Produces

* `application/json`


#### Tags

* PoP


<a name="unregisterpop"></a>
### Unregisters a PoP.
```
DELETE /pop/{id}
```


#### Description
Unregisters the PoP that matches with a given ID.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of PoP|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|PoP unregistered successfully|string|
|**204**|No Content|No Content|
|**400**|Bad Request|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|PoP not found|string|


#### Produces

* `*/*`


#### Tags

* PoP


<a name="updatepop"></a>
### Updates a PoP.
```
PATCH /pop/{id}
```


#### Description
Updates an already registered PoP.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of PoP|string|
|**Body**|**body**  <br>*required*|PoP object that needs to be updated.|[PoP](#pop)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|PoP Update OK|[PoP](#pop)|
|**400**|Bad Request|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|PoP not found|string|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* PoP


<a name="createresourcegroup"></a>
### Creates a new Resource Group.
```
POST /resourceGroup
```


#### Description
Creates a new Resource Group and allocates the defined resources in the defined PoPs.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**body**  <br>*required*|Defintion of a Resource Group which includes all VDUs, Network and respective PoPs|[ResourceGroup](#resourcegroup)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Resource Group creation OK|[ResourceGroup](#resourcegroup)|
|**201**|Created|No Content|
|**400**|Bad Request|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* ResourceGroup


<a name="getallresourcegroups"></a>
### Returns all Resource Groups.
```
GET /resourceGroup
```


#### Description
Returns all Resource Groups with all details.


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|< [ResourceGroup](#resourcegroup) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `application/json`


#### Tags

* ResourceGroup


<a name="getresourcegroupbyid"></a>
### Returns a Resource Group.
```
GET /resourceGroup/{id}
```


#### Description
Returns the Resource Group with the given ID. Returns all its details.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of ResourceGroup|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|[ResourceGroup](#resourcegroup)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|ResourceGroup not found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* ResourceGroup


<a name="deleteresourcegroup"></a>
### Deletes a Resource Group.
```
DELETE /resourceGroup/{id}
```


#### Description
Deletes the Resource Group that matches with a given ID.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of ResourceGroup|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|ResourceGroup deleted successfully|string|
|**204**|No Content|No Content|
|**400**|Bad Request|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|ResourceGroup not found|string|


#### Produces

* `*/*`


#### Tags

* ResourceGroup


<a name="updateresourcegroup"></a>
### Updates a ResourceGroup.
```
PATCH /resourceGroup/{id}
```


#### Description
Updates an existing ResourceGroup.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of ResourceGroup|string|
|**Body**|**body**  <br>*required*|ResourceGroup that needs to be updated.|[ResourceGroup](#resourcegroup)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|ResourceGroup Update OK|[ResourceGroup](#resourcegroup)|
|**204**|No Content|No Content|
|**400**|Bad Request|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|ResourceGroup not found|string|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* ResourceGroup


<a name="executeoninstance"></a>
### Executes given command on the given VDU.
```
PUT /runtime/{id}/action/execute
```


#### Description
Executes the given command on the VDU with the given ID.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|
|**Body**|**commandExecutionBody**  <br>*required*|Contains command to be executed and a flag if for the completion of the execution should be awaited|[commandExecutionBody](#executeoninstance-commandexecutionbody)|

<a name="executeoninstance-commandexecutionbody"></a>
**commandExecutionBody**

|Name|Description|Schema|
|---|---|---|
|**awaitCompletion**  <br>*required*|**Example** : `true`|boolean|
|**command**  <br>*required*|**Example** : `"ls /"`|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU not found.|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* Runtime


<a name="startinstance"></a>
### Starts the given VDU.
```
PUT /runtime/{id}/action/start
```


#### Description
Starts the VDU with the given ID.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU/File not found.|string|


#### Tags

* Runtime


<a name="stopinstance"></a>
### Stops the given VDU.
```
PUT /runtime/{id}/action/stop
```


#### Description
Stops the VDU with the given ID.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU/File not found.|No Content|


#### Tags

* Runtime


<a name="uploadfiletoinstancebyfile"></a>
### Uploads a file to a VDU.
```
POST /runtime/{id}/file
```


#### Description
Uploads a given file to the given filepath to the given VDU.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|
|**FormData**|**file**  <br>*required*|File which has to be uploaded to the Instance. Either this or the hostPath but not both can be provided.|file|
|**FormData**|**remotePath**  <br>*required*|Absolute path where the file should go on the Instance|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|No Content|
|**400**|Bad Request|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU/Path not found.|string|


#### Consumes

* `multipart/form-data`


#### Tags

* Runtime


<a name="downloadfilefrominstance"></a>
### Downloads a file from a VDU.
```
GET /runtime/{id}/file
```


#### Description
Download a file with the given filepath from the given VDU.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|
|**Body**|**fileDownloadBody**  <br>*required*|Contains details of the file to be downloaded from the given Instance|[fileDownloadBody](#downloadfilefrominstance-filedownloadbody)|

<a name="downloadfilefrominstance-filedownloadbody"></a>
**fileDownloadBody**

|Name|Description|Schema|
|---|---|---|
|**path**  <br>*required*|**Example** : `"/"`|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|file|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU/File not found.|string|


#### Consumes

* `application/json`


#### Produces

* `multipart/form-data`


#### Tags

* Runtime


<a name="uploadfiletoinstancebypath"></a>
### Uploads a file to a VDU.
```
POST /runtime/{id}/path
```


#### Description
Uploads a given file from the host path to the given file path to the given VDU.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|
|**Body**|**fileUploadBody**  <br>*required*|Contains details of the file with the given path to upload to the Instance|[fileUploadBody](#uploadfiletoinstancebypath-fileuploadbody)|

<a name="uploadfiletoinstancebypath-fileuploadbody"></a>
**fileUploadBody**

|Name|Description|Schema|
|---|---|---|
|**hostPath**  <br>*required*|**Example** : `"/"`|string|
|**remotePath**  <br>*required*|**Example** : `"/"`|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|No Content|
|**400**|Bad request|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU/Path not found.|string|


#### Consumes

* `application/json`


#### Tags

* Runtime


<a name="deploytoscatemplate"></a>
### Deploys a Tosca template.
```
POST /tosca
```


#### Description
The TOSCA template defines VDUs, Networks and the PoPs where to allocate the virtual resources


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**body**  <br>*required*|TOSCA formatted template|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|[ResourceGroup](#resourcegroup)|
|**400**|Bad Request|string|
|**401**|Unauthorized|string|
|**403**|Forbidden|string|
|**404**|Not Found|string|


#### Consumes

* `text/yaml`


#### Produces

* `application/json`


#### Tags

* TOSCA


<a name="deployvdu"></a>
### Allocates resources in the target cloud.
```
POST /vdu
```


#### Description
Allocates resources defined as a VDU in the cloud to be deployed in the target cloud environment. It instantiates computing resources, deploys artifacts on them and manages the their lifecycle


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**body**  <br>*required*|Defintion of a VDU which defines resources that have to be deployed|[VDU](#vdu)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|VDU allocation OK|[VDU](#vdu)|
|**201**|Created|No Content|
|**400**|Bad Request|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|string|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* VDU


<a name="getallvdus"></a>
### Returns all VDUs.
```
GET /vdu
```


#### Description
Returns all VDUs with all its details.


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|< [VDU](#vdu) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* VDU


<a name="getvdubyid"></a>
### Returns a VDU.
```
GET /vdu/{id}
```


#### Description
Returns the VDU with the given ID. Returns all its details.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful operation|[VDU](#vdu)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU not found.|string|


#### Produces

* `application/json`


#### Tags

* VDU


<a name="deletevdu"></a>
### Terminates a VDU.
```
DELETE /vdu/{id}
```


#### Description
Terminates the VDU that matches with a given ID.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|VDU terminated successfully|string|
|**204**|No Content|No Content|
|**400**|Bad Request|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU not found|string|


#### Produces

* `*/*`


#### Tags

* VDU


<a name="updatevdu"></a>
### Updates a VDU.
```
PATCH /vdu/{id}
```


#### Description
Updates an already deployed VDU.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|ID of VDU|string|
|**Body**|**body**  <br>*required*|VDU object that needs to be updated.|[VDU](#vdu)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|VDU Update OK|[VDU](#vdu)|
|**400**|Bad Request|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|VDU not found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* VDU



