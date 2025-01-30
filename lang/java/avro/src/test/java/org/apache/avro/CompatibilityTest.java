package org.apache.avro;

import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.avro.SchemaCompatibility.*;
import static org.apache.avro.Utils.*;

public class CompatibilityTest {

  public static class TestCompatibilityParams {
    Schema reader;
    Schema writer;
    SchemaCompatibilityType expectedCompatibilityType;
    SchemaIncompatibilityType expectedIncompatibilityType;
    Class<Exception> expectedException;

    public TestCompatibilityParams(DataT readerT, DataT writerT,
                                   SchemaCompatibilityType expectedCompatibilityType,
                                   SchemaIncompatibilityType expectedIncompatibilityType,
                                   boolean isExpectedException) {
      this.reader = getExpectedSchema(readerT);
      this.writer = getExpectedSchema(writerT);
      this.expectedCompatibilityType = expectedCompatibilityType;
      this.expectedIncompatibilityType = expectedIncompatibilityType;
      this.expectedException = isExpectedException ? Exception.class : null;
    }
  }

  public static void testCompatibility(TestCompatibilityParams params) {
    Schema readerSchema = params.reader;
    Schema writerSchema = params.writer;
    SchemaCompatibilityType expectedCompatibilityType = params.expectedCompatibilityType;
    SchemaIncompatibilityType expectedIncompatibilityType = params.expectedIncompatibilityType;
    Class<Exception> expectedException = params.expectedException;

    checkTestConfiguration(expectedCompatibilityType, expectedIncompatibilityType, expectedException);

    if (expectedException != null)
      Assert.assertThrows("Expected exception not thrown",
          expectedException, () -> checkReaderWriterCompatibility(writerSchema, readerSchema));
    else{
      try {
        SchemaPairCompatibility actualCompatibility = checkReaderWriterCompatibility(writerSchema, readerSchema);

        Assert.assertEquals("Compatibility type mismatch",
            expectedCompatibilityType, actualCompatibility.getType());

        if(expectedIncompatibilityType != null){
          List<Incompatibility> actualIncompatibilities = actualCompatibility.getResult().getIncompatibilities();
          Assert.assertNotNull("Incompatibilities should not be null",actualIncompatibilities);

          List<SchemaIncompatibilityType> actualIncompatibilityTypes = actualIncompatibilities
              .stream()
              .map(Incompatibility::getType)
              .collect(Collectors.toList());

          Assert.assertTrue("Expected incompatibility type not found",
              actualIncompatibilityTypes.contains(expectedIncompatibilityType));
        }
      } catch (Exception e) { Assert.fail("Unexpected exception"); }
    }
  }

  private static void checkTestConfiguration(SchemaCompatibilityType expectedCompatibilityType,
                                             SchemaIncompatibilityType expectedIncompatibilityType,
                                             Class<Exception> expectedException) {
    switch (expectedCompatibilityType) {
      case COMPATIBLE:
        if (expectedIncompatibilityType != null || expectedException != null)
          throw new IllegalArgumentException("Incompatible test configuration");
        break;
      case INCOMPATIBLE:
        if (expectedIncompatibilityType == null && expectedException == null)
          throw new IllegalArgumentException("Incompatible test configuration");
        break;
      case RECURSION_IN_PROGRESS:
        throw new IllegalArgumentException("Incompatible test configuration");
      default:
        throw new IllegalArgumentException("Unknown compatibility type");
    }
  }
}
