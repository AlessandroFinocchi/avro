package org.apache.avro;

public class InvalidSchema extends Schema{

  InvalidSchema(Type type) {
    super(type);
  }

  @Override
  public Type getType() {
    throw new NullPointerException();
  }
}
