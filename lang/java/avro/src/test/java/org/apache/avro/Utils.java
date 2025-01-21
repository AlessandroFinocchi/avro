package org.apache.avro;

import java.util.HashMap;
import java.util.Map;

public class Utils {
  public enum DataType {
    RECORD, ENUM, ARRAY, MAP, UNION, FIXED, STRING, BYTES, INT32, LONG64, FLOAT32, FLOAT64, BOOLEAN, NULL
  }
  private static final Map<DataType, Schema.Type> dataTypeToTypeMap = new HashMap<>();
  static {
    dataTypeToTypeMap.put(DataType.RECORD, Schema.Type.RECORD);
    dataTypeToTypeMap.put(DataType.ENUM, Schema.Type.ENUM);
    dataTypeToTypeMap.put(DataType.ARRAY, Schema.Type.ARRAY);
    dataTypeToTypeMap.put(DataType.MAP, Schema.Type.MAP);
    dataTypeToTypeMap.put(DataType.UNION, Schema.Type.UNION);
    dataTypeToTypeMap.put(DataType.FIXED, Schema.Type.FIXED);
    dataTypeToTypeMap.put(DataType.STRING, Schema.Type.STRING);
    dataTypeToTypeMap.put(DataType.BYTES, Schema.Type.BYTES);
    dataTypeToTypeMap.put(DataType.INT32, Schema.Type.INT);
    dataTypeToTypeMap.put(DataType.LONG64, Schema.Type.LONG);
    dataTypeToTypeMap.put(DataType.FLOAT32, Schema.Type.FLOAT);
    dataTypeToTypeMap.put(DataType.FLOAT64, Schema.Type.DOUBLE);
    dataTypeToTypeMap.put(DataType.BOOLEAN, Schema.Type.BOOLEAN);
    dataTypeToTypeMap.put(DataType.NULL, Schema.Type.NULL);
  }

  public static Schema.Type getSchemaType(DataType dataType) {
    return dataTypeToTypeMap.getOrDefault(dataType, null);
  }

  /**
   * Based on <a href="https://avro.apache.org/docs/1.11.1/specification/">Specifications v1.11.1</a>
   */
  public static String getSchemaString(DataType type) {
    switch (type) {
      case NULL: return "{\"type\":\"null\"}";

      case BOOLEAN: return "{\"type\":\"boolean\"}";

      default: throw new IllegalArgumentException("Unsupported data type: " + type);
    }
  }
}
