package io.elastest.epm.pop.generated;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client.proto

public interface CreateClusterMessageOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:CreateClusterMessage)
    com.google.protobuf.MessageOrBuilder {

  /** <code>optional string master_ip = 1;</code> */
  String getMasterIp();
  /** <code>optional string master_ip = 1;</code> */
  com.google.protobuf.ByteString getMasterIpBytes();

  /** <code>repeated string nodes_ip = 2;</code> */
  java.util.List<String> getNodesIpList();
  /** <code>repeated string nodes_ip = 2;</code> */
  int getNodesIpCount();
  /** <code>repeated string nodes_ip = 2;</code> */
  String getNodesIp(int index);
  /** <code>repeated string nodes_ip = 2;</code> */
  com.google.protobuf.ByteString getNodesIpBytes(int index);

  /** <code>optional .Key key = 3;</code> */
  boolean hasKey();
  /** <code>optional .Key key = 3;</code> */
  Key getKey();
  /** <code>optional .Key key = 3;</code> */
  KeyOrBuilder getKeyOrBuilder();
}
