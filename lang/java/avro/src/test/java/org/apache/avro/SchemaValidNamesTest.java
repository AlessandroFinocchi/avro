package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaValidNamesTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Arrays.asList(
        new TestSchemaParams(DataT.RECORD,    NameT.VALID, false),
        new TestSchemaParams(DataT.ENUM,      NameT.VALID, false),
        new TestSchemaParams(DataT.ARRAY,     NameT.VALID, false),
        new TestSchemaParams(DataT.MAP,       NameT.VALID, false),
        new TestSchemaParams(DataT.UNION,     NameT.VALID, false),
        new TestSchemaParams(DataT.FIXED,     NameT.VALID, false),
        new TestSchemaParams(DataT.STRING,    NameT.VALID, false),
        new TestSchemaParams(DataT.BYTES,     NameT.VALID, false),
        new TestSchemaParams(DataT.INT32,     NameT.VALID, false),
        new TestSchemaParams(DataT.LONG64,    NameT.VALID, false),
        new TestSchemaParams(DataT.FLOAT32,   NameT.VALID, false),
        new TestSchemaParams(DataT.DOUBLE64,  NameT.VALID, false),
        new TestSchemaParams(DataT.BOOLEAN,   NameT.VALID, false),
        new TestSchemaParams(DataT.NULL,      NameT.VALID, false),

        // Added after jacoco
        new TestSchemaParams(DataT.ERROR, NameT.VALID, false)
    );
  }

  private final TestSchemaParams params;

  public SchemaValidNamesTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
