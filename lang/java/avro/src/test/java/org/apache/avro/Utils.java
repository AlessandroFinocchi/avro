package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

public class Utils {
  enum NameT {
    VALID, INVALID, NULL
  }
  private static final String VALID_SCHEMA_NAME = "org.apache.avro";

  static Schema.Names getNames(NameT type) {
    switch (type) {
    case VALID:
      return new Schema.Names(VALID_SCHEMA_NAME);
    case INVALID:
      return new InvalidSchemaNames();
    case NULL:
      return null;
    default:
      throw new IllegalArgumentException("Unsupported type " + type);
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

  public enum DataT {
    RECORD, ENUM, ARRAY, MAP, UNION, FIXED, STRING, BYTES, INT32, LONG64, FLOAT32, DOUBLE64, BOOLEAN, NULL,
    NULL_OBJECT
  }
  private static final Map<DataT, Schema.Type> dataTypeToTypeMap = new HashMap<>();
  static {
    dataTypeToTypeMap.put(DataT.RECORD, Schema.Type.RECORD);
    dataTypeToTypeMap.put(DataT.ENUM, Schema.Type.ENUM);
    dataTypeToTypeMap.put(DataT.ARRAY, Schema.Type.ARRAY);
    dataTypeToTypeMap.put(DataT.MAP, Schema.Type.MAP);
    dataTypeToTypeMap.put(DataT.UNION, Schema.Type.UNION);
    dataTypeToTypeMap.put(DataT.FIXED, Schema.Type.FIXED);
    dataTypeToTypeMap.put(DataT.STRING, Schema.Type.STRING);
    dataTypeToTypeMap.put(DataT.BYTES, Schema.Type.BYTES);
    dataTypeToTypeMap.put(DataT.INT32, Schema.Type.INT);
    dataTypeToTypeMap.put(DataT.LONG64, Schema.Type.LONG);
    dataTypeToTypeMap.put(DataT.FLOAT32, Schema.Type.FLOAT);
    dataTypeToTypeMap.put(DataT.DOUBLE64, Schema.Type.DOUBLE);
    dataTypeToTypeMap.put(DataT.BOOLEAN, Schema.Type.BOOLEAN);
    dataTypeToTypeMap.put(DataT.NULL, Schema.Type.NULL);
  }
  public static Schema.Type getSchemaType(DataT dataT) {
    return dataTypeToTypeMap.getOrDefault(dataT, null);
  }

  /**
   * Based on <a href="https://avro.apache.org/docs/1.11.1/specification/">Specifications v1.11.1</a>
   */
  public static JsonNode getJsonNode(DataT type) throws JsonProcessingException {

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

      case FLOAT32:
        str = "{\"type\":\"float\"}";
        jsonNode = mapper.readTree(str);
        break;

      case DOUBLE64:
        str = "{\"type\":\"double\"}";
        jsonNode = mapper.readTree(str);
        break;

      case STRING:
        str = "{\"type\":\"string\"}";
        jsonNode = mapper.readTree(str);
        break;

      case BYTES:
        str = "{\"type\":\"bytes\"}";
        jsonNode = mapper.readTree(str);
        break;

      case ARRAY:
        str = "{\"type\":\"array\",\"items\":\"string\"}";
        jsonNode = mapper.readTree(str);
        break;

    case FIXED:
      str = "{\"type\":\"fixed\",\"size\":16,\"name\":\"md5\"}";
      jsonNode = mapper.readTree(str);
      break;

    case MAP:
      str = "{\"type\":\"map\",\"values\":\"string\"}";
      jsonNode = mapper.readTree(str);
      break;

    case ENUM:
      str = "{\"type\":\"enum\",\"name\":\"EnumName\",\"doc\":\"This is an enum schema\","
          + "\"symbols\":[\"COME\",\"QUANDO\",\"FUORI\",\"PIOVE\"]}";
      jsonNode = mapper.readTree(str);
      break;

    case UNION:
      str = "[\"null\",\"string\"]";
      jsonNode = mapper.readTree(str);
      break;

    case RECORD:
      str = "{\"type\":\"record\",\"name\":\"RecordName\",\"aliases\":[\"RecordAlias\"],"
          + "\"fields\":[{\"name\":\"Value\",\"type\":\"string\"}]}";
      jsonNode = mapper.readTree(str);
      break;

    case NULL_OBJECT:
      jsonNode = null;
      break;

      default: throw new IllegalArgumentException();
    }
    return jsonNode;
  }
  public static Schema getExpectedSchema(DataT dataT) {
    switch (dataT) {
    case STRING:
      return Schema.create(Schema.Type.STRING);

    case BOOLEAN:
      return Schema.create(Schema.Type.BOOLEAN);

    case BYTES:
      return Schema.create(Schema.Type.BYTES);

    case INT32:
      return Schema.create(Schema.Type.INT);

    case LONG64:
      return Schema.create(Schema.Type.LONG);

    case FLOAT32:
      return Schema.create(Schema.Type.FLOAT);

    case DOUBLE64:
      return Schema.create(Schema.Type.DOUBLE);

    case RECORD:
      Schema nestedSchema = Schema.create(Schema.Type.STRING);
      Schema.Field recordField = new Schema.Field("Value", nestedSchema, null, null);
      List<Schema.Field> recordFields = new ArrayList<>();
      recordFields.add(recordField);
      Schema expectedSchema = Schema.createRecord("RecordName", null, VALID_SCHEMA_NAME, false, recordFields);
      expectedSchema.addAlias("RecordAlias");
      return expectedSchema;

    case ENUM:
      List<String> enumValues = new ArrayList<>();
      enumValues.add("COME");
      enumValues.add("QUANDO");
      enumValues.add("FUORI");
      enumValues.add("PIOVE");
      expectedSchema = Schema.createEnum("EnumName", "This is an enum schema", VALID_SCHEMA_NAME, enumValues);
      return expectedSchema;

    case ARRAY:
      Schema elementType = Schema.create(Schema.Type.STRING);
      expectedSchema = Schema.createArray(elementType);
      return expectedSchema;

    case MAP:
      Schema valueType = Schema.create(Schema.Type.STRING);
      expectedSchema = Schema.createMap(valueType);
      return expectedSchema;

    case UNION:
      Schema firstType = Schema.create(Schema.Type.NULL);
      Schema secondType = Schema.create(Schema.Type.STRING);
      List<Schema> schemas = new ArrayList<>();
      schemas.add(firstType);
      schemas.add(secondType);
      expectedSchema = Schema.createUnion(schemas);
      return expectedSchema;

    case FIXED:
      expectedSchema = Schema.createFixed("md5", null, VALID_SCHEMA_NAME, 16);
      return expectedSchema;

    case NULL:
      return Schema.create(Schema.Type.NULL);

    case NULL_OBJECT:
      return null;
    }
    throw new IllegalArgumentException();
  }
}
