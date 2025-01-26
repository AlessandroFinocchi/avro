package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaNullNamesTest {

  @Parameterized.Parameters
  public static Collection<TestParams> getParameters() {
    return Arrays.asList(
        new TestParams(DataT.RECORD, NameT.NULL, true),
        new TestParams(DataT.ENUM, NameT.NULL, true),
        new TestParams(DataT.ARRAY, NameT.NULL, true),
        new TestParams(DataT.MAP, NameT.NULL, true),
        new TestParams(DataT.UNION, NameT.NULL, true),
        new TestParams(DataT.FIXED, NameT.NULL, true),
        new TestParams(DataT.STRING, NameT.NULL, true),
        new TestParams(DataT.BYTES, NameT.NULL, true),
        new TestParams(DataT.INT32, NameT.NULL, true),
        new TestParams(DataT.LONG64, NameT.NULL, true),
        new TestParams(DataT.FLOAT32, NameT.NULL, true),
        new TestParams(DataT.DOUBLE64, NameT.NULL, true),
        new TestParams(DataT.BOOLEAN, NameT.NULL, true),
        new TestParams(DataT.NULL, NameT.NULL, true),

        // Added after jacoco
        new TestParams(DataT.ERROR, NameT.NULL, true)
    );
  }

  private final TestParams params;

  public SchemaNullNamesTest(TestParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
