package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

public class Utils {
  public enum NameType {
    VALID, INVALID, NULL
  }
  public static Schema.Names getNames(NameType type) {
    switch (type) {
      case VALID: return new Schema.Names("org.apache.avro");
      case INVALID: return invalidSchemaNames();
      case NULL: return null;
      default: throw new IllegalArgumentException("Unsupported type " + type);
    }
  }
  private static Schema.Names invalidSchemaNames() {
    Schema.Names names = Mockito.mock(Schema.Names.class);
    Mockito.when(names.get(any())).thenThrow(new Exception());
    Mockito.when(names.toString()).thenThrow(new Exception());
    Mockito.when(names.contains(any())).thenThrow(new Exception());
    Mockito.when(names.put(any(), any())).thenThrow(new Exception());
    Mockito.when(names.space()).thenThrow(new Exception());
    return names;
  }

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
  public static JsonNode getJsonNode(DataType type) throws JsonProcessingException {

    JsonNode jsonNode;
    ObjectMapper mapper = new ObjectMapper();
    String str;

    switch (type) {
      case NULL:
        str = "{\"type\":\"null\"}";
        jsonNode = mapper.readTree(str);
        break;

      case BOOLEAN:
        str = "{\"type\":\"boolean\"}";
        jsonNode = mapper.readTree(str);
        break;

      case INT32:
        str = "{\"type\":\"int\"}";
        jsonNode = mapper.readTree(str);
        break;

      case LONG64:
        str = "{\"type\":\"long\"}";
        jsonNode = mapper.readTree(str);
        break;

      default:
        throw new IllegalArgumentException("");
    }
    return jsonNode;
  }

  public static Schema getExpectedSchema(DataType dataType, NameType type) {
    return null;
  }
}
