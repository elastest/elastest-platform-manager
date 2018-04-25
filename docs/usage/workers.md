# How to use workers

**Note:** Only **Ubuntu** VMs are supported as workers at the moment. The worker registration has been tested with
 Ubuntu 14.04 and Ubuntu 16.04. 
 
This page will show you how the EPM can make use of Workers for deploying packages in a few simple steps:

### Register Key 

Before registering your worker you need to provide the EPM with the private key, so that the EPM can setup the adapters on the worker. 
Since the same key might be used in more than one workers the EPM stores the Keys, before using them. 

Providing the private key directly in the command line is not very practical therefore it makes sense to create a *json* file
with the following structure:

```json
// Example file key.json
{
"name":"mykey", 
"key":"-----BEGIN RSA PRIVATE KEY-----\n
       Proc-Type: 4,ENCRYPTED\n
       DEK-Info: ... \n
       \n
       <KEY>\n
       -----END RSA PRIVATE KEY-----"
}

```

After that you can register your key in the following way:

```bash
curl -X POST -H "Content-Type: application/json" -d @key.json localhost:8180/v1/keys
```

### Register Worker

Once the key for the worker is saved in the EPM we can register the worker itself. This can be done by executing the following command:

```bash
curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"ip":"$WORKER_IP","user":"ubuntu","passphrase":"","epmIp":"$EPM_IP","password":"","keyname":"mykey"}' localhost:8180/v1/workers
```

The **keyname** is the name specified in the *key.json* when registering the key. The EPM ip should also be specified to enable the 
newly started adapters on the worker to register to the EPM. 

**Note:** If the worker should send system statistics to the Elastest Monitoring Platform (EMP), then before starting the 
EPM, you need to enable this in the **application.properties**(src/main/resources/application.properties) file.
 Here is an example of how to set the properties:

```properties
#########################################
####### Elastest Configuration ##########
#########################################

elastest.emp.enabled = true
elastest.emp.endpoint = localhost # KAFKA IP
elastest.emp.port = 9092
elastest.emp.topic = user-1-emp_worker
elastest.emp.seriesName = sys-stats
```

### Worker setup

Now that the worker is successfully registered in EPM it is available to setup the EPM adapters on the worker. 

Setting up the **docker-compose** adapter can be executed by running the following command:

```bash
curl -i localhost:8180/v1/workers/${WORKER_ID}/docker-compose
```

Using the private key provided earlier the EPM will do the following:
1) Install Docker and Docker-Compose if not already available
2) Pull and start the image of the EPM Docker-Compose Adapter
3) Register as a PoP the Docker-Compose environment of the worker

Setting up the **docker** environment can be executed by running the following command:

```bash
curl -i localhost:8180/v1/workers/${WORKER_ID}/docker
```

**Note:** This will not start the docker adapter. 

Using the private key provided earlier the EPM will do the following:
1) Install docker if not already available
2) Activate the Remote API option in docker, so that the adapter can reach the docker environment remotely
3) Create a PoP for the registered Docker Environment