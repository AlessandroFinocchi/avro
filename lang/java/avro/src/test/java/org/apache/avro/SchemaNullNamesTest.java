package org.apache.avro;

import org.junit.Test;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

public class SchemaNullNamesTest {

  @Test public void testRecordSchema()    { testSchema(new TestParams(DataT.RECORD,   NameT.NULL, true)); }
  @Test public void testEnumSchema()      { testSchema(new TestParams(DataT.ENUM,     NameT.NULL, true)); }
  @Test public void testArraySchema()     { testSchema(new TestParams(DataT.ARRAY,    NameT.NULL, true)); }
  @Test public void testMapSchema()       { testSchema(new TestParams(DataT.MAP,      NameT.NULL, true)); }
  @Test public void testUnionSchema()     { testSchema(new TestParams(DataT.UNION,    NameT.NULL, true)); }
  @Test public void testFixedSchema()     { testSchema(new TestParams(DataT.FIXED,    NameT.NULL, true)); }
  @Test public void testStringSchema()    { testSchema(new TestParams(DataT.STRING,   NameT.NULL, true)); }
  @Test public void testByteSchema()      { testSchema(new TestParams(DataT.BYTES,    NameT.NULL, true)); }
  @Test public void testIntValidSchema()  { testSchema(new TestParams(DataT.INT32,    NameT.NULL, true)); }
  @Test public void testLongSchema()      { testSchema(new TestParams(DataT.LONG64,   NameT.NULL, true)); }
  @Test public void testFloatSchema()     { testSchema(new TestParams(DataT.FLOAT32,  NameT.NULL, true)); }
  @Test public void testDoubleSchema()    { testSchema(new TestParams(DataT.DOUBLE64, NameT.NULL, true)); }
  @Test public void testBooleanSchema()   { testSchema(new TestParams(DataT.BOOLEAN,  NameT.NULL, true)); }
  @Test public void testNullSchema()      { testSchema(new TestParams(DataT.NULL,     NameT.NULL, true)); }

  // Added after jacoco
  @Test public void testErrorSchema()     { testSchema(new TestParams(DataT.ERROR,    NameT.NULL, true)); }
}
