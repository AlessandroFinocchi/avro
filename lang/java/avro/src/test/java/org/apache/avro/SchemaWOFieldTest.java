package org.apache.avro;

import org.junit.Test;

import static org.apache.avro.SchemaTests.testSchema;
import static org.apache.avro.Utils.DataState;
import static org.apache.avro.Utils.DataT;

public class SchemaWOFieldTest {

  @Test public void testRecordSchema()    { testSchema(new SchemaTests.TestParams(DataT.RECORD,   DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testEnumSchema()      { testSchema(new SchemaTests.TestParams(DataT.ENUM,     DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testArraySchema()     { testSchema(new SchemaTests.TestParams(DataT.ARRAY,    DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testMapSchema()       { testSchema(new SchemaTests.TestParams(DataT.MAP,      DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  //@Test public void testUnionSchema()     { testSchema(new SchemaTests.TestParams(DataT.UNION,    DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testFixedSchema()     { testSchema(new SchemaTests.TestParams(DataT.FIXED,    DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testStringSchema()    { testSchema(new SchemaTests.TestParams(DataT.STRING,   DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testByteSchema()      { testSchema(new SchemaTests.TestParams(DataT.BYTES,    DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testIntValidSchema()  { testSchema(new SchemaTests.TestParams(DataT.INT32,    DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testLongSchema()      { testSchema(new SchemaTests.TestParams(DataT.LONG64,   DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testFloatSchema()     { testSchema(new SchemaTests.TestParams(DataT.FLOAT32,  DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testDoubleSchema()    { testSchema(new SchemaTests.TestParams(DataT.DOUBLE64, DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testBooleanSchema()   { testSchema(new SchemaTests.TestParams(DataT.BOOLEAN,  DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testNullSchema()      { testSchema(new SchemaTests.TestParams(DataT.NULL,     DataState.WITHOUT_MANDATORY_FIELDS, true)); }

  // Added after jacoco
  @Test public void testErrorSchema()         { testSchema(new SchemaTests.TestParams(DataT.ERROR, DataState.WITHOUT_MANDATORY_FIELDS, true)); }
  @Test public void testNoFieldRecordSchema() { testSchema(new SchemaTests.TestParams(DataT.NO_FIELD_RECORD,  Utils.NameT.VALID, true)); }
  @Test public void testNoSymbolsEnumSchema() { testSchema(new SchemaTests.TestParams(DataT.NO_SYMBOLS_ENUM,  Utils.NameT.VALID, true)); }
  @Test public void testNoItemsArraySchema()  { testSchema(new SchemaTests.TestParams(DataT.NO_ITEMS_ARRAY,   Utils.NameT.VALID, true)); }
  @Test public void testNoValuesMapSchema()   { testSchema(new SchemaTests.TestParams(DataT.NO_VALUES_MAP,    Utils.NameT.VALID, true)); }
  @Test public void testNoSizeFixedSchema()   { testSchema(new SchemaTests.TestParams(DataT.NO_SIZE_FIXED,    Utils.NameT.VALID, true)); }
}
