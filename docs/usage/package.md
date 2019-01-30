# Creating and Launching a package

Before using the available [adapters][adapters] you need to properly create a package, by following these steps:

1. Create a metadata file

The **Metadata.yaml** should look like this:

```yaml
name: package #Here you can specify the name of the package
type: docker-compose
pop: pop-name # OPTIONAL: specify the name of the pop for deploying the package
```

2. Create the descriptor for the desired Virtual Infrastructure

Here are examples of the supported descriptors:
* [Docker-Compose file](https://docs.docker.com/compose/compose-file/)
* [Resource Group (interal format for docker)](../../descriptors/json/resource_group.json)
* [Ansible play (OpenStack / AWS)](https://github.com/tub-elastest/epm-adapter-ansible/blob/master/plays/openstack_launch.yml)

3. Create a **tar** package with the following structure

```bash
- metadata.yaml
- docker-compose.yml
- key # Optional for Ansible packages
```

You can create the **tar** file using the following command

```bash
tar -cvf package.tar *
```

4. Send the package to the EPM

```bash
curl -X POST http://localhost:8180/v1/packages -H "Accept: application/json" -v -F file=@package.tar
```

This will forward the package to the correct adapter. The EPM will return a Resource-Group as json. 

5. Executing runtime commands

You can find the available runtime commands [here](runtime.md).

6. Stopping the package

After you are done using it you can also stop the package.
You have to replace the ID with the Resource Group ID of the package.

```bash
curl -X DELETE http://localhost:8180/v1/packages/9915ceda-c1a1-4521-ad87-a1791b12002a -H "Accept: application/json"
```

7. (Optional) If you want to use images from a custom Docker Registry you need to specify the credentials for that registry in the **Metadata.yaml**
in the following way:

```yaml
docker_registry: localhost:5000 # Here specify the URL to the registry for registering to it
docker_username: user # Specify the username and password credentials for registering to it
docker_password: pass
```

Besides that in the compose file the user should specify the correct path of the image of the private registry.

[adapters]: adapters.md