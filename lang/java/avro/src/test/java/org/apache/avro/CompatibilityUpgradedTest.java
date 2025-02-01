package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.CompatibilityTest.TestCompatibilityParams;
import static org.apache.avro.CompatibilityTest.testCompatibility;
import static org.apache.avro.SchemaCompatibility.SchemaCompatibilityType.*;
import static org.apache.avro.SchemaCompatibility.SchemaIncompatibilityType.*;
import static org.apache.avro.Utils.DataT;

@RunWith(Parameterized.class)
public class CompatibilityUpgradedTest {

  @Parameterized.Parameters
  public static Collection<TestCompatibilityParams> getParameters() {
    return Arrays.asList(
        // Added after jacoco
        new TestCompatibilityParams(DataT.BOOLEAN,  DataT.INT32,  INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.BYTES,    DataT.INT32,  INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.DOUBLE64, DataT.STRING, INCOMPATIBLE, TYPE_MISMATCH, false),

        new TestCompatibilityParams(DataT.ARRAY,    DataT.INT32,  INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.MAP,      DataT.INT32,  INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.FIXED,    DataT.INT32,  INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.ENUM,     DataT.INT32,  INCOMPATIBLE, TYPE_MISMATCH, false),
        new TestCompatibilityParams(DataT.RECORD,   DataT.INT32,  INCOMPATIBLE, TYPE_MISMATCH, false)


    );
  }

  private final TestCompatibilityParams params;

  public CompatibilityUpgradedTest(TestCompatibilityParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testCompatibility(params);
  }
}
