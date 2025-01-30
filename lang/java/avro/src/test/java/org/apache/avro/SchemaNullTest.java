package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaNullTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Arrays.asList(
        new TestSchemaParams(DataT.RECORD,    DataState.NULL, true),
        new TestSchemaParams(DataT.ENUM,      DataState.NULL, true),
        new TestSchemaParams(DataT.ARRAY,     DataState.NULL, true),
        new TestSchemaParams(DataT.MAP,       DataState.NULL, true),
        new TestSchemaParams(DataT.UNION,     DataState.NULL, true),
        new TestSchemaParams(DataT.FIXED,     DataState.NULL, true),
        new TestSchemaParams(DataT.STRING,    DataState.NULL, true),
        new TestSchemaParams(DataT.BYTES,     DataState.NULL, true),
        new TestSchemaParams(DataT.INT32,     DataState.NULL, true),
        new TestSchemaParams(DataT.LONG64,    DataState.NULL, true),
        new TestSchemaParams(DataT.FLOAT32,   DataState.NULL, true),
        new TestSchemaParams(DataT.DOUBLE64,  DataState.NULL, true),
        new TestSchemaParams(DataT.BOOLEAN,   DataState.NULL, true),
        new TestSchemaParams(DataT.NULL,      DataState.NULL, true),

        // Added after jacoco
        new TestSchemaParams(DataT.ERROR, DataState.NULL, true)
    );
  }

  private final TestSchemaParams params;

  public SchemaNullTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
