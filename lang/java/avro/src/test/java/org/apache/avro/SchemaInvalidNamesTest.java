package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaInvalidNamesTest {

  @Parameterized.Parameters
  public static Collection<TestParams> getParameters() {
    return Arrays.asList(
        new TestParams(DataT.RECORD, NameT.INVALID, true),
        new TestParams(DataT.ENUM, NameT.INVALID, true),
        new TestParams(DataT.ARRAY, NameT.INVALID, true),
        new TestParams(DataT.MAP, NameT.INVALID, true),
        new TestParams(DataT.UNION, NameT.INVALID, true),
        new TestParams(DataT.FIXED, NameT.INVALID, true),
        new TestParams(DataT.STRING, NameT.INVALID, true),
        new TestParams(DataT.BYTES, NameT.INVALID, true),
        new TestParams(DataT.INT32, NameT.INVALID, true),
        new TestParams(DataT.LONG64, NameT.INVALID, true),
        new TestParams(DataT.FLOAT32, NameT.INVALID, true),
        new TestParams(DataT.DOUBLE64, NameT.INVALID, true),
        new TestParams(DataT.BOOLEAN, NameT.INVALID, true),
        new TestParams(DataT.NULL, NameT.INVALID, true),

        // Added after jacoco
        new TestParams(DataT.ERROR, NameT.INVALID, true)
    );
  }

  private final TestParams params;

  public SchemaInvalidNamesTest(TestParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
