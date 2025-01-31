package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.CompatibilityTest.TestCompatibilityParams;
import static org.apache.avro.CompatibilityTest.testCompatibility;
import static org.apache.avro.SchemaCompatibility.SchemaCompatibilityType.INCOMPATIBLE;
import static org.apache.avro.Utils.DataT;
import static org.apache.avro.Utils.validSchema;

@RunWith(Parameterized.class)
public class CompatibilityInvalidTest {

  @Parameterized.Parameters
  public static Collection<TestCompatibilityParams> getParameters() {
    return Arrays.asList(
        // Null Schemas
        new TestCompatibilityParams((Schema) null, null,            INCOMPATIBLE, null, true),
        new TestCompatibilityParams(null, validSchema(DataT.INT32), INCOMPATIBLE, null, true),
        new TestCompatibilityParams(validSchema(DataT.INT32), null, INCOMPATIBLE, null, true),

        // Invalid Schemas
        new TestCompatibilityParams(invalidSchema(), invalidSchema(), INCOMPATIBLE, null, true),
        new TestCompatibilityParams(invalidSchema(), validSchema(DataT.INT32),   INCOMPATIBLE, null, true),
        new TestCompatibilityParams(validSchema(DataT.INT32),   invalidSchema(), INCOMPATIBLE, null, true)
    );
  }

  private static Schema invalidSchema() {
    return new InvalidSchema(Schema.Type.INT);
  }

  private final TestCompatibilityParams params;

  public CompatibilityInvalidTest(TestCompatibilityParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testCompatibility(params);
  }
}
