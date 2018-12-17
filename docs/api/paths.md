# Paths

## Returns all registered adapters

    GET /adapters

### Responses

| HTTP Code | Description          | Schema                           |
| --------- | -------------------- | -------------------------------- |
| **200**   | Successful operation | \< [Adapter](#_adapter) \> array |
| **401**   | Unauthorized         | No Content                       |
| **403**   | Forbidden            | No Content                       |

### Produces

  - `application/json`

### Tags

  - Adapter

## Returns all clusters

    GET /cluster

### Responses

| HTTP Code | Description          | Schema                           |
| --------- | -------------------- | -------------------------------- |
| **200**   | Successful operation | \< [Cluster](#_cluster) \> array |
| **401**   | Unauthorized         | No Content                       |
| **403**   | Forbidden            | No Content                       |

### Produces

  - `application/json`

### Tags

  - Cluster

## Creates a new cluster.

    POST /cluster/create

### Description

Receives an Identifier for a ResourceGroup and an array of types to
setup the Resource Group as a cluster.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>clusterFromResourceGroup</strong><br />
<em>required</em></p></td>
<td><p>Body to create Cluster from ResourceGroup</p></td>
<td><p><a href="#_createcluster_clusterfromresourcegroup">clusterFromResourceGroup</a></p></td>
</tr>
</tbody>
</table>

**clusterFromResourceGroup**

<table>
<colgroup>
<col style="width: 16%" />
<col style="width: 61%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>masterId</strong><br />
<em>required</em></p></td>
<td><p>The ID of the VDU which will serve as the master node. This should be an ID of a VDU which belongs to the specified Ressource Group.</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>resourceGroupId</strong><br />
<em>required</em></p></td>
<td><p>The id of vdu from which to create the worker.</p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>type</strong><br />
<em>required</em></p></td>
<td><p>The types of the worker.<br />
<strong>Example</strong> : <code>&quot;kubernetes&quot;</code></p></td>
<td><p>&lt; string &gt; array</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description     | Schema               |
| --------- | --------------- | -------------------- |
| **200**   | Cluster created | [Cluster](#_cluster) |
| **201**   | Created         | No Content           |
| **400**   | Bad Request     | string               |
| **401**   | Unauthorized    | No Content           |
| **403**   | Forbidden       | No Content           |
| **404**   | Not Found       | string               |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - Cluster

## Deletes a Cluster.

    DELETE /cluster/{id}

### Description

Deletes the Cluster that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Cluster</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                  | Schema     |
| --------- | ---------------------------- | ---------- |
| **200**   | Cluster deleted successfully | string     |
| **204**   | No Content                   | No Content |
| **400**   | Bad Request                  | string     |
| **401**   | Unauthorized                 | No Content |
| **403**   | Forbidden                    | No Content |
| **404**   | Cluster not found            | string     |

### Produces

  - `/`

### Tags

  - Cluster

## Adds a worker to the cluster.

    GET /cluster/{id}/add/{machineId}

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Cluster</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Path</strong></p></td>
<td><p><strong>machineId</strong><br />
<em>required</em></p></td>
<td><p>The ID of either a Worker or a VDU, which will be added to the cluster</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | string     |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |

### Produces

  - `application/json`

### Tags

  - Cluster

## Sets up the specified cluster to install the specified technology and connected it.

    GET /cluster/{id}/{type}

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Cluster</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Path</strong></p></td>
<td><p><strong>type</strong><br />
<em>required</em></p></td>
<td><p>type of technology</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | string     |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |

### Produces

  - `application/json`

### Tags

  - Cluster

## Uploads a key to the EPM.

    POST /keys

### Description

This uploads a key to the EPM, so it can be later used when registering
workers.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>Key in a json</p></td>
<td><p><a href="#_key">Key</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                | Schema       |
| --------- | -------------------------- | ------------ |
| **200**   | Resource Group creation OK | [Key](#_key) |
| **201**   | Created                    | No Content   |
| **400**   | Bad Request                | No Content   |
| **401**   | Unauthorized               | No Content   |
| **403**   | Forbidden                  | No Content   |
| **404**   | Not Found                  | No Content   |

### Consumes

  - `application/json`

### Tags

  - Key

## Returns all available Keys

    GET /keys

### Responses

| HTTP Code | Description          | Schema                   |
| --------- | -------------------- | ------------------------ |
| **200**   | Successful operation | \< [Key](#_key) \> array |
| **401**   | Unauthorized         | No Content               |
| **403**   | Forbidden            | No Content               |

### Produces

  - `application/json`

### Tags

  - Key

## Deletes a Key.

    DELETE /keys/{id}

### Description

Deletes the Key that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Key</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description              | Schema     |
| --------- | ------------------------ | ---------- |
| **200**   | Key deleted successfully | string     |
| **204**   | No Content               | No Content |
| **400**   | Bad Request              | string     |
| **401**   | Unauthorized             | No Content |
| **403**   | Forbidden                | No Content |
| **404**   | Key not found            | string     |

### Produces

  - `/`

### Tags

  - Key

## Creates a new network.

    POST /network

### Description

Creates a new network in the target cloud environment with the given
CIDR.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>Defintion of a Network which has to be created on a certain PoP</p></td>
<td><p><a href="#_network">Network</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description         | Schema               |
| --------- | ------------------- | -------------------- |
| **200**   | Network creation OK | [Network](#_network) |
| **201**   | Created             | No Content           |
| **400**   | Bad Request         | string               |
| **401**   | Unauthorized        | No Content           |
| **403**   | Forbidden           | No Content           |
| **404**   | Not Found           | string               |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - Network

## Returns all existing networks.

    GET /network

### Description

Returns all networks with all details.

### Responses

| HTTP Code | Description          | Schema                           |
| --------- | -------------------- | -------------------------------- |
| **200**   | Successful operation | \< [Network](#_network) \> array |

### Produces

  - `application/json`

### Tags

  - Network

## Returns a network.

    GET /network/{id}

### Description

Returns the network with the given ID. Returns all its details.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Network</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema               |
| --------- | -------------------- | -------------------- |
| **200**   | Successful operation | [Network](#_network) |
| **401**   | Unauthorized         | No Content           |
| **403**   | Forbidden            | No Content           |
| **404**   | Network not found    | string               |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - Network

## Deletes a network.

    DELETE /network/{id}

### Description

Deletes the network that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Network</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                  | Schema     |
| --------- | ---------------------------- | ---------- |
| **200**   | Network deleted successfully | string     |
| **204**   | No Content                   | No Content |
| **400**   | Bad Request                  | string     |
| **401**   | Unauthorized                 | No Content |
| **403**   | Forbidden                    | No Content |
| **404**   | Network not found            | string     |

### Tags

  - Network

## Updates a Network.

    PATCH /network/{id}

### Description

Updates an existing Network.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Network</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>Network that needs to be updated.</p></td>
<td><p><a href="#_network">Network</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description       | Schema               |
| --------- | ----------------- | -------------------- |
| **200**   | Network Update OK | [Network](#_network) |
| **204**   | No Content        | No Content           |
| **400**   | Bad Request       | string               |
| **401**   | Unauthorized      | No Content           |
| **403**   | Forbidden         | No Content           |
| **404**   | Network not found | string               |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - Network

## Receives a package.

    POST /packages

### Description

Receives a package so that it can be forwarded to the correct
environment.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>FormData</strong></p></td>
<td><p><strong>file</strong><br />
<em>required</em></p></td>
<td><p>Package in a multipart form</p></td>
<td><p>file</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description         | Schema                           |
| --------- | ------------------- | -------------------------------- |
| **200**   | Package received OK | [ResourceGroup](#_resourcegroup) |
| **201**   | Created             | No Content                       |
| **400**   | Bad Request         | string                           |
| **401**   | Unauthorized        | No Content                       |
| **403**   | Forbidden           | No Content                       |
| **404**   | Not Found           | string                           |

### Consumes

  - `multipart/form-data`

### Produces

  - `application/json`

### Tags

  - Package

## Deletes a package.

    DELETE /packages/{id}

### Description

Deletes the package that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Package</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                  | Schema     |
| --------- | ---------------------------- | ---------- |
| **200**   | Package deleted successfully | No Content |
| **204**   | No Content                   | No Content |
| **400**   | Bad Request                  | string     |
| **401**   | Unauthorized                 | No Content |
| **403**   | Forbidden                    | No Content |
| **404**   | Package not found            | string     |

### Tags

  - Package

## Registers a new PoP

    POST /pop

### Description

Registers a new Point-of-Presence represented by a PoP

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>Defintion of a PoP which defines a Point-of-Presence used to host resources</p></td>
<td><p><a href="#_pop">PoP</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description         | Schema       |
| --------- | ------------------- | ------------ |
| **200**   | PoP registration OK | [PoP](#_pop) |
| **400**   | Bad Request         | No Content   |
| **401**   | Unauthorized        | No Content   |
| **403**   | Forbidden           | No Content   |
| **404**   | Not Found           | string       |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - PoP

## Returns all PoPs.

    GET /pop

### Description

Returns all PoPs with all its details.

### Responses

| HTTP Code | Description          | Schema                   |
| --------- | -------------------- | ------------------------ |
| **200**   | Successful operation | \< [PoP](#_pop) \> array |
| **401**   | Unauthorized         | No Content               |
| **403**   | Forbidden            | No Content               |

### Produces

  - `application/json`

### Tags

  - PoP

## Returns a PoP.

    GET /pop/{id}

### Description

Returns the PoP with the given ID. Returns all its details.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of PoP</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema       |
| --------- | -------------------- | ------------ |
| **200**   | Successful operation | [PoP](#_pop) |
| **401**   | Unauthorized         | No Content   |
| **403**   | Forbidden            | No Content   |
| **404**   | PoP not found.       | string       |

### Produces

  - `application/json`

### Tags

  - PoP

## Unregisters a PoP.

    DELETE /pop/{id}

### Description

Unregisters the PoP that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of PoP</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                   | Schema     |
| --------- | ----------------------------- | ---------- |
| **200**   | PoP unregistered successfully | string     |
| **204**   | No Content                    | No Content |
| **400**   | Bad Request                   | string     |
| **401**   | Unauthorized                  | No Content |
| **403**   | Forbidden                     | No Content |
| **404**   | PoP not found                 | string     |

### Produces

  - `/`

### Tags

  - PoP

## Updates a PoP.

    PATCH /pop/{id}

### Description

Updates an already registered PoP.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of PoP</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>PoP object that needs to be updated.</p></td>
<td><p><a href="#_pop">PoP</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description   | Schema       |
| --------- | ------------- | ------------ |
| **200**   | PoP Update OK | [PoP](#_pop) |
| **400**   | Bad Request   | No Content   |
| **401**   | Unauthorized  | No Content   |
| **403**   | Forbidden     | No Content   |
| **404**   | PoP not found | string       |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - PoP

## Creates a new Resource Group.

    POST /resourceGroup

### Description

Creates a new Resource Group and allocates the defined resources in the
defined PoPs.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>Defintion of a Resource Group which includes all VDUs, Network and respective PoPs</p></td>
<td><p><a href="#_resourcegroup">ResourceGroup</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                | Schema                           |
| --------- | -------------------------- | -------------------------------- |
| **200**   | Resource Group creation OK | [ResourceGroup](#_resourcegroup) |
| **201**   | Created                    | No Content                       |
| **400**   | Bad Request                | No Content                       |
| **401**   | Unauthorized               | No Content                       |
| **403**   | Forbidden                  | No Content                       |
| **404**   | Not Found                  | No Content                       |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - ResourceGroup

## Returns all Resource Groups.

    GET /resourceGroup

### Description

Returns all Resource Groups with all
details.

### Responses

| HTTP Code | Description          | Schema                                       |
| --------- | -------------------- | -------------------------------------------- |
| **200**   | Successful operation | \< [ResourceGroup](#_resourcegroup) \> array |
| **401**   | Unauthorized         | No Content                                   |
| **403**   | Forbidden            | No Content                                   |

### Produces

  - `application/json`

### Tags

  - ResourceGroup

## Returns a Resource Group.

    GET /resourceGroup/{id}

### Description

Returns the Resource Group with the given ID. Returns all its details.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of ResourceGroup</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description             | Schema                           |
| --------- | ----------------------- | -------------------------------- |
| **200**   | Successful operation    | [ResourceGroup](#_resourcegroup) |
| **401**   | Unauthorized            | No Content                       |
| **403**   | Forbidden               | No Content                       |
| **404**   | ResourceGroup not found | No Content                       |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - ResourceGroup

## Deletes a Resource Group.

    DELETE /resourceGroup/{id}

### Description

Deletes the Resource Group that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of ResourceGroup</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                        | Schema     |
| --------- | ---------------------------------- | ---------- |
| **200**   | ResourceGroup deleted successfully | string     |
| **204**   | No Content                         | No Content |
| **400**   | Bad Request                        | string     |
| **401**   | Unauthorized                       | No Content |
| **403**   | Forbidden                          | No Content |
| **404**   | ResourceGroup not found            | string     |

### Produces

  - `/`

### Tags

  - ResourceGroup

## Updates a ResourceGroup.

    PATCH /resourceGroup/{id}

### Description

Updates an existing ResourceGroup.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of ResourceGroup</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>ResourceGroup that needs to be updated.</p></td>
<td><p><a href="#_resourcegroup">ResourceGroup</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description             | Schema                           |
| --------- | ----------------------- | -------------------------------- |
| **200**   | ResourceGroup Update OK | [ResourceGroup](#_resourcegroup) |
| **204**   | No Content              | No Content                       |
| **400**   | Bad Request             | string                           |
| **401**   | Unauthorized            | No Content                       |
| **403**   | Forbidden               | No Content                       |
| **404**   | ResourceGroup not found | string                           |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - ResourceGroup

## Executes given command on the given VDU.

    PUT /runtime/{id}/action/execute

### Description

Executes the given command on the VDU with the given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Body</strong></p></td>
<td><p><strong>commandExecutionBody</strong><br />
<em>required</em></p></td>
<td><p>Contains command to be executed and a flag if for the completion of the execution should be awaited</p></td>
<td><p><a href="#_executeoninstance_commandexecutionbody">commandExecutionBody</a></p></td>
</tr>
</tbody>
</table>

**commandExecutionBody**

<table>
<colgroup>
<col style="width: 16%" />
<col style="width: 61%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>awaitCompletion</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>true</code></p></td>
<td><p>boolean</p></td>
</tr>
<tr class="even">
<td><p><strong>command</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;ls /&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | string     |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |
| **404**   | VDU not found.       | No Content |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - Runtime

## Starts the given VDU.

    PUT /runtime/{id}/action/start

### Description

Starts the VDU with the given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | No Content |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |
| **404**   | VDU/File not found.  | string     |

### Tags

  - Runtime

## Stops the given VDU.

    PUT /runtime/{id}/action/stop

### Description

Stops the VDU with the given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | No Content |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |
| **404**   | VDU/File not found.  | No Content |

### Tags

  - Runtime

## Uploads a file to a VDU.

    POST /runtime/{id}/file

### Description

Uploads a given file to the given filepath to the given VDU.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>FormData</strong></p></td>
<td><p><strong>file</strong><br />
<em>required</em></p></td>
<td><p>File which has to be uploaded to the Instance. Either this or the hostPath but not both can be provided.</p></td>
<td><p>file</p></td>
</tr>
<tr class="odd">
<td><p><strong>FormData</strong></p></td>
<td><p><strong>remotePath</strong><br />
<em>required</em></p></td>
<td><p>Absolute path where the file should go on the Instance</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | No Content |
| **400**   | Bad Request          | No Content |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |
| **404**   | VDU/Path not found.  | string     |

### Consumes

  - `multipart/form-data`

### Tags

  - Runtime

## Downloads a file from a VDU.

    GET /runtime/{id}/file

### Description

Download a file with the given filepath from the given VDU.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Body</strong></p></td>
<td><p><strong>fileDownloadBody</strong><br />
<em>required</em></p></td>
<td><p>Contains details of the file to be downloaded from the given Instance</p></td>
<td><p><a href="#_downloadfilefrominstance_filedownloadbody">fileDownloadBody</a></p></td>
</tr>
</tbody>
</table>

**fileDownloadBody**

<table>
<colgroup>
<col style="width: 16%" />
<col style="width: 61%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>path</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;/&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | file       |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |
| **404**   | VDU/File not found.  | string     |

### Consumes

  - `application/json`

### Produces

  - `multipart/form-data`

### Tags

  - Runtime

## Uploads a file to a VDU.

    POST /runtime/{id}/path

### Description

Uploads a given file from the host path to the given file path to the
given VDU.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Body</strong></p></td>
<td><p><strong>fileUploadBody</strong><br />
<em>required</em></p></td>
<td><p>Contains details of the file with the given path to upload to the Instance</p></td>
<td><p><a href="#_uploadfiletoinstancebypath_fileuploadbody">fileUploadBody</a></p></td>
</tr>
</tbody>
</table>

**fileUploadBody**

<table>
<colgroup>
<col style="width: 16%" />
<col style="width: 61%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>hostPath</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;/&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>remotePath</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;/&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | No Content |
| **400**   | Bad request          | No Content |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |
| **404**   | VDU/Path not found.  | string     |

### Consumes

  - `application/json`

### Tags

  - Runtime

## Deploys a Tosca template.

    POST /tosca

### Description

The TOSCA template defines VDUs, Networks and the PoPs where to allocate
the virtual resources

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>TOSCA formatted template</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema                           |
| --------- | -------------------- | -------------------------------- |
| **200**   | Successful operation | [ResourceGroup](#_resourcegroup) |
| **400**   | Bad Request          | string                           |
| **401**   | Unauthorized         | string                           |
| **403**   | Forbidden            | string                           |
| **404**   | Not Found            | string                           |

### Consumes

  - `text/yaml`

### Produces

  - `application/json`

### Tags

  - TOSCA

## Allocates resources in the target cloud.

    POST /vdu

### Description

Allocates resources defined as a VDU in the cloud to be deployed in the
target cloud environment. It instantiates computing resources, deploys
artifacts on them and manages the their lifecycle

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>Defintion of a VDU which defines resources that have to be deployed</p></td>
<td><p><a href="#_vdu">VDU</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description       | Schema       |
| --------- | ----------------- | ------------ |
| **200**   | VDU allocation OK | [VDU](#_vdu) |
| **201**   | Created           | No Content   |
| **400**   | Bad Request       | No Content   |
| **401**   | Unauthorized      | No Content   |
| **403**   | Forbidden         | No Content   |
| **404**   | Not Found         | string       |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - VDU

## Returns all VDUs.

    GET /vdu

### Description

Returns all VDUs with all its details.

### Responses

| HTTP Code | Description          | Schema                   |
| --------- | -------------------- | ------------------------ |
| **200**   | Successful operation | \< [VDU](#_vdu) \> array |
| **401**   | Unauthorized         | No Content               |
| **403**   | Forbidden            | No Content               |
| **404**   | Not Found            | No Content               |

### Produces

  - `application/json`

### Tags

  - VDU

## Returns a VDU.

    GET /vdu/{id}

### Description

Returns the VDU with the given ID. Returns all its details.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema       |
| --------- | -------------------- | ------------ |
| **200**   | Successful operation | [VDU](#_vdu) |
| **401**   | Unauthorized         | No Content   |
| **403**   | Forbidden            | No Content   |
| **404**   | VDU not found.       | string       |

### Produces

  - `application/json`

### Tags

  - VDU

## Terminates a VDU.

    DELETE /vdu/{id}

### Description

Terminates the VDU that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                 | Schema     |
| --------- | --------------------------- | ---------- |
| **200**   | VDU terminated successfully | string     |
| **204**   | No Content                  | No Content |
| **400**   | Bad Request                 | string     |
| **401**   | Unauthorized                | No Content |
| **403**   | Forbidden                   | No Content |
| **404**   | VDU not found               | string     |

### Produces

  - `/`

### Tags

  - VDU

## Updates a VDU.

    PATCH /vdu/{id}

### Description

Updates an already deployed VDU.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of VDU</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>VDU object that needs to be updated.</p></td>
<td><p><a href="#_vdu">VDU</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description   | Schema       |
| --------- | ------------- | ------------ |
| **200**   | VDU Update OK | [VDU](#_vdu) |
| **400**   | Bad Request   | No Content   |
| **401**   | Unauthorized  | No Content   |
| **403**   | Forbidden     | No Content   |
| **404**   | VDU not found | No Content   |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - VDU

## Registers the worker and saves the information.

    POST /workers

### Description

This registers a worker with the information provided.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>body</strong><br />
<em>required</em></p></td>
<td><p>worker in a json</p></td>
<td><p><a href="#_worker">Worker</a></p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema             |
| --------- | -------------------- | ------------------ |
| **200**   | Worker registered OK | [Worker](#_worker) |
| **201**   | Created              | No Content         |
| **400**   | Bad Request          | No Content         |
| **401**   | Unauthorized         | No Content         |
| **403**   | Forbidden            | No Content         |
| **404**   | Not Found/keys       | No Content         |

### Consumes

  - `application/json`

### Tags

  - Worker

## Returns all registered workers

    GET /workers

### Responses

| HTTP Code | Description          | Schema                         |
| --------- | -------------------- | ------------------------------ |
| **200**   | Successful operation | \< [Worker](#_worker) \> array |
| **401**   | Unauthorized         | No Content                     |
| **403**   | Forbidden            | No Content                     |

### Produces

  - `application/json`

### Tags

  - Worker

## Creates a new worker.

    POST /workers/create

### Description

Receives a package that can be used for creating a new worker.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Body</strong></p></td>
<td><p><strong>workerFromVDU</strong><br />
<em>required</em></p></td>
<td><p>Body to create Worker from VDU</p></td>
<td><p><a href="#_createworker_workerfromvdu">workerFromVDU</a></p></td>
</tr>
</tbody>
</table>

**workerFromVDU**

<table>
<colgroup>
<col style="width: 16%" />
<col style="width: 61%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>type</strong><br />
<em>required</em></p></td>
<td><p>The types of the worker.</p></td>
<td><p>&lt; string &gt; array</p></td>
</tr>
<tr class="even">
<td><p><strong>vduId</strong><br />
<em>required</em></p></td>
<td><p>The id of vdu from which to create the worker.</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description     | Schema             |
| --------- | --------------- | ------------------ |
| **200**   | Workers created | [Worker](#_worker) |
| **201**   | Created         | No Content         |
| **400**   | Bad Request     | string             |
| **401**   | Unauthorized    | No Content         |
| **403**   | Forbidden       | No Content         |
| **404**   | Not Found       | string             |

### Consumes

  - `application/json`

### Produces

  - `application/json`

### Tags

  - Worker

## Deletes a Worker.

    DELETE /workers/{id}

### Description

Deletes the Worker that matches with a given ID.

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Worker</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description                 | Schema     |
| --------- | --------------------------- | ---------- |
| **200**   | Worker deleted successfully | string     |
| **204**   | No Content                  | No Content |
| **400**   | Bad Request                 | string     |
| **401**   | Unauthorized                | No Content |
| **403**   | Forbidden                   | No Content |
| **404**   | Worker not found            | string     |

### Produces

  - `/`

### Tags

  - Worker

## Sets up the specified worker to install the specified type of adapter.

    GET /workers/{id}/{type}

### Parameters

<table>
<colgroup>
<col style="width: 11%" />
<col style="width: 16%" />
<col style="width: 50%" />
<col style="width: 22%" />
</colgroup>
<thead>
<tr class="header">
<th>Type</th>
<th>Name</th>
<th>Description</th>
<th>Schema</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Path</strong></p></td>
<td><p><strong>id</strong><br />
<em>required</em></p></td>
<td><p>ID of Worker</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>Path</strong></p></td>
<td><p><strong>type</strong><br />
<em>required</em></p></td>
<td><p>type of adapter</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

### Responses

| HTTP Code | Description          | Schema     |
| --------- | -------------------- | ---------- |
| **200**   | Successful operation | string     |
| **401**   | Unauthorized         | No Content |
| **403**   | Forbidden            | No Content |

### Produces

  - `application/json`

### Tags

  - Worker
