package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaNullNamesTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Arrays.asList(
        new TestSchemaParams(DataT.RECORD,    NameT.NULL, true),
        new TestSchemaParams(DataT.ENUM,      NameT.NULL, true),
        new TestSchemaParams(DataT.ARRAY,     NameT.NULL, true),
        new TestSchemaParams(DataT.MAP,       NameT.NULL, true),
        new TestSchemaParams(DataT.UNION,     NameT.NULL, true),
        new TestSchemaParams(DataT.FIXED,     NameT.NULL, true),
        new TestSchemaParams(DataT.STRING,    NameT.NULL, true),
        new TestSchemaParams(DataT.BYTES,     NameT.NULL, true),
        new TestSchemaParams(DataT.INT32,     NameT.NULL, true),
        new TestSchemaParams(DataT.LONG64,    NameT.NULL, true),
        new TestSchemaParams(DataT.FLOAT32,   NameT.NULL, true),
        new TestSchemaParams(DataT.DOUBLE64,  NameT.NULL, true),
        new TestSchemaParams(DataT.BOOLEAN,   NameT.NULL, true),
        new TestSchemaParams(DataT.NULL,      NameT.NULL, true),

        // Added after jacoco
        new TestSchemaParams(DataT.ERROR, NameT.NULL, true)
    );
  }

  private final TestSchemaParams params;

  public SchemaNullNamesTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
