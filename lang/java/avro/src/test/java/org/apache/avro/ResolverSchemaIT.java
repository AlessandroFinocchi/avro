package org.apache.avro;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import org.apache.avro.Resolver.Action;

import static org.apache.avro.Utils.*;

@RunWith(Parameterized.class)
public class ResolverSchemaIT {

  public static class ITResolverSchemaParams {
    Schema writer;
    Schema reader;
    Action.Type expectedActionType;
    Class<Exception> expectedException;

    private ITResolverSchemaParams(Action.Type expectedActionType, Class<Exception> expectedException) {
      this.expectedActionType = expectedActionType;
      this.expectedException = expectedException;
    }

    public ITResolverSchemaParams(Schema writer, Schema reader,
                                  Action.Type expectedActionType, Class<Exception> expectedException) {
      this(expectedActionType, expectedException);
      this.writer = writer;
      this.reader = reader;
    }

    public ITResolverSchemaParams(DataT readerT, DataT writerT,
                                  Action.Type expectedActionType, Class<Exception> expectedException) {
      this(expectedActionType, expectedException);
      this.reader = validSchema(readerT);
      this.writer = validSchema(writerT);
    }
  }

  @Parameterized.Parameters
  public static Collection<ITResolverSchemaParams> getParameters() {
    return Arrays.asList(
        new ITResolverSchemaParams(DataT.INT32, DataT.INT32,  Action.Type.DO_NOTHING,  null),
        new ITResolverSchemaParams(DataT.BYTES, DataT.STRING, Action.Type.PROMOTE,     null)
    );
  }

  private final ITResolverSchemaParams params;

  public ResolverSchemaIT(ITResolverSchemaParams params) {
    this.params = params;
  }

  @Test
  public void test() {
    Schema readerSchema = params.reader;
    Schema writerSchema = params.writer;
    Action.Type expectedActionType = params.expectedActionType;
    Class<Exception> expectedException = params.expectedException;

    if (expectedException != null)
      Assert.assertThrows("Expected exception not thrown",
          expectedException, () -> Resolver.resolve(writerSchema, readerSchema));
    else {
      try {
        Action actualAction = Resolver.resolve(writerSchema, readerSchema);

        Assert.assertEquals("Actions mismatch", expectedActionType, actualAction.type);
        Assert.assertEquals("Readers mismatch", readerSchema, actualAction.reader);
        Assert.assertEquals("Writers mismatch", writerSchema, actualAction.writer);

      } catch (Exception e) {
        Assert.fail("Unexpected exception: " + e);
      }
    }
  }

}
