package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.CompatibilityTest.*;
import static org.apache.avro.SchemaCompatibility.SchemaCompatibilityType.*;

@RunWith(Parameterized.class)
public class CompatibilityCompatibleTest {

  @Parameterized.Parameters
  public static Collection<TestCompatibilityParams> getParameters() {
    return Arrays.asList(
        new TestCompatibilityParams(DataT.RECORD,   DataT.RECORD,   COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.ENUM,     DataT.ENUM,     COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.ARRAY,    DataT.ARRAY,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.MAP,      DataT.MAP,      COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.UNION,    DataT.UNION,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.FIXED,    DataT.FIXED,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.STRING,   DataT.STRING,   COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.BYTES,    DataT.BYTES,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.INT32,    DataT.INT32,    COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.LONG64,   DataT.LONG64,   COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.FLOAT32,  DataT.FLOAT32,  COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.DOUBLE64, DataT.DOUBLE64, COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.BOOLEAN,  DataT.BOOLEAN,  COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.NULL,     DataT.NULL,     COMPATIBLE, null, false),
        new TestCompatibilityParams(DataT.ERROR,    DataT.ERROR,    COMPATIBLE, null, false)
    );
  }

  private final TestCompatibilityParams params;

  public CompatibilityCompatibleTest(TestCompatibilityParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testCompatibility(params);
  }
}
