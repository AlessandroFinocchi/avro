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
  public static Collection<TestParams> getParameters() {
    return Arrays.asList(
        new TestParams(DataT.RECORD, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.ENUM, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.ARRAY, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.MAP, DataState.WITHOUT_MANDATORY_FIELDS, true),
        //new TestParams(DataT.UNION, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.FIXED, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.STRING, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.BYTES, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.INT32, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.LONG64, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.FLOAT32, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.DOUBLE64, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.BOOLEAN, DataState.WITHOUT_MANDATORY_FIELDS, true),
        new TestParams(DataT.NULL, DataState.WITHOUT_MANDATORY_FIELDS, true)
    );
  }

  private final TestParams params;

  public SchemaWOFieldTest(TestParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
