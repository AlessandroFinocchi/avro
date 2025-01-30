package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaInvalidFieldTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Arrays.asList(
        new TestSchemaParams(DataT.RECORD,    DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.ENUM,      DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.ARRAY,     DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.MAP,       DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.UNION,     DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.FIXED,     DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.STRING,    DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.BYTES,     DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.INT32,     DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.LONG64,    DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.FLOAT32,   DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.DOUBLE64,  DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.BOOLEAN,   DataState.INVALID_MANDATORY_FIELD, true),
        new TestSchemaParams(DataT.NULL,      DataState.INVALID_MANDATORY_FIELD, true),

        // Added after jacoco
        new TestSchemaParams(DataT.ERROR, DataState.INVALID_MANDATORY_FIELD, true)
    );
  }

  private final TestSchemaParams params;

  public SchemaInvalidFieldTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
