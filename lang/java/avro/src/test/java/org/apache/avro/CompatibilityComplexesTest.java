package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.apache.avro.CompatibilityTest.TestCompatibilityParams;
import static org.apache.avro.CompatibilityTest.testCompatibility;
import static org.apache.avro.SchemaCompatibility.SchemaCompatibilityType.*;
import static org.apache.avro.SchemaCompatibility.SchemaIncompatibilityType.*;
import static org.apache.avro.Utils.*;

@RunWith(Parameterized.class)
public class CompatibilityComplexesTest {

  @Parameterized.Parameters
  public static Collection<TestCompatibilityParams> getParameters() {
    return Arrays.asList(
        // Arrays
        new TestCompatibilityParams(incompatible(DataT.ARRAY),  validSchema(DataT.ARRAY),  INCOMPATIBLE, TYPE_MISMATCH, false),

        // Maps
        new TestCompatibilityParams(incompatible(DataT.MAP),    validSchema(DataT.MAP),    INCOMPATIBLE, TYPE_MISMATCH, false),

        // Enums
        new TestCompatibilityParams(incompatible(DataT.ENUM),   validSchema(DataT.ENUM),   INCOMPATIBLE, NAME_MISMATCH, false),
        new TestCompatibilityParams(incompatible(DataT.ENUM),   validSchema(DataT.ENUM),   INCOMPATIBLE, MISSING_ENUM_SYMBOLS, false),

        // Fixed
        new TestCompatibilityParams(incompatible(DataT.FIXED),  validSchema(DataT.FIXED),  INCOMPATIBLE, NAME_MISMATCH, false),
        new TestCompatibilityParams(incompatible(DataT.FIXED),  validSchema(DataT.FIXED),  INCOMPATIBLE, FIXED_SIZE_MISMATCH, false),

        // Records
        new TestCompatibilityParams(incompatible(DataT.RECORD), validSchema(DataT.RECORD), INCOMPATIBLE, NAME_MISMATCH, false),
        new TestCompatibilityParams(incompatible(DataT.RECORD), validSchema(DataT.RECORD), INCOMPATIBLE, READER_FIELD_MISSING_DEFAULT_VALUE, false),

        // Unions
        new TestCompatibilityParams(incompatible(DataT.UNION),  validSchema(DataT.UNION),  INCOMPATIBLE, MISSING_UNION_BRANCH, false),
        new TestCompatibilityParams(validSchema(DataT.UNION),   validSchema(DataT.INT32),  INCOMPATIBLE, MISSING_UNION_BRANCH, false),
        new TestCompatibilityParams(validSchema(DataT.INT32),   validSchema(DataT.UNION),  INCOMPATIBLE, TYPE_MISMATCH, false),
        // Added after Badua
        new TestCompatibilityParams(validSchema(DataT.STRING),  emptyUnion(),              COMPATIBLE,   null, false)

    );
  }

  private static Schema emptyUnion() {
    List<Schema> schemas = new ArrayList<>();
    return Schema.createUnion(schemas);
  }

  private static Schema incompatible(DataT type) {
    Schema schema;
    switch (type) {

      case ARRAY:
        Schema elementType = Schema.create(Schema.Type.INT);
        schema = Schema.createArray(elementType);
        return schema;

      case MAP:
        Schema valueType = Schema.create(Schema.Type.INT);
        schema = Schema.createMap(valueType);
        return schema;

      case ENUM:
        List<String> enumValues = new ArrayList<>();
        enumValues.add("SPRING");
        enumValues.add("SUMMER");
        enumValues.add("AUTUMN");
        enumValues.add("INVERNO");
        schema = Schema.createEnum("EnumName2", "This is an enum schema", VALID_SCHEMA_NAMESPACE, enumValues);
        return schema;

      case FIXED:
        schema = Schema.createFixed("md6", null, VALID_SCHEMA_NAMESPACE, 32);
        return schema;

      case RECORD:
        Schema nestedRecordSchema = Schema.create(Schema.Type.INT);
        Schema.Field recordField = new Schema.Field("ValueInt", nestedRecordSchema, null, null);
        List<Schema.Field> recordFields = new ArrayList<>();
        recordFields.add(recordField);
        schema = Schema.createRecord("RecordName2", null, VALID_SCHEMA_NAMESPACE, false, recordFields);
        return schema;

      case UNION:
        Schema firstType = Schema.create(Schema.Type.INT);
        Schema secondType = Schema.create(Schema.Type.LONG);
        List<Schema> schemas = new ArrayList<>();
        schemas.add(firstType);
        schemas.add(secondType);
        schema = Schema.createUnion(schemas);
        return schema;

      default:
        throw new IllegalArgumentException("Unknown type: " + type);
      }
  }

  private final TestCompatibilityParams params;

  public CompatibilityComplexesTest(TestCompatibilityParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testCompatibility(params);
  }
}
