## Protobuffer compiler installation and usage procedure

**1) Install google protobuf compiler**

The best way to do that is by downloading it from here:

https://github.com/google/protobuf/releases

**2) Download java plugin**

The java plugin can be downloaded from here : https://github.com/grpc/grpc-java

Then the plugin has to be built using this command: 
```bash
../gradlew java_pluginExecutable
```

**3) Compile the protocol buffers**

Inside the **compiler** folder of **gprc-java** run the following command:

```bash
protoc --plugin=protoc-gen-grpc-java=build/exe/java_plugin/protoc-gen-grpc-java --grpc-java_out=output --proto_path="protos" protos/client.proto 

``` 

This will generate the **service** classes. 
The **message** classes are created from the **client.proto** file using the protocol buffer plugin for gradle.
They are generated on each build in the **src/generated-protobuf** folder. 

