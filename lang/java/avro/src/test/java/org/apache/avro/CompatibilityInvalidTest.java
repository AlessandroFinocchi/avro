package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.CompatibilityTest.TestCompatibilityParams;
import static org.apache.avro.CompatibilityTest.testCompatibility;
import static org.apache.avro.SchemaCompatibility.SchemaCompatibilityType.INCOMPATIBLE;
import static org.apache.avro.Utils.DataT;
import static org.apache.avro.Utils.getExpectedSchema;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(Parameterized.class)
public class CompatibilityInvalidTest {

  @Parameterized.Parameters
  public static Collection<TestCompatibilityParams> getParameters() {
    return Arrays.asList(
        // Null Schemas
        new TestCompatibilityParams((Schema) null, null,                           INCOMPATIBLE, null, true),
        new TestCompatibilityParams(null, getExpectedSchema(DataT.INT32), INCOMPATIBLE, null, true),
        new TestCompatibilityParams(getExpectedSchema(DataT.INT32), null, INCOMPATIBLE, null, true),

        // Invalid Schemas
        new TestCompatibilityParams(invalidSchema(DataT.INT32), invalidSchema(DataT.INT32),     INCOMPATIBLE, null, true),
        new TestCompatibilityParams(invalidSchema(DataT.INT32), getExpectedSchema(DataT.INT32), INCOMPATIBLE, null, true),
        new TestCompatibilityParams(getExpectedSchema(DataT.INT32), invalidSchema(DataT.INT32), INCOMPATIBLE, null, true)

    );
  }

  private static Schema invalidSchema(DataT dataT) {
    Schema schema = getExpectedSchema(dataT);
    if(schema == null) { throw new IllegalArgumentException("Null Schema"); }
    Schema invalidSchema = Mockito.spy(schema);

    Mockito.when(invalidSchema.getType()).thenThrow(new NullPointerException());

    return invalidSchema;
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
