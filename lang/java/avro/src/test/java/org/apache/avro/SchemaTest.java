package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;

import static org.apache.avro.Utils.*;


public class SchemaTest {
  private static class TestSchemaParameters {
    JsonNode schemaJsonNode;
    Schema.Names schemaNames;
    Schema expectedSchema;
    Class<Exception> expectedException;

    private TestSchemaParameters(DataType dataType, NameType schemaNames, Class<Exception> expectedException) throws JsonProcessingException {
      this.schemaJsonNode = getJsonNode(dataType);
      this.schemaNames = getNames(schemaNames);
      this.expectedSchema = Schema.create(getSchemaType(dataType));
      this.expectedException = expectedException;

    }
  }

  private TestSchemaParameters[] testData() {
    try{
      return new TestSchemaParameters[] {
          new TestSchemaParameters(DataType.INT32, NameType.VALID, null),
          new TestSchemaParameters(DataType.INT32, NameType.NULL, Exception.class)
      };
    }
    catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

  }

  @Test public void testIntValidSchema() { testSchema(0); }
  @Test public void testIntNullSchema() { testSchema(1); }

  private void testSchema(int index) {
    TestSchemaParameters params = testData()[index];
    JsonNode schemaJsonNode = params.schemaJsonNode;
    Schema.Names schemaNames = params.schemaNames;
    Schema expectedSchema = params.expectedSchema;
    Class<Exception> expectedException = params.expectedException;

    if (expectedException != null)
      Assert.assertThrows(expectedException, () -> Schema.parse(schemaJsonNode, schemaNames));
    else{
      try {
        Schema actualSchema = Schema.parse(schemaJsonNode, schemaNames);

        Assert.assertEquals(expectedSchema, actualSchema);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
