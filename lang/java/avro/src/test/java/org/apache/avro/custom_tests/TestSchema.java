package org.apache.avro.custom_tests;

import org.apache.avro.Schema;
import org.junit.Assert;
import org.junit.Test;

import static org.apache.avro.Schema.Type.STRING;

public class TestSchema {

  @Test
  public void testSchema() {
    Schema schema = Schema.create(STRING);

    Assert.assertTrue(true);
  }
}
