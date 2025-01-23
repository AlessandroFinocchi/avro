package org.apache.avro;

import org.junit.Test;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

public class SchemaInvalidNamesTest {

  @Test public void testRecordSchema()    { testSchema(new TestParams(DataT.RECORD,   NameT.INVALID, true)); }
  @Test public void testEnumSchema()      { testSchema(new TestParams(DataT.ENUM,     NameT.INVALID, true)); }
  @Test public void testArraySchema()     { testSchema(new TestParams(DataT.ARRAY,    NameT.INVALID, true)); }
  @Test public void testMapSchema()       { testSchema(new TestParams(DataT.MAP,      NameT.INVALID, true)); }
  @Test public void testUnionSchema()     { testSchema(new TestParams(DataT.UNION,    NameT.INVALID, true)); }
  @Test public void testFixedSchema()     { testSchema(new TestParams(DataT.FIXED,    NameT.INVALID, true)); }
  @Test public void testStringSchema()    { testSchema(new TestParams(DataT.STRING,   NameT.INVALID, true)); }
  @Test public void testByteSchema()      { testSchema(new TestParams(DataT.BYTES,    NameT.INVALID, true)); }
  @Test public void testIntValidSchema()  { testSchema(new TestParams(DataT.INT32,    NameT.INVALID, true)); }
  @Test public void testLongSchema()      { testSchema(new TestParams(DataT.LONG64,   NameT.INVALID, true)); }
  @Test public void testFloatSchema()     { testSchema(new TestParams(DataT.FLOAT32,  NameT.INVALID, true)); }
  @Test public void testDoubleSchema()    { testSchema(new TestParams(DataT.DOUBLE64, NameT.INVALID, true)); }
  @Test public void testBooleanSchema()   { testSchema(new TestParams(DataT.BOOLEAN,  NameT.INVALID, true)); }
  @Test public void testNullSchema()      { testSchema(new TestParams(DataT.NULL,     NameT.INVALID, true)); }
}
