package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaInvalidNamesTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Arrays.asList(
        new TestSchemaParams(DataT.RECORD,    NameT.INVALID, true),
        new TestSchemaParams(DataT.ENUM,      NameT.INVALID, true),
        new TestSchemaParams(DataT.ARRAY,     NameT.INVALID, true),
        new TestSchemaParams(DataT.MAP,       NameT.INVALID, true),
        new TestSchemaParams(DataT.UNION,     NameT.INVALID, true),
        new TestSchemaParams(DataT.FIXED,     NameT.INVALID, true),
        new TestSchemaParams(DataT.STRING,    NameT.INVALID, true),
        new TestSchemaParams(DataT.BYTES,     NameT.INVALID, true),
        new TestSchemaParams(DataT.INT32,     NameT.INVALID, true),
        new TestSchemaParams(DataT.LONG64,    NameT.INVALID, true),
        new TestSchemaParams(DataT.FLOAT32,   NameT.INVALID, true),
        new TestSchemaParams(DataT.DOUBLE64,  NameT.INVALID, true),
        new TestSchemaParams(DataT.BOOLEAN,   NameT.INVALID, true),
        new TestSchemaParams(DataT.NULL,      NameT.INVALID, true),

        // Added after jacoco
        new TestSchemaParams(DataT.ERROR, NameT.INVALID, true)
    );
  }

  private final TestSchemaParams params;

  public SchemaInvalidNamesTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
