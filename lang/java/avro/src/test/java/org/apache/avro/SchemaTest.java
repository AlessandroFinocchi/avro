package org.apache.avro;

import org.junit.Assert;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SchemaTest {

  private static Stream<Arguments> data() {
    return Stream.of(
        // Varying allocator
        Arguments.of(Schema.Type.INT, null),
        Arguments.of(Schema.Type.LONG, null)
    );
  }

  @ParameterizedTest
  @MethodSource("data")
  @Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
  public void testSchema(Schema.Type t, Class<Exception> expectedException) {
    if (expectedException != null) Assert.assertTrue(true);
    else{
      try {
        Schema schema1 = Schema.create(t);
        Schema schema2 = Schema.create(t);

        Assert.assertEquals(schema1, schema2);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}

