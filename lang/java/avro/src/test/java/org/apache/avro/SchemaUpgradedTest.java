package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaUpgradedTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Arrays.asList(
        // Added after jacoco

        // Valid
        new TestSchemaParams(DataT.DEFAULT_VALUE_ENUM,  NameT.VALID, false),
        new TestSchemaParams(DataT.NON_TEXTUAL,         NameT.VALID, false),

        // Without mandatory fields
        new TestSchemaParams(DataT.NO_FIELD_RECORD, NameT.VALID, true),
        new TestSchemaParams(DataT.NO_SYMBOLS_ENUM, NameT.VALID, true),
        new TestSchemaParams(DataT.NO_ITEMS_ARRAY,  NameT.VALID, true),
        new TestSchemaParams(DataT.NO_VALUES_MAP,   NameT.VALID, true),
        new TestSchemaParams(DataT.NO_SIZE_FIXED,   NameT.VALID, true),

        // Invalid field
        new TestSchemaParams(DataT.ERROR,                 DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.NO_ARRAY_FIELD_RECORD, NameT.VALID, true),
        new TestSchemaParams(DataT.NO_ARRAY_SYMBOLS_ENUM, NameT.VALID, true),
        new TestSchemaParams(DataT.NO_INT_SIZE_FIXED,     NameT.VALID, true),

        // Added after PIT
        new TestSchemaParams(DataT.LOGICAL_TYPE_DATE, NameT.VALID, false)
    );
  }

  private final TestSchemaParams params;

  public SchemaUpgradedTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
