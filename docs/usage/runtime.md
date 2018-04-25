## Listing resources

* List PoPs: 
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/pop
```

* List Networks:
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/network
```

* List VDUs:
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/vdu
```

* List Resource Groups:
```bash
curl -X GET -H "Accept: application/json" localhost:8180/v1/resourceGroup
```

## Executing Runtime Operations

* Download a file from an instance:
```bash
curl -X GET -H "accept: multipart/form-data" -H "content-type: application/json" -d '{"path":"/PATH_TO_FILE"}' "http://localhost:8180/v1/runtime/{VDU_ID}/file"
```

* Upload a file from the given path:
```bash
curl -X POST -H "content-type: application/json" -d '{ "remotePath": "{PATH_ON_INSTANCE}", "hostPath": "{PATH_ON_HOST}"}' "http://localhost:8180/v1/runtime/{VDU_ID}/path"
```

* Upload a file that is passed within the request:
```bash
curl -X POST -H "content-type: multipart/form-data" -F "remotePath=/" -F "file=@{PATH_TO_FILE}" "http://localhost:8180/v1/runtime/{VDU_ID}/file"
```

* Execute a command on the instance:
```bash
curl -X PUT -H "accept: application/json" -H "content-type: application/json" -d '{"command":"ls","awaitCompletion":"true"}' "http://localhost:8180/v1/runtime/{VDU_ID}/action/execute"
```

* Start instance
```bash
curl -X PUT "http://localhost:8180/v1/runtime/{VDU_ID}/action/start"
```

* Stop instance
```bash
curl -X PUT "http://localhost:8180/v1/runtime/{VDU_ID}/action/stop"
```
