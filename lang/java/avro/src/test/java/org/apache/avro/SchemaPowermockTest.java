package org.apache.avro;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.apache.avro.Utils.*;
import static org.apache.avro.SchemaTests.TestSchemaParams;

@RunWith(Parameterized.class)
public class SchemaPowermockTest {

  @Parameterized.Parameters
  public static Collection<TestSchemaParams> getParameters() {
    return Collections.singletonList(
        // Added after PIT
        new TestSchemaParams(DataT.POWERMOCK_RECORD, NameT.VALID, false)
    );
  }

  private final TestSchemaParams params;

  public SchemaPowermockTest(TestSchemaParams params) {
    this.params = params;
  }

  @Test
  public void testSchema() {
    JsonNode schemaJsonNode = this.params.schemaJsonNode;
    Schema.Names schemaNames = this.params.schemaNames;
    Class<Exception> expectedException = this.params.expectedException;

    if (expectedException != null)
      Assert.assertThrows(expectedException, () -> Schema.parse(schemaJsonNode, schemaNames));
    else{
      try {
        Logger mockLogger = spy(Schema.LOG);
        Whitebox.setInternalState(Schema.class, "LOG", mockLogger);

        Schema.parse(schemaJsonNode, schemaNames);

        String schemaType = schemaJsonNode.get("type").toString();
        boolean isRecord = schemaType.equals("\"record\"");
        boolean isError  = schemaType.equals("\"error\"");

        if(isRecord || isError) verify(mockLogger, times(1)).warn(anyString(), any(Object[].class));

      } catch (Exception e) {
        Assert.fail("Unexpected exception");
      }
    }
  }
}
