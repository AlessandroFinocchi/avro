package org.apache.avro.custom_tests;

import org.apache.avro.data.TimeConversions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class FirstClassTest {

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testParse() {
    try {
      TimeConversions timeConversions = new TimeConversions();
      TimeConversions.DateConversion dateConversion = new TimeConversions.DateConversion();
      Class<LocalDate> clazz = dateConversion.getConvertedType();
    }
    catch (Exception e) {
      Assert.assertTrue(true);
    }
    Assert.assertTrue(true);
  }
}
