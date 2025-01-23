package org.apache.avro;

import org.junit.Test;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

public class SchemaValidNamesTest {

  @Test public void testRecordSchema()    { testSchema(new TestParams(DataT.RECORD,   NameT.VALID, false)); }
  @Test public void testEnumSchema()      { testSchema(new TestParams(DataT.ENUM,     NameT.VALID, false)); }
  @Test public void testArraySchema()     { testSchema(new TestParams(DataT.ARRAY,    NameT.VALID, false)); }
  @Test public void testMapSchema()       { testSchema(new TestParams(DataT.MAP,      NameT.VALID, false)); }
  @Test public void testUnionSchema()     { testSchema(new TestParams(DataT.UNION,    NameT.VALID, false)); }
  @Test public void testFixedSchema()     { testSchema(new TestParams(DataT.FIXED,    NameT.VALID, false)); }
  @Test public void testStringSchema()    { testSchema(new TestParams(DataT.STRING,   NameT.VALID, false)); }
  @Test public void testByteSchema()      { testSchema(new TestParams(DataT.BYTES,    NameT.VALID, false)); }
  @Test public void testIntValidSchema()  { testSchema(new TestParams(DataT.INT32,    NameT.VALID, false)); }
  @Test public void testLongSchema()      { testSchema(new TestParams(DataT.LONG64,   NameT.VALID, false)); }
  @Test public void testFloatSchema()     { testSchema(new TestParams(DataT.FLOAT32,  NameT.VALID, false)); }
  @Test public void testDoubleSchema()    { testSchema(new TestParams(DataT.DOUBLE64, NameT.VALID, false)); }
  @Test public void testBooleanSchema()   { testSchema(new TestParams(DataT.BOOLEAN,  NameT.VALID, false)); }
  @Test public void testNullSchema()      { testSchema(new TestParams(DataT.NULL,     NameT.VALID, false)); }
}
