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
  public static Collection<TestParams> getParameters() {
    return Arrays.asList(
        new TestParams(DataT.RECORD, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.ENUM, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.ARRAY, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.MAP, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.UNION, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.FIXED, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.STRING, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.BYTES, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.INT32, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.LONG64, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.FLOAT32, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.DOUBLE64, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.BOOLEAN, DataState.INVALID_MANDATORY_FIELD, true),
        new TestParams(DataT.NULL, DataState.INVALID_MANDATORY_FIELD, true),

        // Added after jacoco
        new TestParams(DataT.ERROR, DataState.INVALID_MANDATORY_FIELD, true)
    );
  }

  private final TestParams params;

  public SchemaInvalidFieldTest(TestParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
