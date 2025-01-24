package org.apache.avro;

import org.junit.Test;

import static org.apache.avro.SchemaTests.testSchema;
import static org.apache.avro.Utils.DataState;
import static org.apache.avro.Utils.DataT;

public class SchemaNullTest {

  @Test public void testRecordSchema()    { testSchema(new SchemaTests.TestParams(DataT.RECORD,   DataState.NULL, true)); }
  @Test public void testEnumSchema()      { testSchema(new SchemaTests.TestParams(DataT.ENUM,     DataState.NULL, true)); }
  @Test public void testArraySchema()     { testSchema(new SchemaTests.TestParams(DataT.ARRAY,    DataState.NULL, true)); }
  @Test public void testMapSchema()       { testSchema(new SchemaTests.TestParams(DataT.MAP,      DataState.NULL, true)); }
  @Test public void testUnionSchema()     { testSchema(new SchemaTests.TestParams(DataT.UNION,    DataState.NULL, true)); }
  @Test public void testFixedSchema()     { testSchema(new SchemaTests.TestParams(DataT.FIXED,    DataState.NULL, true)); }
  @Test public void testStringSchema()    { testSchema(new SchemaTests.TestParams(DataT.STRING,   DataState.NULL, true)); }
  @Test public void testByteSchema()      { testSchema(new SchemaTests.TestParams(DataT.BYTES,    DataState.NULL, true)); }
  @Test public void testIntValidSchema()  { testSchema(new SchemaTests.TestParams(DataT.INT32,    DataState.NULL, true)); }
  @Test public void testLongSchema()      { testSchema(new SchemaTests.TestParams(DataT.LONG64,   DataState.NULL, true)); }
  @Test public void testFloatSchema()     { testSchema(new SchemaTests.TestParams(DataT.FLOAT32,  DataState.NULL, true)); }
  @Test public void testDoubleSchema()    { testSchema(new SchemaTests.TestParams(DataT.DOUBLE64, DataState.NULL, true)); }
  @Test public void testBooleanSchema()   { testSchema(new SchemaTests.TestParams(DataT.BOOLEAN,  DataState.NULL, true)); }
  @Test public void testNullSchema()      { testSchema(new SchemaTests.TestParams(DataT.NULL,     DataState.NULL, true)); }
}
