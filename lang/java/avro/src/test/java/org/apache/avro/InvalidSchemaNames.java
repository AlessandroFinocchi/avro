package org.apache.avro;

public class InvalidSchemaNames extends Schema.Names{
  @Override
  public String space() {
    throw new NullPointerException();
  }

  @Override
  public void space(String space) {
    throw new NullPointerException();
  }

  @Override
  public Schema get(String o) {
    throw new NullPointerException();
  }

  @Override
  public boolean contains(Schema schema) {
    throw new NullPointerException();
  }

  @Override
  public void add(Schema schema) {
    throw new NullPointerException();
  }

  @Override
  public Schema put(Schema.Name name, Schema schema) {
    throw new NullPointerException();
  }
}
