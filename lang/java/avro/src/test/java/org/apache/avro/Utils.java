package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class Utils {
  public enum NameT {
    VALID, INVALID, NULL
  }
  public static final String VALID_SCHEMA_NAMESPACE = "my.namespace";
  public static Schema.Names getNames(NameT type) {
    switch (type) {
    case VALID:
      return new Schema.Names(VALID_SCHEMA_NAMESPACE);
    case INVALID:
      return new InvalidSchemaNames();
    case NULL:
      return null;
    default:
      throw new IllegalArgumentException("Unsupported type " + type);
    }
  }

  public enum DataT {
    RECORD, ENUM, ARRAY, MAP, UNION, FIXED, STRING, BYTES, INT32, LONG64, FLOAT32, DOUBLE64, BOOLEAN, NULL,
    // Added after jacoco
    ERROR, NO_FIELD_RECORD, NO_ARRAY_FIELD_RECORD, NO_SYMBOLS_ENUM, NO_ARRAY_SYMBOLS_ENUM,
    DEFAULT_VALUE_ENUM, NO_ITEMS_ARRAY, NO_VALUES_MAP, NO_SIZE_FIXED, NO_INT_SIZE_FIXED, NON_TEXTUAL,
    // Added after PIT
    LOGICAL_TYPE_DATE,
  }
  public enum DataState {
    VALID, WITHOUT_MANDATORY_FIELDS, INVALID_MANDATORY_FIELD, NULL
  }

  /**
   * Based on <a href="https://avro.apache.org/docs/1.12.0/specification/">Specifications v1.11.1</a>
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
        break;

      case BOOLEAN:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "boolean\"" : "") +
            "}";
        break;

      case INT32:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "int\"" : "") +
            "}";
        break;

      case LONG64:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "long\"" : "") +
            "}";
        break;

      case FLOAT32:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "float\"" : "") +
            "}";
        break;

      case DOUBLE64:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "double\"" : "") +
            "}";
        break;

      case STRING:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "string\"" : "") +
            "}";
        break;

      case BYTES:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "bytes\"" : "") +
            "}";
        break;

      case ARRAY:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "array\"," : "") +
            "\"items\":\"string\"" +
            "}";
        break;

      case FIXED:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "fixed\"," : "") +
            "\"size\":16," +
            "\"name\":\"md5\"" +
            "}";
        break;

      case MAP:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "map\"," : "") +
            "\"values\":\"string\"" +
            "}";
        break;

      case ENUM:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "enum\"," : "") +
            "\"name\":\"EnumName\"," +
            "\"doc\":\"This is an enum schema\"," +
            "\"symbols\":[\"SPRING\",\"SUMMER\",\"AUTUMN\",\"WINTER\"]" +
            "}";
        break;

      case UNION:
        jsonNodeString = "[" +
            (isMandatoryFieldPresent ? "\"null\",\"string\"" + unionInvalidField : "") +
            "]";
        break;

      case RECORD:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "record\"," : "") +
            "\"name\":\"RecordName\"," +
            "\"aliases\":[\"RecordAlias\"]," +
            "\"fields\":[{\"name\":\"Value\",\"type\":\"string\"}]" +
            "}";
        break;

      case ERROR:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "error\"," : "") +
            "\"namespace\":\""+ VALID_SCHEMA_NAMESPACE + "\"," +
            "\"name\":\"ErrorName\"," +
            "\"aliases\":[\"ErrorAlias\"]," +
            "\"fields\":[{\"name\":\"Value\",\"type\":\"string\"}]" +
            "}";
        break;

      case NO_FIELD_RECORD:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "record\"," : "") +
            "\"name\":\"RecordName\"," +
            "\"aliases\":[\"RecordAlias\"]" +
            "}";
        break;

      case NO_ARRAY_FIELD_RECORD:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "record\"," : "") +
            "\"namespace\":\""+ VALID_SCHEMA_NAMESPACE + "\"," +
            "\"name\":\"RecordName\"," +
            "\"aliases\":[\"RecordAlias\"]," +
            "\"fields\":\"NoArrayFields\"" +
            "}";
        break;

      case NO_SYMBOLS_ENUM:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "enum\"," : "") +
            "\"name\":\"EnumName\"," +
            "\"doc\":\"This is an enum schema\"" +
            "}";
        break;

      case NO_ARRAY_SYMBOLS_ENUM:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "enum\"," : "") +
            "\"name\":\"EnumName\"," +
            "\"doc\":\"This is an enum schema\"," +
            "\"symbols\":\"SingleSymbol\"" +
            "}";
        break;

      case DEFAULT_VALUE_ENUM:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "enum\"," : "") +
            "\"name\":\"EnumName\"," +
            "\"doc\":\"This is an enum schema\"," +
            "\"symbols\":[\"SPRING\",\"SUMMER\",\"AUTUMN\",\"WINTER\"]," +
            "\"default\":\"WINTER\"" +
            "}";
        break;

      case NO_ITEMS_ARRAY:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "array\"" : "") +
            "}";
        break;

      case NO_VALUES_MAP:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "map\"" : "") +
            "}";
        break;

      case NO_SIZE_FIXED:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "fixed\"," : "") +
            "\"name\":\"md5\"" +
            "}";
        break;

      case NO_INT_SIZE_FIXED:
        jsonNodeString = "{" +
            (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "fixed\"," : "") +
            "\"size\":\"sixteen\"," +
            "\"name\":\"md5\"" +
            "}";
        break;

      case NON_TEXTUAL:
        return mapper.valueToTree(42);

    case LOGICAL_TYPE_DATE:
      jsonNodeString = "{" +
          (isMandatoryFieldPresent ? "\"type\":\"" + mandatoryFieldAppendingValue + "int\"," : "") +
          "\"logicalType\": \"date\"" +
          "}";
      break;

      default: throw new IllegalArgumentException();
    }
    jsonNode = mapper.readTree(jsonNodeString);
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

    case ENUM:
      List<String> enumValues = new ArrayList<>();
      enumValues.add("SPRING");
      enumValues.add("SUMMER");
      enumValues.add("AUTUMN");
      enumValues.add("WINTER");
      expectedSchema = Schema.createEnum("EnumName", "This is an enum schema", VALID_SCHEMA_NAMESPACE, enumValues);
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
      expectedSchema = Schema.createFixed("md5", null, VALID_SCHEMA_NAMESPACE, 16);
      return expectedSchema;

    case RECORD:
      Schema nestedRecordSchema = Schema.create(Schema.Type.STRING);
      Schema.Field recordField = new Schema.Field("Value", nestedRecordSchema, null, null);
      List<Schema.Field> recordFields = new ArrayList<>();
      recordFields.add(recordField);
      expectedSchema = Schema.createRecord("RecordName", null, VALID_SCHEMA_NAMESPACE, false, recordFields);
      expectedSchema.addAlias("RecordAlias");
      return expectedSchema;

    case ERROR:
      Schema nestedErrorSchema = Schema.create(Schema.Type.STRING);
      Schema.Field errorField = new Schema.Field("Value", nestedErrorSchema, null, null);
      List<Schema.Field> errorFields = new ArrayList<>();
      errorFields.add(errorField);
      expectedSchema = Schema.createRecord("ErrorName", null, VALID_SCHEMA_NAMESPACE, false, errorFields);
      expectedSchema.addAlias("ErrorName");
      return expectedSchema;

    case DEFAULT_VALUE_ENUM:
      List<String> defEnumValues = new ArrayList<>();
      defEnumValues.add("SPRING");
      defEnumValues.add("SUMMER");
      defEnumValues.add("AUTUMN");
      defEnumValues.add("WINTER");
      expectedSchema = Schema.createEnum("EnumName", "This is an enum schema", VALID_SCHEMA_NAMESPACE, defEnumValues, "WINTER");
      return expectedSchema;

    case NO_FIELD_RECORD: // It's invalid, it cannot be created
    case NO_ARRAY_FIELD_RECORD:
    case NO_SYMBOLS_ENUM:
    case NO_ARRAY_SYMBOLS_ENUM:
    case NO_ITEMS_ARRAY:
    case NO_VALUES_MAP:
    case NO_SIZE_FIXED:
    case NO_INT_SIZE_FIXED:
    case NON_TEXTUAL:
      return null;

    case NULL:
      return Schema.create(Schema.Type.NULL);

    case LOGICAL_TYPE_DATE:
      expectedSchema = Schema.create(Schema.Type.INT);
      expectedSchema.addProp("logicalType", "date");
      return expectedSchema;
    }
    throw new IllegalArgumentException();
  }
}
