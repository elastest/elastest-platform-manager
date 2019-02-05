package io.elastest.epm.pop.generated;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client.proto

/**
 * Protobuf type {@code InstallMessage}
 */
public  final class InstallMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:InstallMessage)
    InstallMessageOrBuilder {
  // Use InstallMessage.newBuilder() to construct.
  private InstallMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private InstallMessage() {
    type_ = "";
    masterIp_ = "";
    nodesIp_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    metadata_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private InstallMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            String s = input.readStringRequireUtf8();

            type_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            masterIp_ = s;
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              nodesIp_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000004;
            }
            nodesIp_.add(s);
            break;
          }
          case 34: {
            Key.Builder subBuilder = null;
            if (key_ != null) {
              subBuilder = key_.toBuilder();
            }
            key_ = input.readMessage(Key.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(key_);
              key_ = subBuilder.buildPartial();
            }

            break;
          }
          case 42: {
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
              metadata_ = new java.util.ArrayList<MetadataEntry>();
              mutable_bitField0_ |= 0x00000010;
            }
            metadata_.add(
                input.readMessage(MetadataEntry.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        nodesIp_ = nodesIp_.getUnmodifiableView();
      }
      if (((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
        metadata_ = java.util.Collections.unmodifiableList(metadata_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return Client.internal_static_InstallMessage_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Client.internal_static_InstallMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            InstallMessage.class, Builder.class);
  }

  private int bitField0_;
  public static final int TYPE_FIELD_NUMBER = 1;
  private volatile Object type_;
  /**
   * <code>optional string type = 1;</code>
   */
  public String getType() {
    Object ref = type_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      type_ = s;
      return s;
    }
  }
  /**
   * <code>optional string type = 1;</code>
   */
  public com.google.protobuf.ByteString
      getTypeBytes() {
    Object ref = type_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      type_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MASTER_IP_FIELD_NUMBER = 2;
  private volatile Object masterIp_;
  /**
   * <code>optional string master_ip = 2;</code>
   */
  public String getMasterIp() {
    Object ref = masterIp_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      masterIp_ = s;
      return s;
    }
  }
  /**
   * <code>optional string master_ip = 2;</code>
   */
  public com.google.protobuf.ByteString
      getMasterIpBytes() {
    Object ref = masterIp_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      masterIp_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NODES_IP_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList nodesIp_;
  /**
   * <code>repeated string nodes_ip = 3;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getNodesIpList() {
    return nodesIp_;
  }
  /**
   * <code>repeated string nodes_ip = 3;</code>
   */
  public int getNodesIpCount() {
    return nodesIp_.size();
  }
  /**
   * <code>repeated string nodes_ip = 3;</code>
   */
  public String getNodesIp(int index) {
    return nodesIp_.get(index);
  }
  /**
   * <code>repeated string nodes_ip = 3;</code>
   */
  public com.google.protobuf.ByteString
      getNodesIpBytes(int index) {
    return nodesIp_.getByteString(index);
  }

  public static final int KEY_FIELD_NUMBER = 4;
  private Key key_;
  /**
   * <code>optional .Key key = 4;</code>
   */
  public boolean hasKey() {
    return key_ != null;
  }
  /**
   * <code>optional .Key key = 4;</code>
   */
  public Key getKey() {
    return key_ == null ? Key.getDefaultInstance() : key_;
  }
  /**
   * <code>optional .Key key = 4;</code>
   */
  public KeyOrBuilder getKeyOrBuilder() {
    return getKey();
  }

  public static final int METADATA_FIELD_NUMBER = 5;
  private java.util.List<MetadataEntry> metadata_;
  /**
   * <code>repeated .MetadataEntry metadata = 5;</code>
   */
  public java.util.List<MetadataEntry> getMetadataList() {
    return metadata_;
  }
  /**
   * <code>repeated .MetadataEntry metadata = 5;</code>
   */
  public java.util.List<? extends MetadataEntryOrBuilder> 
      getMetadataOrBuilderList() {
    return metadata_;
  }
  /**
   * <code>repeated .MetadataEntry metadata = 5;</code>
   */
  public int getMetadataCount() {
    return metadata_.size();
  }
  /**
   * <code>repeated .MetadataEntry metadata = 5;</code>
   */
  public MetadataEntry getMetadata(int index) {
    return metadata_.get(index);
  }
  /**
   * <code>repeated .MetadataEntry metadata = 5;</code>
   */
  public MetadataEntryOrBuilder getMetadataOrBuilder(
      int index) {
    return metadata_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, type_);
    }
    if (!getMasterIpBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, masterIp_);
    }
    for (int i = 0; i < nodesIp_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, nodesIp_.getRaw(i));
    }
    if (key_ != null) {
      output.writeMessage(4, getKey());
    }
    for (int i = 0; i < metadata_.size(); i++) {
      output.writeMessage(5, metadata_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, type_);
    }
    if (!getMasterIpBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, masterIp_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < nodesIp_.size(); i++) {
        dataSize += computeStringSizeNoTag(nodesIp_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getNodesIpList().size();
    }
    if (key_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getKey());
    }
    for (int i = 0; i < metadata_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, metadata_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof InstallMessage)) {
      return super.equals(obj);
    }
    InstallMessage other = (InstallMessage) obj;

    boolean result = true;
    result = result && getType()
        .equals(other.getType());
    result = result && getMasterIp()
        .equals(other.getMasterIp());
    result = result && getNodesIpList()
        .equals(other.getNodesIpList());
    result = result && (hasKey() == other.hasKey());
    if (hasKey()) {
      result = result && getKey()
          .equals(other.getKey());
    }
    result = result && getMetadataList()
        .equals(other.getMetadataList());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    hash = (37 * hash) + MASTER_IP_FIELD_NUMBER;
    hash = (53 * hash) + getMasterIp().hashCode();
    if (getNodesIpCount() > 0) {
      hash = (37 * hash) + NODES_IP_FIELD_NUMBER;
      hash = (53 * hash) + getNodesIpList().hashCode();
    }
    if (hasKey()) {
      hash = (37 * hash) + KEY_FIELD_NUMBER;
      hash = (53 * hash) + getKey().hashCode();
    }
    if (getMetadataCount() > 0) {
      hash = (37 * hash) + METADATA_FIELD_NUMBER;
      hash = (53 * hash) + getMetadataList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static InstallMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static InstallMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static InstallMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static InstallMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static InstallMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static InstallMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static InstallMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static InstallMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static InstallMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static InstallMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(InstallMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code InstallMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:InstallMessage)
      InstallMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Client.internal_static_InstallMessage_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Client.internal_static_InstallMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              InstallMessage.class, Builder.class);
    }

    // Construct using InstallMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getMetadataFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      type_ = "";

      masterIp_ = "";

      nodesIp_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      if (keyBuilder_ == null) {
        key_ = null;
      } else {
        key_ = null;
        keyBuilder_ = null;
      }
      if (metadataBuilder_ == null) {
        metadata_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
      } else {
        metadataBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return Client.internal_static_InstallMessage_descriptor;
    }

    public InstallMessage getDefaultInstanceForType() {
      return InstallMessage.getDefaultInstance();
    }

    public InstallMessage build() {
      InstallMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public InstallMessage buildPartial() {
      InstallMessage result = new InstallMessage(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.type_ = type_;
      result.masterIp_ = masterIp_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        nodesIp_ = nodesIp_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.nodesIp_ = nodesIp_;
      if (keyBuilder_ == null) {
        result.key_ = key_;
      } else {
        result.key_ = keyBuilder_.build();
      }
      if (metadataBuilder_ == null) {
        if (((bitField0_ & 0x00000010) == 0x00000010)) {
          metadata_ = java.util.Collections.unmodifiableList(metadata_);
          bitField0_ = (bitField0_ & ~0x00000010);
        }
        result.metadata_ = metadata_;
      } else {
        result.metadata_ = metadataBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof InstallMessage) {
        return mergeFrom((InstallMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(InstallMessage other) {
      if (other == InstallMessage.getDefaultInstance()) return this;
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
      }
      if (!other.getMasterIp().isEmpty()) {
        masterIp_ = other.masterIp_;
        onChanged();
      }
      if (!other.nodesIp_.isEmpty()) {
        if (nodesIp_.isEmpty()) {
          nodesIp_ = other.nodesIp_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureNodesIpIsMutable();
          nodesIp_.addAll(other.nodesIp_);
        }
        onChanged();
      }
      if (other.hasKey()) {
        mergeKey(other.getKey());
      }
      if (metadataBuilder_ == null) {
        if (!other.metadata_.isEmpty()) {
          if (metadata_.isEmpty()) {
            metadata_ = other.metadata_;
            bitField0_ = (bitField0_ & ~0x00000010);
          } else {
            ensureMetadataIsMutable();
            metadata_.addAll(other.metadata_);
          }
          onChanged();
        }
      } else {
        if (!other.metadata_.isEmpty()) {
          if (metadataBuilder_.isEmpty()) {
            metadataBuilder_.dispose();
            metadataBuilder_ = null;
            metadata_ = other.metadata_;
            bitField0_ = (bitField0_ & ~0x00000010);
            metadataBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMetadataFieldBuilder() : null;
          } else {
            metadataBuilder_.addAllMessages(other.metadata_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      InstallMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (InstallMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private Object type_ = "";
    /**
     * <code>optional string type = 1;</code>
     */
    public String getType() {
      Object ref = type_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        type_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      Object ref = type_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public Builder setType(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public Builder setTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      type_ = value;
      onChanged();
      return this;
    }

    private Object masterIp_ = "";
    /**
     * <code>optional string master_ip = 2;</code>
     */
    public String getMasterIp() {
      Object ref = masterIp_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        masterIp_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string master_ip = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMasterIpBytes() {
      Object ref = masterIp_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        masterIp_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string master_ip = 2;</code>
     */
    public Builder setMasterIp(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      masterIp_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string master_ip = 2;</code>
     */
    public Builder clearMasterIp() {
      
      masterIp_ = getDefaultInstance().getMasterIp();
      onChanged();
      return this;
    }
    /**
     * <code>optional string master_ip = 2;</code>
     */
    public Builder setMasterIpBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      masterIp_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList nodesIp_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureNodesIpIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        nodesIp_ = new com.google.protobuf.LazyStringArrayList(nodesIp_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getNodesIpList() {
      return nodesIp_.getUnmodifiableView();
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public int getNodesIpCount() {
      return nodesIp_.size();
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public String getNodesIp(int index) {
      return nodesIp_.get(index);
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public com.google.protobuf.ByteString
        getNodesIpBytes(int index) {
      return nodesIp_.getByteString(index);
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public Builder setNodesIp(
        int index, String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureNodesIpIsMutable();
      nodesIp_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public Builder addNodesIp(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureNodesIpIsMutable();
      nodesIp_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public Builder addAllNodesIp(
        Iterable<String> values) {
      ensureNodesIpIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, nodesIp_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public Builder clearNodesIp() {
      nodesIp_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string nodes_ip = 3;</code>
     */
    public Builder addNodesIpBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureNodesIpIsMutable();
      nodesIp_.add(value);
      onChanged();
      return this;
    }

    private Key key_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        Key, Key.Builder, KeyOrBuilder> keyBuilder_;
    /**
     * <code>optional .Key key = 4;</code>
     */
    public boolean hasKey() {
      return keyBuilder_ != null || key_ != null;
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    public Key getKey() {
      if (keyBuilder_ == null) {
        return key_ == null ? Key.getDefaultInstance() : key_;
      } else {
        return keyBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    public Builder setKey(Key value) {
      if (keyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        key_ = value;
        onChanged();
      } else {
        keyBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    public Builder setKey(
        Key.Builder builderForValue) {
      if (keyBuilder_ == null) {
        key_ = builderForValue.build();
        onChanged();
      } else {
        keyBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    public Builder mergeKey(Key value) {
      if (keyBuilder_ == null) {
        if (key_ != null) {
          key_ =
            Key.newBuilder(key_).mergeFrom(value).buildPartial();
        } else {
          key_ = value;
        }
        onChanged();
      } else {
        keyBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    public Builder clearKey() {
      if (keyBuilder_ == null) {
        key_ = null;
        onChanged();
      } else {
        key_ = null;
        keyBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    public Key.Builder getKeyBuilder() {
      
      onChanged();
      return getKeyFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    public KeyOrBuilder getKeyOrBuilder() {
      if (keyBuilder_ != null) {
        return keyBuilder_.getMessageOrBuilder();
      } else {
        return key_ == null ?
            Key.getDefaultInstance() : key_;
      }
    }
    /**
     * <code>optional .Key key = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        Key, Key.Builder, KeyOrBuilder> 
        getKeyFieldBuilder() {
      if (keyBuilder_ == null) {
        keyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            Key, Key.Builder, KeyOrBuilder>(
                getKey(),
                getParentForChildren(),
                isClean());
        key_ = null;
      }
      return keyBuilder_;
    }

    private java.util.List<MetadataEntry> metadata_ =
      java.util.Collections.emptyList();
    private void ensureMetadataIsMutable() {
      if (!((bitField0_ & 0x00000010) == 0x00000010)) {
        metadata_ = new java.util.ArrayList<MetadataEntry>(metadata_);
        bitField0_ |= 0x00000010;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> metadataBuilder_;

    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public java.util.List<MetadataEntry> getMetadataList() {
      if (metadataBuilder_ == null) {
        return java.util.Collections.unmodifiableList(metadata_);
      } else {
        return metadataBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public int getMetadataCount() {
      if (metadataBuilder_ == null) {
        return metadata_.size();
      } else {
        return metadataBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public MetadataEntry getMetadata(int index) {
      if (metadataBuilder_ == null) {
        return metadata_.get(index);
      } else {
        return metadataBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder setMetadata(
        int index, MetadataEntry value) {
      if (metadataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMetadataIsMutable();
        metadata_.set(index, value);
        onChanged();
      } else {
        metadataBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder setMetadata(
        int index, MetadataEntry.Builder builderForValue) {
      if (metadataBuilder_ == null) {
        ensureMetadataIsMutable();
        metadata_.set(index, builderForValue.build());
        onChanged();
      } else {
        metadataBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder addMetadata(MetadataEntry value) {
      if (metadataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMetadataIsMutable();
        metadata_.add(value);
        onChanged();
      } else {
        metadataBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder addMetadata(
        int index, MetadataEntry value) {
      if (metadataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMetadataIsMutable();
        metadata_.add(index, value);
        onChanged();
      } else {
        metadataBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder addMetadata(
        MetadataEntry.Builder builderForValue) {
      if (metadataBuilder_ == null) {
        ensureMetadataIsMutable();
        metadata_.add(builderForValue.build());
        onChanged();
      } else {
        metadataBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder addMetadata(
        int index, MetadataEntry.Builder builderForValue) {
      if (metadataBuilder_ == null) {
        ensureMetadataIsMutable();
        metadata_.add(index, builderForValue.build());
        onChanged();
      } else {
        metadataBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder addAllMetadata(
        Iterable<? extends MetadataEntry> values) {
      if (metadataBuilder_ == null) {
        ensureMetadataIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, metadata_);
        onChanged();
      } else {
        metadataBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder clearMetadata() {
      if (metadataBuilder_ == null) {
        metadata_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
        onChanged();
      } else {
        metadataBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public Builder removeMetadata(int index) {
      if (metadataBuilder_ == null) {
        ensureMetadataIsMutable();
        metadata_.remove(index);
        onChanged();
      } else {
        metadataBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public MetadataEntry.Builder getMetadataBuilder(
        int index) {
      return getMetadataFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public MetadataEntryOrBuilder getMetadataOrBuilder(
        int index) {
      if (metadataBuilder_ == null) {
        return metadata_.get(index);  } else {
        return metadataBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public java.util.List<? extends MetadataEntryOrBuilder> 
         getMetadataOrBuilderList() {
      if (metadataBuilder_ != null) {
        return metadataBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(metadata_);
      }
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public MetadataEntry.Builder addMetadataBuilder() {
      return getMetadataFieldBuilder().addBuilder(
          MetadataEntry.getDefaultInstance());
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public MetadataEntry.Builder addMetadataBuilder(
        int index) {
      return getMetadataFieldBuilder().addBuilder(
          index, MetadataEntry.getDefaultInstance());
    }
    /**
     * <code>repeated .MetadataEntry metadata = 5;</code>
     */
    public java.util.List<MetadataEntry.Builder> 
         getMetadataBuilderList() {
      return getMetadataFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> 
        getMetadataFieldBuilder() {
      if (metadataBuilder_ == null) {
        metadataBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder>(
                metadata_,
                ((bitField0_ & 0x00000010) == 0x00000010),
                getParentForChildren(),
                isClean());
        metadata_ = null;
      }
      return metadataBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:InstallMessage)
  }

  // @@protoc_insertion_point(class_scope:InstallMessage)
  private static final InstallMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new InstallMessage();
  }

  public static InstallMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<InstallMessage>
      PARSER = new com.google.protobuf.AbstractParser<InstallMessage>() {
    public InstallMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new InstallMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<InstallMessage> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<InstallMessage> getParserForType() {
    return PARSER;
  }

  public InstallMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

