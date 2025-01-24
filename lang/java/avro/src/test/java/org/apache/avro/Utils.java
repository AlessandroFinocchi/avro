package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

public class Utils {
  enum NameT {
    VALID, INVALID, NULL
  }
  private static final String VALID_SCHEMA_NAME = "my.namespace";
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

  public enum DataT {
    RECORD, ENUM, ARRAY, MAP, UNION, FIXED, STRING, BYTES, INT32, LONG64, FLOAT32, DOUBLE64, BOOLEAN, NULL
  }
  public enum DataState {
    VALID, WITHOUT_MANDATORY_FIELDS, INVALID_MANDATORY_FIELD, NULL
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

  /**
   * Based on <a href="https://avro.apache.org/docs/1.11.1/specification/">Specifications v1.11.1</a>
   */
  public static JsonNode getJsonNode(DataT type, DataState dataState) throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode;
    String jsonNodeString;
    String mandatoryFieldAppendingValue = "";
    String unionInvalidField = "";
    boolean isMandatoryFieldPresent = true;

    switch (dataState) {
      case VALID:
        break;
      case WITHOUT_MANDATORY_FIELDS:
        isMandatoryFieldPresent = false;
        break;
      case INVALID_MANDATORY_FIELD:
        mandatoryFieldAppendingValue = "invalid";
        unionInvalidField = ",\"invalid\"";
        break;
      case NULL:
        return null;
    }

    switch (type) {
      case NULL:

        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "null\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case BOOLEAN:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "boolean\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case INT32:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "int\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case LONG64:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "long\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case FLOAT32:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "float\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case DOUBLE64:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "double\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case STRING:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "string\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case BYTES:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "bytes\"" : "") +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

      case ARRAY:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "array\"," : "") +
            "\"items\":\"string\"" +
            "}";
        jsonNode = mapper.readTree(jsonNodeString);
        break;

    case FIXED:
      jsonNodeString = "{" +
          (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "fixed\"," : "") +
          "\"size\":16," +
          "\"name\":\"md5\"" +
          "}";
      jsonNode = mapper.readTree(jsonNodeString);
      break;

    case MAP:
      jsonNodeString = "{" +
          (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "map\"," : "") +
          "\"values\":\"string\"" +
          "}";
      jsonNode = mapper.readTree(jsonNodeString);
      break;

    case ENUM:
      jsonNodeString = "{" +
          (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "enum\"," : "") +
          "\"name\":\"EnumName\"," +
          "\"doc\":\"This is an enum schema\"," +
          "\"symbols\":[\"SPRING\",\"SUMMER\",\"AUTUMN\",\"WINTER\"]" +
          "}";
      jsonNode = mapper.readTree(jsonNodeString);
      break;

    case UNION:
      jsonNodeString = "[" +
          (isMandatoryFieldPresent ? "\"null\",\"string\"" + unionInvalidField : "") +
          "]";
      jsonNode = mapper.readTree(jsonNodeString);
      break;

    case RECORD:
      jsonNodeString = "{" +
          (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "record\"," : "") +
          "\"name\":\"RecordName\"," +
          "\"aliases\":[\"RecordAlias\"]," +
          "\"fields\":[{\"name\":\"Value\",\"type\":\"string\"}]" +
          "}";
      jsonNode = mapper.readTree(jsonNodeString);
      break;

      default: throw new IllegalArgumentException();
    }
    return jsonNode;
  }
  public static Schema getExpectedSchema(DataT dataT) {
    Schema expectedSchema;
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
      expectedSchema = Schema.createRecord("RecordName", null, VALID_SCHEMA_NAME, false, recordFields);
      expectedSchema.addAlias("RecordAlias");
      return expectedSchema;

    case ENUM:
      List<String> enumValues = new ArrayList<>();
      enumValues.add("SPRING");
      enumValues.add("SUMMER");
      enumValues.add("AUTUMN");
      enumValues.add("WINTER");
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
    }
    throw new IllegalArgumentException();
  }
}
