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
  public static Collection<TestParams> getParameters() {
    return Arrays.asList(
        new TestParams(DataT.RECORD, DataState.NULL, true),
        new TestParams(DataT.ENUM, DataState.NULL, true),
        new TestParams(DataT.ARRAY, DataState.NULL, true),
        new TestParams(DataT.MAP, DataState.NULL, true),
        new TestParams(DataT.UNION, DataState.NULL, true),
        new TestParams(DataT.FIXED, DataState.NULL, true),
        new TestParams(DataT.STRING, DataState.NULL, true),
        new TestParams(DataT.BYTES, DataState.NULL, true),
        new TestParams(DataT.INT32, DataState.NULL, true),
        new TestParams(DataT.LONG64, DataState.NULL, true),
        new TestParams(DataT.FLOAT32, DataState.NULL, true),
        new TestParams(DataT.DOUBLE64, DataState.NULL, true),
        new TestParams(DataT.BOOLEAN, DataState.NULL, true),
        new TestParams(DataT.NULL, DataState.NULL, true),

        // Added after jacoco
        new TestParams(DataT.ERROR, DataState.NULL, true)
    );
  }

  private final TestParams params;

  public SchemaNullTest(TestParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
