# Definitions

## Adapter

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
<td><p><strong>endpoint</strong><br />
<em>required</em></p></td>
<td><p>The endpoint where the Adapter is reachable.<br />
<strong>Example</strong> : <code>&quot;localhost:50052&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p>Identifier for the Adapter.</p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>type</strong><br />
<em>required</em></p></td>
<td><p>The type of virtualization technology, that the adapter is designed to connect to.<br />
<strong>Example</strong> : <code>&quot;docker-compose&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

## AuthCredentials

Everything needed for accessing a machine using SSH

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
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>key</strong><br />
<em>optional</em></p></td>
<td><p>The name of the key saved in EPM, which can be used to execute runtime operations on this VDU.<br />
<strong>Example</strong> : <code>&quot;vdu_1-key&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>passphrase</strong><br />
<em>optional</em></p></td>
<td><p>This is the Passphrase of the Key provided for connecting to the Worker.</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>password</strong><br />
<em>optional</em></p></td>
<td><p>This is the password of the user, which can be left blank if no password is needed.</p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>port</strong><br />
<em>optional</em></p></td>
<td><p>The ssh port of the worker, where the EPM can reach it.</p></td>
<td><p>integer</p></td>
</tr>
<tr class="even">
<td><p><strong>user</strong><br />
<em>required</em></p></td>
<td><p>This is the user, which the EPM will use when trying to ssh in to the Worker.<br />
<strong>Example</strong> : <code>&quot;ubuntu&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

## Cluster

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
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>master</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p><a href="#_worker">Worker</a></p></td>
</tr>
<tr class="odd">
<td><p><strong>nodes</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p>&lt; <a href="#_worker">Worker</a> &gt; array</p></td>
</tr>
<tr class="even">
<td><p><strong>resourceGroupId</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>type</strong><br />
<em>required</em></p></td>
<td><p>Type of the Cluster.<br />
<strong>Example</strong> : <code>&quot;kubernetes&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

## Event

An event contains certain life cycle information of the VDU at a
specific time.

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
<td><p><strong>description</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;testEvent1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;1234-abcd&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>timestamp</strong><br />
<em>required</em></p></td>
<td><p>The recorded time of the Event.</p></td>
<td><p>string (string)</p></td>
</tr>
</tbody>
</table>

## Key

A private key for executing commands on a worker.

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
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p>The identifier of the Key</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>key</strong><br />
<em>required</em></p></td>
<td><p>This is the key itself as String.</p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>name</strong><br />
<em>required</em></p></td>
<td><p>The name of the key. This will be used for referencing the Key in a Worker.<br />
<strong>Example</strong> : <code>&quot;key1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

## KeyValuePair

This entity is a Key-Value pair for storing metadata contained in other
entities

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
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;1234-abcd&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>key</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;testKey1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>value</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;testValue1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

## Network

This entity defines the network connectivity and details where the VDUs
are connected to.

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
<td><p><strong>cidr</strong><br />
<em>required</em></p></td>
<td><p><strong>Example</strong> : <code>&quot;192.168.1.1/24&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p>The identifier of the Network in the EPM.<br />
<strong>Example</strong> : <code>&quot;1234-abcd&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>name</strong><br />
<em>required</em></p></td>
<td><p>The name of the network, this should correspond to the name of the network in the virtualization technology.<br />
<strong>Example</strong> : <code>&quot;testNetwork1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>networkId</strong><br />
<em>required</em></p></td>
<td><p>The id of the Network in the virtualization technology.<br />
<strong>Example</strong> : <code>&quot;1234-abcd&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>poPName</strong><br />
<em>required</em></p></td>
<td><p>The PoP where the Network was created.</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>

## PoP

This entity contains information about the Point-of-Presence (PoP)

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
<td><p><strong>accessInfo</strong><br />
<em>required</em></p></td>
<td><p>Authentication credentials for accessing the PoP. Examples may include those to support different authentication schemes, e.g. OAuth, Token, etc.</p></td>
<td><p>&lt; <a href="#_keyvaluepair">KeyValuePair</a> &gt; array</p></td>
</tr>
<tr class="even">
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p>Identifier of the PoP</p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>interfaceEndpoint</strong><br />
<em>required</em></p></td>
<td><p>Information about the interface endpoint. An example is a URL.<br />
<strong>Example</strong> : <code>&quot;localhost&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>interfaceInfo</strong><br />
<em>required</em></p></td>
<td><p>Information about the interface(s) to the PoP, including PoP provider type, API version, and protocol type.<br />
<strong>Example</strong> : <code>&quot;[{&quot;key&quot;:&quot;type&quot;,&quot;value&quot;:&quot;docker&quot;}]&quot;</code></p></td>
<td><p>&lt; <a href="#_keyvaluepair">KeyValuePair</a> &gt; array</p></td>
</tr>
<tr class="odd">
<td><p><strong>name</strong><br />
<em>required</em></p></td>
<td><p>Human-readable identifier of this PoP information element<br />
<strong>Example</strong> : <code>&quot;testPoPName&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>status</strong><br />
<em>optional</em></p></td>
<td><p>Representing the status of a PoP (INACTIVE, CONFIGURE, ACTIVE)</p></td>
<td><p>enum (configure, active, inactive)</p></td>
</tr>
</tbody>
</table>

## ResourceGroup

A Resource Group defines a bundle of VDUs and virtual networks which
belongs together. It includes also the Point-of-Presences where the
virtual resources have to be allocated.

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
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p>The identifier of the Resource Group in the EPM.</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>name</strong><br />
<em>required</em></p></td>
<td><p>The name of the Resource Group.<br />
<strong>Example</strong> : <code>&quot;testResourceGroupName1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>networks</strong><br />
<em>optional</em></p></td>
<td><p>The Networks in the Resource Group.</p></td>
<td><p>&lt; <a href="#_network">Network</a> &gt; array</p></td>
</tr>
<tr class="even">
<td><p><strong>vdus</strong><br />
<em>required</em></p></td>
<td><p>The VDUs of which this Resource Group consists of.</p></td>
<td><p>&lt; <a href="#_vdu">VDU</a> &gt; array</p></td>
</tr>
</tbody>
</table>

## VDU

A Virtual Deployment Unit (VDU) describes the capabilities of
virtualized computing (Containers, VMs) and networking resources.

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
<td><p><strong>computeId</strong><br />
<em>required</em></p></td>
<td><p>The identifier of the deployed VDU in the virtualization technology.</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>events</strong><br />
<em>optional</em></p></td>
<td><p>A list of events recorded for this VDU.</p></td>
<td><p>&lt; <a href="#_event">Event</a> &gt; array</p></td>
</tr>
<tr class="odd">
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td><p>The identifier of the VDU in the EPM.</p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>imageName</strong><br />
<em>required</em></p></td>
<td><p>The name of the image used for the VDU.<br />
<strong>Example</strong> : <code>&quot;testImage1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>ip</strong><br />
<em>required</em></p></td>
<td><p>The IP assigned to the VDU.<br />
<strong>Example</strong> : <code>&quot;172.0.0.1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>key</strong><br />
<em>optional</em></p></td>
<td><p>The name of the key saved in EPM, which can be used to execute runtime operations on this VDU.<br />
<strong>Example</strong> : <code>&quot;vdu_1-key&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>metadata</strong><br />
<em>optional</em></p></td>
<td><p>More detailed information about the VDU in a Key-Value pair format.</p></td>
<td><p>&lt; <a href="#_keyvaluepair">KeyValuePair</a> &gt; array</p></td>
</tr>
<tr class="even">
<td><p><strong>name</strong><br />
<em>required</em></p></td>
<td><p>The name of the VDU.<br />
<strong>Example</strong> : <code>&quot;testVdu1&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>netName</strong><br />
<em>required</em></p></td>
<td><p>The name of the network to which the VDU is associated with.<br />
<strong>Example</strong> : <code>&quot;testNetworkName&quot;</code></p></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>poPName</strong><br />
<em>required</em></p></td>
<td><p>The name of the PoP where the VDU is deployed.</p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>status</strong><br />
<em>optional</em></p></td>
<td><p>The status of the virtualized compute resource.</p></td>
<td><p>enum (initializing, initialized, deploying, deployed, running, undeploying, undeployed, error)</p></td>
</tr>
</tbody>
</table>

## Worker

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
<td><p><strong>authCredentials</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p><a href="#_authcredentials">AuthCredentials</a></p></td>
</tr>
<tr class="even">
<td><p><strong>epmIp</strong><br />
<em>optional</em></p></td>
<td><p>This is the IP where the EPM is reachable for the Worker. This is needed because the Worker has to be able to reach the EPM for registering adapters.</p></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>id</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p>string</p></td>
</tr>
<tr class="even">
<td><p><strong>ip</strong><br />
<em>optional</em></p></td>
<td></td>
<td><p>string</p></td>
</tr>
<tr class="odd">
<td><p><strong>type</strong><br />
<em>optional</em></p></td>
<td><p>The types which this worker supports at the moment when this information is requested.</p></td>
<td><p>&lt; string &gt; array</p></td>
</tr>
<tr class="even">
<td><p><strong>vduId</strong><br />
<em>optional</em></p></td>
<td><p>The vduId if the worker was created from a vdu.</p></td>
<td><p>string</p></td>
</tr>
</tbody>
</table>
