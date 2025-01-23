package org.apache.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;

import static org.apache.avro.Utils.*;


public class SchemaValidNamesTest {

  private static class TestParams {
    JsonNode schemaJsonNode;
    Schema.Names schemaNames;
    Schema expectedSchema;
    Class<Exception> expectedException;

    private TestParams(DataT dataT, NameT schemaNames, Class<Exception> expectedException) {
      try {
        this.schemaJsonNode = getJsonNode(dataT);
        this.schemaNames = getNames(schemaNames);
        this.expectedSchema = getExpectedSchema(dataT);
        this.expectedException = expectedException;
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Test public void testRecordSchema()    { testSchema(new TestParams(DataT.RECORD,   NameT.VALID, null)); }
  @Test public void testEnumSchema()      { testSchema(new TestParams(DataT.ENUM,     NameT.VALID, null)); }
  @Test public void testArraySchema()     { testSchema(new TestParams(DataT.ARRAY,    NameT.VALID, null)); }
  @Test public void testMapSchema()       { testSchema(new TestParams(DataT.MAP,      NameT.VALID, null)); }
  @Test public void testUnionSchema()     { testSchema(new TestParams(DataT.UNION,    NameT.VALID, null)); }
  @Test public void testFixedSchema()     { testSchema(new TestParams(DataT.FIXED,    NameT.VALID, null)); }
  @Test public void testStringSchema()    { testSchema(new TestParams(DataT.STRING,   NameT.VALID, null)); }
  @Test public void testByteSchema()      { testSchema(new TestParams(DataT.BYTES,    NameT.VALID, null)); }
  @Test public void testIntValidSchema()  { testSchema(new TestParams(DataT.INT32,    NameT.VALID, null)); }
  @Test public void testLongSchema()      { testSchema(new TestParams(DataT.LONG64,   NameT.VALID, null)); }
  @Test public void testFloatSchema()     { testSchema(new TestParams(DataT.FLOAT32,  NameT.VALID, null)); }
  @Test public void testDoubleSchema()    { testSchema(new TestParams(DataT.DOUBLE64, NameT.VALID, null)); }
  @Test public void testBooleanSchema()   { testSchema(new TestParams(DataT.BOOLEAN,  NameT.VALID, null)); }
  @Test public void testNullSchema()      { testSchema(new TestParams(DataT.NULL,     NameT.VALID, null)); }

  private void testSchema(TestParams params) {
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
