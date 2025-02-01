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
    Class<Throwable> expectedException;

    private TestCompatibilityParams(SchemaCompatibilityType expectedCompatibilityType,
                                    SchemaIncompatibilityType expectedIncompatibilityType,
                                    boolean isExpectedException) {
      this.expectedCompatibilityType = expectedCompatibilityType;
      this.expectedIncompatibilityType = expectedIncompatibilityType;
      this.expectedException = isExpectedException ? Throwable.class : null;
    }

    public TestCompatibilityParams(DataT readerT, DataT writerT,
                                   SchemaCompatibilityType expectedCompatibilityType,
                                   SchemaIncompatibilityType expectedIncompatibilityType,
                                   boolean isExpectedException) {
      this(expectedCompatibilityType, expectedIncompatibilityType, isExpectedException);
      this.reader = validSchema(readerT);
      this.writer = validSchema(writerT);
    }

    public TestCompatibilityParams(Schema reader, Schema writer,
                                   SchemaCompatibilityType expectedCompatibilityType,
                                   SchemaIncompatibilityType expectedIncompatibilityType,
                                   boolean isExpectedException) {
      this(expectedCompatibilityType, expectedIncompatibilityType, isExpectedException);
      this.reader = reader;
      this.writer = writer;
    }
  }

  public static void testCompatibility(TestCompatibilityParams params) {
    Schema readerSchema = params.reader;
    Schema writerSchema = params.writer;
    SchemaCompatibilityType expectedCompatibilityType = params.expectedCompatibilityType;
    SchemaIncompatibilityType expectedIncompatibilityType = params.expectedIncompatibilityType;
    Class<Throwable> expectedException = params.expectedException;

    if (expectedException != null)
      Assert.assertThrows("Expected exception not thrown",
          expectedException, () -> checkReaderWriterCompatibility(readerSchema, writerSchema));
    else{
      try {
        SchemaPairCompatibility actualCompatibility = checkReaderWriterCompatibility(readerSchema, writerSchema);

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

          for(Incompatibility incompatibility : actualIncompatibilities){
            try {
              String referenceToken = incompatibility.getLocation().substring(1);
              if(referenceToken.matches("-?\\d+")){ // Check if the reference token is a number
                int referenceTokenNumber = Integer.parseInt(referenceToken);
                Assert.assertTrue("Reference token is not greater or equal than zero", referenceTokenNumber >= 0);
              }

            } catch (NumberFormatException e) {
              Assert.fail("Reference token is not a number");
            }
          }
        }
      } catch (Exception e) { Assert.fail("Unexpected exception"); }
    }
  }
}
