package org.apache.avro;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;

@RunWith(Parameterized.class)
public class SchemaTest {
  @Parameterized.Parameters
  public static Collection<Object[]> testData() {
    return Arrays.asList(new Object[][] {
        { DataType.NULL, null },
        { DataType.BOOLEAN, null }
    });
  }

  private final DataType type;
  private final Class<Exception> expectedException;

  public SchemaTest(DataType type, Class<Exception> expectedException) {
    this.type = type;
    this.expectedException = expectedException;
  }

  @Test
  public void testSchema() {
    if (expectedException != null) Assert.assertThrows(expectedException, () -> Schema.create(getSchemaType(type)));
    else{
      String schemaString = getSchemaString(type);
      Schema.Parser parser = new Schema.Parser();
      Schema actualSchema = parser.parse(schemaString);
      Schema expectedSchema = Schema.create(getSchemaType(type));

      Assert.assertEquals(expectedSchema, actualSchema);
    }
  }
}
