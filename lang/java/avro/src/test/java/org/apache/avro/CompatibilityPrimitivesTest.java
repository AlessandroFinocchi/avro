package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.CompatibilityTest.*;
import static org.apache.avro.SchemaCompatibility.SchemaCompatibilityType.*;
import static org.apache.avro.SchemaCompatibility.SchemaIncompatibilityType.*;

@RunWith(Parameterized.class)
public class CompatibilityPrimitivesTest {

  @Parameterized.Parameters
  public static Collection<TestCompatibilityParams> getParameters() {
    return Arrays.asList(
        // Compatible Primitives
        new TestCompatibilityParams(DataT.LONG64,   DataT.INT32,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.FLOAT32,  DataT.INT32,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.DOUBLE64, DataT.INT32,    COMPATIBLE, null, false),

        new TestCompatibilityParams(DataT.FLOAT32,  DataT.LONG64,   COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.DOUBLE64, DataT.LONG64,   COMPATIBLE, null, false),

        new TestCompatibilityParams(DataT.DOUBLE64, DataT.FLOAT32,  COMPATIBLE, null, false),

        new TestCompatibilityParams(DataT.STRING,   DataT.BYTES,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.BYTES,    DataT.STRING,   COMPATIBLE, null, false),

        // Incompatible Primitives
        new TestCompatibilityParams(DataT.INT32,    DataT.DOUBLE64, INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.LONG64,   DataT.DOUBLE64, INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.FLOAT32,  DataT.DOUBLE64, INCOMPATIBLE, TYPE_MISMATCH, false),

        new TestCompatibilityParams(DataT.INT32,    DataT.FLOAT32,  INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.LONG64,   DataT.FLOAT32,  INCOMPATIBLE, TYPE_MISMATCH, false),

        new TestCompatibilityParams(DataT.INT32,    DataT.LONG64,   INCOMPATIBLE, TYPE_MISMATCH, false),

        new TestCompatibilityParams(DataT.INT32,    DataT.STRING,   INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.STRING,   DataT.INT32,    INCOMPATIBLE, TYPE_MISMATCH, false)

    );
  }

  private final TestCompatibilityParams params;

  public CompatibilityPrimitivesTest(TestCompatibilityParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testCompatibility(params);
  }
}
