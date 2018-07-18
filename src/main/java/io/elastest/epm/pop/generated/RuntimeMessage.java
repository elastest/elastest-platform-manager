package io.elastest.epm.pop.generated;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client.proto

/**
 * Protobuf type {@code RuntimeMessage}
 */
public final class RuntimeMessage extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:RuntimeMessage)
        RuntimeMessageOrBuilder {
    // Use RuntimeMessage.newBuilder() to construct.
    private RuntimeMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private RuntimeMessage() {
        resourceId_ = "";
        property_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        file_ = com.google.protobuf.ByteString.EMPTY;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
        return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }

    private RuntimeMessage(
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

                        resourceId_ = s;
                        break;
                    }
                    case 18: {
                        String s = input.readStringRequireUtf8();
                        if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                            property_ = new com.google.protobuf.LazyStringArrayList();
                            mutable_bitField0_ |= 0x00000002;
                        }
                        property_.add(s);
                        break;
                    }
                    case 26: {
                        file_ = input.readBytes();
                        break;
                    }
                    case 34: {
                        PoP.Builder subBuilder = null;
                        if (pop_ != null) {
                            subBuilder = pop_.toBuilder();
                        }
                        pop_ = input.readMessage(PoP.parser(), extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom(pop_);
                            pop_ = subBuilder.buildPartial();
                        }

                        break;
                    }
                    case 42: {
                        VDU.Builder subBuilder = null;
                        if (vdu_ != null) {
                            subBuilder = vdu_.toBuilder();
                        }
                        vdu_ = input.readMessage(VDU.parser(), extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom(vdu_);
                            vdu_ = subBuilder.buildPartial();
                        }

                        break;
                    }
                }
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        } finally {
            if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                property_ = property_.getUnmodifiableView();
            }
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return Client.internal_static_RuntimeMessage_descriptor;
    }

    protected FieldAccessorTable
    internalGetFieldAccessorTable() {
        return Client.internal_static_RuntimeMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(
                RuntimeMessage.class, Builder.class);
    }

    private int bitField0_;
    public static final int RESOURCE_ID_FIELD_NUMBER = 1;
    private volatile Object resourceId_;

    /**
     * <code>optional string resource_id = 1;</code>
     */
    public String getResourceId() {
        Object ref = resourceId_;
        if (ref instanceof String) {
            return (String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            String s = bs.toStringUtf8();
            resourceId_ = s;
            return s;
        }
    }

    /**
     * <code>optional string resource_id = 1;</code>
     */
    public com.google.protobuf.ByteString getResourceIdBytes() {
        Object ref = resourceId_;
        if (ref instanceof String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((String) ref);
            resourceId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PROPERTY_FIELD_NUMBER = 2;
    private com.google.protobuf.LazyStringList property_;

    /**
     * <code>repeated string property = 2;</code>
     */
    public com.google.protobuf.ProtocolStringList getPropertyList() {
        return property_;
    }

    /**
     * <code>repeated string property = 2;</code>
     */
    public int getPropertyCount() {
        return property_.size();
    }

    /**
     * <code>repeated string property = 2;</code>
     */
    public String getProperty(int index) {
        return property_.get(index);
    }

    /**
     * <code>repeated string property = 2;</code>
     */
    public com.google.protobuf.ByteString getPropertyBytes(int index) {
        return property_.getByteString(index);
    }

    public static final int FILE_FIELD_NUMBER = 3;
    private com.google.protobuf.ByteString file_;

    /**
     * <code>optional bytes file = 3;</code>
     */
    public com.google.protobuf.ByteString getFile() {
        return file_;
    }

    public static final int POP_FIELD_NUMBER = 4;
    private PoP pop_;

    /**
     * <code>optional .PoP pop = 4;</code>
     */
    public boolean hasPop() {
        return pop_ != null;
    }

    /**
     * <code>optional .PoP pop = 4;</code>
     */
    public PoP getPop() {
        return pop_ == null ? PoP.getDefaultInstance() : pop_;
    }

    /**
     * <code>optional .PoP pop = 4;</code>
     */
    public PoPOrBuilder getPopOrBuilder() {
        return getPop();
    }

    public static final int VDU_FIELD_NUMBER = 5;
    private VDU vdu_;

    /**
     * <code>optional .VDU vdu = 5;</code>
     */
    public boolean hasVdu() {
        return vdu_ != null;
    }

    /**
     * <code>optional .VDU vdu = 5;</code>
     */
    public VDU getVdu() {
        return vdu_ == null ? VDU.getDefaultInstance() : vdu_;
    }

    /**
     * <code>optional .VDU vdu = 5;</code>
     */
    public VDUOrBuilder getVduOrBuilder() {
        return getVdu();
    }

    private byte memoizedIsInitialized = -1;

    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) return true;
        if (isInitialized == 0) return false;

        memoizedIsInitialized = 1;
        return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (!getResourceIdBytes().isEmpty()) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 1, resourceId_);
        }
        for (int i = 0; i < property_.size(); i++) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 2, property_.getRaw(i));
        }
        if (!file_.isEmpty()) {
            output.writeBytes(3, file_);
        }
        if (pop_ != null) {
            output.writeMessage(4, getPop());
        }
        if (vdu_ != null) {
            output.writeMessage(5, getVdu());
        }
    }

    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) return size;

        size = 0;
        if (!getResourceIdBytes().isEmpty()) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, resourceId_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < property_.size(); i++) {
                dataSize += computeStringSizeNoTag(property_.getRaw(i));
            }
            size += dataSize;
            size += 1 * getPropertyList().size();
        }
        if (!file_.isEmpty()) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(3, file_);
        }
        if (pop_ != null) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, getPop());
        }
        if (vdu_ != null) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5, getVdu());
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
        if (!(obj instanceof RuntimeMessage)) {
            return super.equals(obj);
        }
        RuntimeMessage other = (RuntimeMessage) obj;

        boolean result = true;
        result = result && getResourceId().equals(other.getResourceId());
        result = result && getPropertyList().equals(other.getPropertyList());
        result = result && getFile().equals(other.getFile());
        result = result && (hasPop() == other.hasPop());
        if (hasPop()) {
            result = result && getPop().equals(other.getPop());
        }
        result = result && (hasVdu() == other.hasVdu());
        if (hasVdu()) {
            result = result && getVdu().equals(other.getVdu());
        }
        return result;
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptorForType().hashCode();
        hash = (37 * hash) + RESOURCE_ID_FIELD_NUMBER;
        hash = (53 * hash) + getResourceId().hashCode();
        if (getPropertyCount() > 0) {
            hash = (37 * hash) + PROPERTY_FIELD_NUMBER;
            hash = (53 * hash) + getPropertyList().hashCode();
        }
        hash = (37 * hash) + FILE_FIELD_NUMBER;
        hash = (53 * hash) + getFile().hashCode();
        if (hasPop()) {
            hash = (37 * hash) + POP_FIELD_NUMBER;
            hash = (53 * hash) + getPop().hashCode();
        }
        if (hasVdu()) {
            hash = (37 * hash) + VDU_FIELD_NUMBER;
            hash = (53 * hash) + getVdu().hashCode();
        }
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static RuntimeMessage parseFrom(com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RuntimeMessage parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RuntimeMessage parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static RuntimeMessage parseFrom(
            byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static RuntimeMessage parseFrom(java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static RuntimeMessage parseFrom(
            java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
                PARSER, input, extensionRegistry);
    }

    public static RuntimeMessage parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static RuntimeMessage parseDelimitedFrom(
            java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
                PARSER, input, extensionRegistry);
    }

    public static RuntimeMessage parseFrom(com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static RuntimeMessage parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
                PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RuntimeMessage prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /**
     * Protobuf type {@code RuntimeMessage}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:RuntimeMessage)
            RuntimeMessageOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return Client.internal_static_RuntimeMessage_descriptor;
        }

        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return Client.internal_static_RuntimeMessage_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(RuntimeMessage.class, Builder.class);
        }

        // Construct using RuntimeMessage.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
            }
        }

        public Builder clear() {
            super.clear();
            resourceId_ = "";

            property_ = com.google.protobuf.LazyStringArrayList.EMPTY;
            bitField0_ = (bitField0_ & ~0x00000002);
            file_ = com.google.protobuf.ByteString.EMPTY;

            if (popBuilder_ == null) {
                pop_ = null;
            } else {
                pop_ = null;
                popBuilder_ = null;
            }
            if (vduBuilder_ == null) {
                vdu_ = null;
            } else {
                vdu_ = null;
                vduBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return Client.internal_static_RuntimeMessage_descriptor;
        }

        public RuntimeMessage getDefaultInstanceForType() {
            return RuntimeMessage.getDefaultInstance();
        }

        public RuntimeMessage build() {
            RuntimeMessage result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        public RuntimeMessage buildPartial() {
            RuntimeMessage result = new RuntimeMessage(this);
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            result.resourceId_ = resourceId_;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                property_ = property_.getUnmodifiableView();
                bitField0_ = (bitField0_ & ~0x00000002);
            }
            result.property_ = property_;
            result.file_ = file_;
            if (popBuilder_ == null) {
                result.pop_ = pop_;
            } else {
                result.pop_ = popBuilder_.build();
            }
            if (vduBuilder_ == null) {
                result.vdu_ = vdu_;
            } else {
                result.vdu_ = vduBuilder_.build();
            }
            result.bitField0_ = to_bitField0_;
            onBuilt();
            return result;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        public Builder setRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        public Builder addRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof RuntimeMessage) {
                return mergeFrom((RuntimeMessage) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(RuntimeMessage other) {
            if (other == RuntimeMessage.getDefaultInstance()) return this;
            if (!other.getResourceId().isEmpty()) {
                resourceId_ = other.resourceId_;
                onChanged();
            }
            if (!other.property_.isEmpty()) {
                if (property_.isEmpty()) {
                    property_ = other.property_;
                    bitField0_ = (bitField0_ & ~0x00000002);
                } else {
                    ensurePropertyIsMutable();
                    property_.addAll(other.property_);
                }
                onChanged();
            }
            if (other.getFile() != com.google.protobuf.ByteString.EMPTY) {
                setFile(other.getFile());
            }
            if (other.hasPop()) {
                mergePop(other.getPop());
            }
            if (other.hasVdu()) {
                mergeVdu(other.getVdu());
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
            RuntimeMessage parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (RuntimeMessage) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private int bitField0_;

        private Object resourceId_ = "";

        /**
         * <code>optional string resource_id = 1;</code>
         */
        public String getResourceId() {
            Object ref = resourceId_;
            if (!(ref instanceof String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                resourceId_ = s;
                return s;
            } else {
                return (String) ref;
            }
        }

        /**
         * <code>optional string resource_id = 1;</code>
         */
        public com.google.protobuf.ByteString getResourceIdBytes() {
            Object ref = resourceId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((String) ref);
                resourceId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string resource_id = 1;</code>
         */
        public Builder setResourceId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            resourceId_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>optional string resource_id = 1;</code>
         */
        public Builder clearResourceId() {

            resourceId_ = getDefaultInstance().getResourceId();
            onChanged();
            return this;
        }

        /**
         * <code>optional string resource_id = 1;</code>
         */
        public Builder setResourceIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            resourceId_ = value;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringList property_ =
                com.google.protobuf.LazyStringArrayList.EMPTY;

        private void ensurePropertyIsMutable() {
            if (!((bitField0_ & 0x00000002) == 0x00000002)) {
                property_ = new com.google.protobuf.LazyStringArrayList(property_);
                bitField0_ |= 0x00000002;
            }
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public com.google.protobuf.ProtocolStringList getPropertyList() {
            return property_.getUnmodifiableView();
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public int getPropertyCount() {
            return property_.size();
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public String getProperty(int index) {
            return property_.get(index);
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public com.google.protobuf.ByteString getPropertyBytes(int index) {
            return property_.getByteString(index);
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public Builder setProperty(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensurePropertyIsMutable();
            property_.set(index, value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public Builder addProperty(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensurePropertyIsMutable();
            property_.add(value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public Builder addAllProperty(Iterable<String> values) {
            ensurePropertyIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, property_);
            onChanged();
            return this;
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public Builder clearProperty() {
            property_ = com.google.protobuf.LazyStringArrayList.EMPTY;
            bitField0_ = (bitField0_ & ~0x00000002);
            onChanged();
            return this;
        }

        /**
         * <code>repeated string property = 2;</code>
         */
        public Builder addPropertyBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensurePropertyIsMutable();
            property_.add(value);
            onChanged();
            return this;
        }

        private com.google.protobuf.ByteString file_ = com.google.protobuf.ByteString.EMPTY;

        /**
         * <code>optional bytes file = 3;</code>
         */
        public com.google.protobuf.ByteString getFile() {
            return file_;
        }

        /**
         * <code>optional bytes file = 3;</code>
         */
        public Builder setFile(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }

            file_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>optional bytes file = 3;</code>
         */
        public Builder clearFile() {

            file_ = getDefaultInstance().getFile();
            onChanged();
            return this;
        }

        private PoP pop_ = null;
        private com.google.protobuf.SingleFieldBuilderV3<PoP, PoP.Builder, PoPOrBuilder> popBuilder_;

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public boolean hasPop() {
            return popBuilder_ != null || pop_ != null;
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public PoP getPop() {
            if (popBuilder_ == null) {
                return pop_ == null ? PoP.getDefaultInstance() : pop_;
            } else {
                return popBuilder_.getMessage();
            }
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public Builder setPop(PoP value) {
            if (popBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                pop_ = value;
                onChanged();
            } else {
                popBuilder_.setMessage(value);
            }

            return this;
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public Builder setPop(PoP.Builder builderForValue) {
            if (popBuilder_ == null) {
                pop_ = builderForValue.build();
                onChanged();
            } else {
                popBuilder_.setMessage(builderForValue.build());
            }

            return this;
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public Builder mergePop(PoP value) {
            if (popBuilder_ == null) {
                if (pop_ != null) {
                    pop_ = PoP.newBuilder(pop_).mergeFrom(value).buildPartial();
                } else {
                    pop_ = value;
                }
                onChanged();
            } else {
                popBuilder_.mergeFrom(value);
            }

            return this;
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public Builder clearPop() {
            if (popBuilder_ == null) {
                pop_ = null;
                onChanged();
            } else {
                pop_ = null;
                popBuilder_ = null;
            }

            return this;
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public PoP.Builder getPopBuilder() {

            onChanged();
            return getPopFieldBuilder().getBuilder();
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        public PoPOrBuilder getPopOrBuilder() {
            if (popBuilder_ != null) {
                return popBuilder_.getMessageOrBuilder();
            } else {
                return pop_ == null ? PoP.getDefaultInstance() : pop_;
            }
        }

        /**
         * <code>optional .PoP pop = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<PoP, PoP.Builder, PoPOrBuilder>
        getPopFieldBuilder() {
            if (popBuilder_ == null) {
                popBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<PoP, PoP.Builder, PoPOrBuilder>(
                                getPop(), getParentForChildren(), isClean());
                pop_ = null;
            }
            return popBuilder_;
        }

        private VDU vdu_ = null;
        private com.google.protobuf.SingleFieldBuilderV3<VDU, VDU.Builder, VDUOrBuilder> vduBuilder_;

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public boolean hasVdu() {
            return vduBuilder_ != null || vdu_ != null;
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public VDU getVdu() {
            if (vduBuilder_ == null) {
                return vdu_ == null ? VDU.getDefaultInstance() : vdu_;
            } else {
                return vduBuilder_.getMessage();
            }
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public Builder setVdu(VDU value) {
            if (vduBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                vdu_ = value;
                onChanged();
            } else {
                vduBuilder_.setMessage(value);
            }

            return this;
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public Builder setVdu(VDU.Builder builderForValue) {
            if (vduBuilder_ == null) {
                vdu_ = builderForValue.build();
                onChanged();
            } else {
                vduBuilder_.setMessage(builderForValue.build());
            }

            return this;
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public Builder mergeVdu(VDU value) {
            if (vduBuilder_ == null) {
                if (vdu_ != null) {
                    vdu_ = VDU.newBuilder(vdu_).mergeFrom(value).buildPartial();
                } else {
                    vdu_ = value;
                }
                onChanged();
            } else {
                vduBuilder_.mergeFrom(value);
            }

            return this;
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public Builder clearVdu() {
            if (vduBuilder_ == null) {
                vdu_ = null;
                onChanged();
            } else {
                vdu_ = null;
                vduBuilder_ = null;
            }

            return this;
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public VDU.Builder getVduBuilder() {

            onChanged();
            return getVduFieldBuilder().getBuilder();
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        public VDUOrBuilder getVduOrBuilder() {
            if (vduBuilder_ != null) {
                return vduBuilder_.getMessageOrBuilder();
            } else {
                return vdu_ == null ? VDU.getDefaultInstance() : vdu_;
            }
        }

        /**
         * <code>optional .VDU vdu = 5;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<VDU, VDU.Builder, VDUOrBuilder>
        getVduFieldBuilder() {
            if (vduBuilder_ == null) {
                vduBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<VDU, VDU.Builder, VDUOrBuilder>(
                                getVdu(), getParentForChildren(), isClean());
                vdu_ = null;
            }
            return vduBuilder_;
        }

        public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return this;
        }

        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return this;
        }

        // @@protoc_insertion_point(builder_scope:RuntimeMessage)
    }

    // @@protoc_insertion_point(class_scope:RuntimeMessage)
    private static final RuntimeMessage DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new RuntimeMessage();
    }

    public static RuntimeMessage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RuntimeMessage> PARSER =
            new com.google.protobuf.AbstractParser<RuntimeMessage>() {
                public RuntimeMessage parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    return new RuntimeMessage(input, extensionRegistry);
                }
            };

    public static com.google.protobuf.Parser<RuntimeMessage> parser() {
        return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<RuntimeMessage> getParserForType() {
        return PARSER;
    }

    public RuntimeMessage getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
