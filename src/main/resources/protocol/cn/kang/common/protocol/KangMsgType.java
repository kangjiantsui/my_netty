// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: KangMsgType_pb.proto

package cn.kang.common.protocol;

public final class KangMsgType {
  private KangMsgType() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code KangMsgType_pb.ProtoMsgType}
   */
  public enum ProtoMsgType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>PB_TYPE_CONNECTED = 100;</code>
     */
    PB_TYPE_CONNECTED(0, 100),
    ;

    /**
     * <code>PB_TYPE_CONNECTED = 100;</code>
     */
    public static final int PB_TYPE_CONNECTED_VALUE = 100;


    public final int getNumber() { return value; }

    public static ProtoMsgType valueOf(int value) {
      switch (value) {
        case 100: return PB_TYPE_CONNECTED;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProtoMsgType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ProtoMsgType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ProtoMsgType>() {
            public ProtoMsgType findValueByNumber(int number) {
              return ProtoMsgType.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return cn.kang.common.protocol.KangMsgType.getDescriptor().getEnumTypes().get(0);
    }

    private static final ProtoMsgType[] VALUES = values();

    public static ProtoMsgType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ProtoMsgType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:KangMsgType_pb.ProtoMsgType)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024KangMsgType_pb.proto\022\016KangMsgType_pb*%" +
      "\n\014ProtoMsgType\022\025\n\021PB_TYPE_CONNECTED\020dB&\n" +
      "\027cn.kang.common.protocolB\013KangMsgType"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
