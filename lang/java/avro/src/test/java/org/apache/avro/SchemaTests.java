package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;

import static org.apache.avro.Utils.*;

public class SchemaTests {

  public static class TestParams {
    JsonNode schemaJsonNode;
    Schema.Names schemaNames;
    Schema expectedSchema;
    Class<Exception> expectedException;

    public TestParams(Utils.DataT dataT, Utils.NameT schemaNames, boolean isExpectedException) {
      try {
        this.schemaJsonNode = getJsonNode(dataT);
        this.schemaNames = getNames(schemaNames);
        this.expectedSchema = getExpectedSchema(dataT);
        this.expectedException = isExpectedException ? Exception.class : null;
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }
  }

  static void testSchema(TestParams params) {
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
