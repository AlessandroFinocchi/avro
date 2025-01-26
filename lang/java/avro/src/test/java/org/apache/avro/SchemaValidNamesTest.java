package org.apache.avro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.*;

@RunWith(Parameterized.class)
public class SchemaValidNamesTest {

  @Parameterized.Parameters
  public static Collection<TestParams> getParameters() {
    return Arrays.asList(
        new TestParams(DataT.RECORD, NameT.VALID, false),
        new TestParams(DataT.ENUM, NameT.VALID, false),
        new TestParams(DataT.ARRAY, NameT.VALID, false),
        new TestParams(DataT.MAP, NameT.VALID, false),
        new TestParams(DataT.UNION, NameT.VALID, false),
        new TestParams(DataT.FIXED, NameT.VALID, false),
        new TestParams(DataT.STRING, NameT.VALID, false),
        new TestParams(DataT.BYTES, NameT.VALID, false),
        new TestParams(DataT.INT32, NameT.VALID, false),
        new TestParams(DataT.LONG64, NameT.VALID, false),
        new TestParams(DataT.FLOAT32, NameT.VALID, false),
        new TestParams(DataT.DOUBLE64, NameT.VALID, false),
        new TestParams(DataT.BOOLEAN, NameT.VALID, false),
        new TestParams(DataT.NULL, NameT.VALID, false),

        // Added after jacoco
        new TestParams(DataT.ERROR, NameT.VALID, false)
    );
  }

  private final TestParams params;

  public SchemaValidNamesTest(TestParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    testSchema(params);
  }
}
