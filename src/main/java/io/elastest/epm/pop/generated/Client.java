package io.elastest.epm.pop.generated;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client.proto

public final class Client {
  private Client() {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_FileMessage_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_FileMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_ResourceIdentifier_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResourceIdentifier_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor internal_static_RuntimeMessage_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RuntimeMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor internal_static_StringResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StringResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_StartStopMessage_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StartStopMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor internal_static_Empty_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Empty_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor internal_static_Status_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Status_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor internal_static_Auth_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Auth_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_ResourceGroupProto_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResourceGroupProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_ResourceGroupProto_PoP_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResourceGroupProto_PoP_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_ResourceGroupProto_MetadataEntry_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResourceGroupProto_MetadataEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_ResourceGroupProto_Network_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResourceGroupProto_Network_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_ResourceGroupProto_VDU_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResourceGroupProto_VDU_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

  static {
    String[] descriptorData = {
      "\n\014client.proto\",\n\013FileMessage\022\014\n\004file\030\001 "
          + "\001(\014\022\017\n\007options\030\002 \003(\t\")\n\022ResourceIdentifi"
          + "er\022\023\n\013resource_id\030\001 \001(\t\"E\n\016RuntimeMessag"
          + "e\022\023\n\013resource_id\030\001 \001(\t\022\020\n\010property\030\002 \003(\t"
          + "\022\014\n\004file\030\003 \001(\014\"\"\n\016StringResponse\022\020\n\010resp"
          + "onse\030\001 \001(\t\"(\n\020StartStopMessage\022\024\n\014contai"
          + "ner_id\030\001 \001(\t\"\007\n\005Empty\"i\n\006Status\022%\n\006statu"
          + "s\030\001 \001(\0162\025.Status.ServingStatus\"8\n\rServin"
          + "gStatus\022\r\n\tCONFIGURE\020\000\022\n\n\006ACTIVE\020\001\022\014\n\010IN"
          + "ACTIVE\020\002\"M\n\004Auth\022\020\n\010auth_url\030\001 \001(\t\022\020\n\010us",
      "ername\030\002 \001(\t\022\020\n\010password\030\003 \001(\t\022\017\n\007projec"
          + "t\030\004 \001(\t\"\346\003\n\022ResourceGroupProto\022\014\n\004name\030\001"
          + " \001(\t\022%\n\004pops\030\002 \003(\0132\027.ResourceGroupProto."
          + "PoP\022-\n\010networks\030\003 \003(\0132\033.ResourceGroupPro"
          + "to.Network\022%\n\004vdus\030\004 \003(\0132\027.ResourceGroup"
          + "Proto.VDU\032.\n\003PoP\022\014\n\004name\030\001 \001(\t\022\031\n\021interf"
          + "aceEndpoint\030\002 \001(\t\032+\n\rMetadataEntry\022\013\n\003ke"
          + "y\030\001 \001(\t\022\r\n\005value\030\002 \001(\t\032I\n\007Network\022\014\n\004nam"
          + "e\030\001 \001(\t\022\017\n\007poPName\030\002 \001(\t\022\014\n\004cidr\030\003 \001(\t\022\021"
          + "\n\tnetworkId\030\004 \001(\t\032\234\001\n\003VDU\022\014\n\004name\030\001 \001(\t\022",
      "\021\n\timageName\030\002 \001(\t\022\017\n\007netName\030\003 \001(\t\022\017\n\007p"
          + "oPName\030\004 \001(\t\022\021\n\tcomputeId\030\005 \001(\t\022\n\n\002ip\030\006 "
          + "\001(\t\0223\n\010metadata\030\007 \003(\0132!.ResourceGroupPro"
          + "to.MetadataEntry2\202\004\n\020OperationHandler\022-\n"
          + "\006Create\022\014.FileMessage\032\023.ResourceGroupPro"
          + "to\"\000\022\'\n\006Remove\022\023.ResourceIdentifier\032\006.Em"
          + "pty\"\000\022.\n\rStopContainer\022\023.ResourceIdentif"
          + "ier\032\006.Empty\"\000\022@\n\026CheckIfContainerExists\022"
          + "\023.ResourceIdentifier\032\017.StringResponse\"\000\022"
          + "A\n\027CheckIfContainerRunning\022\023.ResourceIde",
      "ntifier\032\017.StringResponse\"\000\022/\n\016StartConta"
          + "iner\022\023.ResourceIdentifier\032\006.Empty\"\000\0224\n\016E"
          + "xecuteCommand\022\017.RuntimeMessage\032\017.StringR"
          + "esponse\"\000\022/\n\014DownloadFile\022\017.RuntimeMessa"
          + "ge\032\014.FileMessage\"\000\022\'\n\nUploadFile\022\017.Runti"
          + "meMessage\032\006.Empty\"\000\022 \n\013CheckStatus\022\006.Emp"
          + "ty\032\007.Status\"\000B\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
        descriptorData, new com.google.protobuf.Descriptors.FileDescriptor[] {}, assigner);
    internal_static_FileMessage_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_FileMessage_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_FileMessage_descriptor,
            new String[] {
              "File", "Options",
            });
    internal_static_ResourceIdentifier_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_ResourceIdentifier_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_ResourceIdentifier_descriptor,
            new String[] {
              "ResourceId",
            });
    internal_static_RuntimeMessage_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_RuntimeMessage_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_RuntimeMessage_descriptor,
            new String[] {
              "ResourceId", "Property", "File",
            });
    internal_static_StringResponse_descriptor = getDescriptor().getMessageTypes().get(3);
    internal_static_StringResponse_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_StringResponse_descriptor,
            new String[] {
              "Response",
            });
    internal_static_StartStopMessage_descriptor = getDescriptor().getMessageTypes().get(4);
    internal_static_StartStopMessage_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_StartStopMessage_descriptor,
            new String[] {
              "ContainerId",
            });
    internal_static_Empty_descriptor = getDescriptor().getMessageTypes().get(5);
    internal_static_Empty_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_Empty_descriptor, new String[] {});
    internal_static_Status_descriptor = getDescriptor().getMessageTypes().get(6);
    internal_static_Status_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_Status_descriptor,
            new String[] {
              "Status",
            });
    internal_static_Auth_descriptor = getDescriptor().getMessageTypes().get(7);
    internal_static_Auth_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_Auth_descriptor,
            new String[] {
              "AuthUrl", "Username", "Password", "Project",
            });
    internal_static_ResourceGroupProto_descriptor = getDescriptor().getMessageTypes().get(8);
    internal_static_ResourceGroupProto_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_ResourceGroupProto_descriptor,
            new String[] {
              "Name", "Pops", "Networks", "Vdus",
            });
    internal_static_ResourceGroupProto_PoP_descriptor =
        internal_static_ResourceGroupProto_descriptor.getNestedTypes().get(0);
    internal_static_ResourceGroupProto_PoP_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_ResourceGroupProto_PoP_descriptor,
            new String[] {
              "Name", "InterfaceEndpoint",
            });
    internal_static_ResourceGroupProto_MetadataEntry_descriptor =
        internal_static_ResourceGroupProto_descriptor.getNestedTypes().get(1);
    internal_static_ResourceGroupProto_MetadataEntry_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_ResourceGroupProto_MetadataEntry_descriptor,
            new String[] {
              "Key", "Value",
            });
    internal_static_ResourceGroupProto_Network_descriptor =
        internal_static_ResourceGroupProto_descriptor.getNestedTypes().get(2);
    internal_static_ResourceGroupProto_Network_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_ResourceGroupProto_Network_descriptor,
            new String[] {
              "Name", "PoPName", "Cidr", "NetworkId",
            });
    internal_static_ResourceGroupProto_VDU_descriptor =
        internal_static_ResourceGroupProto_descriptor.getNestedTypes().get(3);
    internal_static_ResourceGroupProto_VDU_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_ResourceGroupProto_VDU_descriptor,
            new String[] {
              "Name", "ImageName", "NetName", "PoPName", "ComputeId", "Ip", "Metadata",
            });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
