package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaWOFieldTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Arrays.asList(
        new TestSchemaParams(DataT.RECORD,    DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.ENUM,      DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.ARRAY,     DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.MAP,       DataState.WITHOUT_MANDATORY_FIELDS, true),
        //new TestSchemaParams(DataT.UNION, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.FIXED,     DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.STRING,  DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.BYTES,     DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.INT32,     DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.LONG64,    DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.FLOAT32,   DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.DOUBLE64,  DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.BOOLEAN,   DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestSchemaParams(DataT.NULL,      DataState.WITHOUT_MANDATORY_FIELDS, true)
    );
  }

  private final TestSchemaParams params;

  public SchemaWOFieldTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
