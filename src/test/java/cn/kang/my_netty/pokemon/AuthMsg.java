// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Auth_pb.proto

package com.leocool.sgland.protocol;

public final class AuthMsg {
  private AuthMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registry.add(com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.authentication);
    registry.add(com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.agent);
    registry.add(com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.challenge);
  }
  public interface SglAuthMsgOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
  }
  /**
   * Protobuf type {@code Auth_pb.SglAuthMsg}
   */
  public static final class SglAuthMsg extends
      com.google.protobuf.GeneratedMessage
      implements SglAuthMsgOrBuilder {
    // Use SglAuthMsg.newBuilder() to construct.
    private SglAuthMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SglAuthMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SglAuthMsg defaultInstance;
    public static SglAuthMsg getDefaultInstance() {
      return defaultInstance;
    }

    public SglAuthMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SglAuthMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.leocool.sgland.protocol.AuthMsg.internal_static_Auth_pb_SglAuthMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.leocool.sgland.protocol.AuthMsg.internal_static_Auth_pb_SglAuthMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.class, com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<SglAuthMsg> PARSER =
        new com.google.protobuf.AbstractParser<SglAuthMsg>() {
      public SglAuthMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SglAuthMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SglAuthMsg> getParserForType() {
      return PARSER;
    }

    private void initFields() {
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.leocool.sgland.protocol.AuthMsg.SglAuthMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Auth_pb.SglAuthMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.leocool.sgland.protocol.AuthMsg.SglAuthMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.leocool.sgland.protocol.AuthMsg.internal_static_Auth_pb_SglAuthMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.leocool.sgland.protocol.AuthMsg.internal_static_Auth_pb_SglAuthMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.class, com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.Builder.class);
      }

      // Construct using com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.leocool.sgland.protocol.AuthMsg.internal_static_Auth_pb_SglAuthMsg_descriptor;
      }

      public com.leocool.sgland.protocol.AuthMsg.SglAuthMsg getDefaultInstanceForType() {
        return com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.getDefaultInstance();
      }

      public com.leocool.sgland.protocol.AuthMsg.SglAuthMsg build() {
        com.leocool.sgland.protocol.AuthMsg.SglAuthMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.leocool.sgland.protocol.AuthMsg.SglAuthMsg buildPartial() {
        com.leocool.sgland.protocol.AuthMsg.SglAuthMsg result = new com.leocool.sgland.protocol.AuthMsg.SglAuthMsg(this);
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.leocool.sgland.protocol.AuthMsg.SglAuthMsg) {
          return mergeFrom((com.leocool.sgland.protocol.AuthMsg.SglAuthMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.leocool.sgland.protocol.AuthMsg.SglAuthMsg other) {
        if (other == com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.getDefaultInstance()) return this;
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.leocool.sgland.protocol.AuthMsg.SglAuthMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.leocool.sgland.protocol.AuthMsg.SglAuthMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      // @@protoc_insertion_point(builder_scope:Auth_pb.SglAuthMsg)
    }

    static {
      defaultInstance = new SglAuthMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:Auth_pb.SglAuthMsg)
    public static final int AUTHENTICATION_FIELD_NUMBER = 100;
    /**
     * <code>extend .SglMsg_pb.SglReqMsg { ... }</code>
     */
    public static final
      com.google.protobuf.GeneratedMessage.GeneratedExtension<
        com.leocool.sgland.protocol.SglMsg.SglReqMsg,
        java.lang.String> authentication = com.google.protobuf.GeneratedMessage
            .newMessageScopedGeneratedExtension(
          com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.getDefaultInstance(),
          0,
          java.lang.String.class,
          null);
    public static final int AGENT_FIELD_NUMBER = 101;
    /**
     * <code>extend .SglMsg_pb.SglReqMsg { ... }</code>
     */
    public static final
      com.google.protobuf.GeneratedMessage.GeneratedExtension<
        com.leocool.sgland.protocol.SglMsg.SglReqMsg,
        java.lang.String> agent = com.google.protobuf.GeneratedMessage
            .newMessageScopedGeneratedExtension(
          com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.getDefaultInstance(),
          1,
          java.lang.String.class,
          null);
    public static final int CHALLENGE_FIELD_NUMBER = 100;
    /**
     * <code>extend .SglMsg_pb.SglRespMsg { ... }</code>
     */
    public static final
      com.google.protobuf.GeneratedMessage.GeneratedExtension<
        com.leocool.sgland.protocol.SglMsg.SglRespMsg,
        java.lang.String> challenge = com.google.protobuf.GeneratedMessage
            .newMessageScopedGeneratedExtension(
          com.leocool.sgland.protocol.AuthMsg.SglAuthMsg.getDefaultInstance(),
          2,
          java.lang.String.class,
          null);
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_Auth_pb_SglAuthMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Auth_pb_SglAuthMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rAuth_pb.proto\022\007Auth_pb\032\017SglMsg_pb.prot" +
      "o\"\211\001\n\nSglAuthMsg2,\n\016authentication\022\024.Sgl" +
      "Msg_pb.SglReqMsg\030d \001(\t2#\n\005agent\022\024.SglMsg" +
      "_pb.SglReqMsg\030e \001(\t2(\n\tchallenge\022\025.SglMs" +
      "g_pb.SglRespMsg\030d \001(\tB&\n\033com.leocool.sgl" +
      "and.protocolB\007AuthMsg"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_Auth_pb_SglAuthMsg_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_Auth_pb_SglAuthMsg_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_Auth_pb_SglAuthMsg_descriptor,
              new java.lang.String[] { });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.leocool.sgland.protocol.SglMsg.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}