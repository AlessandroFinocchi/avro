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

    public TestParams(DataT dataT, NameT schemaNames, boolean isExpectedException) {
      this(dataT, DataState.VALID, schemaNames, isExpectedException);
    }

    public TestParams(DataT dataT, DataState dataState, boolean isExpectedException) {
      this(dataT, dataState, NameT.VALID, isExpectedException);
    }

    public TestParams(DataT dataT, DataState dataState, NameT schemaNames, boolean isExpectedException) {
      try {
        this.schemaJsonNode = getJsonNode(dataT, dataState);
        this.schemaNames = getNames(schemaNames);
        this.expectedSchema = isExpectedException? null : getExpectedSchema(dataT);
        this.expectedException = isExpectedException ? Exception.class : null;
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }

  }

  public static void testSchema(TestParams params) {
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
        if(expectedSchema != null) Assert.assertEquals(LogicalTypes.fromSchemaIgnoreInvalid(expectedSchema), actualSchema.getLogicalType());
      } catch (Exception e) {
        Assert.fail("Unexpected exception");
      }
    }
  }
}
