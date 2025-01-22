package org.apache.avro;

import org.junit.Assert;
import org.junit.Test;

public class SchemaTest {
  public static Object[][] testData() {
    return new Object[][] {
        { Schema.Type.INT,  null },
        { Schema.Type.LONG, null }
    };
  }

  public SchemaTest() {}

  @Test public void testIntSchema() { testSchema(0); }
  @Test public void testLongSchema() { testSchema(1); }

  private void testSchema(int index) {
    Schema.Type schemaType = (Schema.Type) testData()[index][0];
    Class<Exception> expectedException = (Class<Exception>) testData()[index][1];

    if (expectedException != null) Assert.assertThrows(expectedException, () -> Schema.create(schemaType));
    else{
      try {
        Schema actualSchema = Schema.create(schemaType);
        Schema expectedSchema = Schema.create(schemaType);

        Assert.assertEquals(expectedSchema, actualSchema);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
