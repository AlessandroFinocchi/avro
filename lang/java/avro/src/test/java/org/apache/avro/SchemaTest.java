package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
        { DataType.NULL, NameType.NULL, Exception.class },
        { DataType.BOOLEAN, NameType.NULL, Exception.class }
    });
  }
  private JsonNode schemaJsonNode;
  private Schema.Names names;
  private Schema expectedSchema;
  private Class<Exception> expectedException;

  public SchemaTest(DataType dataType, NameType nameType, Class<Exception> expectedException) {
    try {
      this.schemaJsonNode = getJsonNode(dataType);
      this.names = getNames(nameType);
      this.expectedSchema = getExpectedSchema(dataType, nameType);
      this.expectedException = expectedException;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSchema() {
    if (expectedException != null) Assert.assertThrows(expectedException, () -> Schema.parse(this.schemaJsonNode, this.names));
    else{
      try {
        Schema actualSchema = Schema.parse(this.schemaJsonNode, this.names);

        Assert.assertEquals(expectedSchema, actualSchema);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
