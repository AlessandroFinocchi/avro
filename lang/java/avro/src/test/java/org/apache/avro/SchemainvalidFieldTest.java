package org.apache.avro;

import org.junit.Test;

import static org.apache.avro.SchemaTests.testSchema;
import static org.apache.avro.Utils.DataState;
import static org.apache.avro.Utils.DataT;

public class SchemainvalidFieldTest {

  @Test public void testRecordSchema()    { testSchema(new SchemaTests.TestParams(DataT.RECORD,   DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testEnumSchema()      { testSchema(new SchemaTests.TestParams(DataT.ENUM,     DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testArraySchema()     { testSchema(new SchemaTests.TestParams(DataT.ARRAY,    DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testMapSchema()       { testSchema(new SchemaTests.TestParams(DataT.MAP,      DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testUnionSchema()     { testSchema(new SchemaTests.TestParams(DataT.UNION,    DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testFixedSchema()     { testSchema(new SchemaTests.TestParams(DataT.FIXED,    DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testStringSchema()    { testSchema(new SchemaTests.TestParams(DataT.STRING,   DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testByteSchema()      { testSchema(new SchemaTests.TestParams(DataT.BYTES,    DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testIntValidSchema()  { testSchema(new SchemaTests.TestParams(DataT.INT32,    DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testLongSchema()      { testSchema(new SchemaTests.TestParams(DataT.LONG64,   DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testFloatSchema()     { testSchema(new SchemaTests.TestParams(DataT.FLOAT32,  DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testDoubleSchema()    { testSchema(new SchemaTests.TestParams(DataT.DOUBLE64, DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testBooleanSchema()   { testSchema(new SchemaTests.TestParams(DataT.BOOLEAN,  DataState.INVALID_MANDATORY_FIELD, true)); }
  @Test public void testNullSchema()      { testSchema(new SchemaTests.TestParams(DataT.NULL,     DataState.INVALID_MANDATORY_FIELD, true)); }
}
