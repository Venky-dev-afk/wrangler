package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Represents a parsed ByteSize token (e.g., "10KB", "2MB").
 */
public class ByteSize implements Token {
  private final long bytes;

  public ByteSize(String value) throws IllegalArgumentException {
    this.bytes = parse(value);
  }

  public long getBytes() {
    return bytes;
  }

  private long parse(String value) {
    value = value.trim().toUpperCase();
    if (value.endsWith("KB")) {
      return Long.parseLong(value.replace("KB", "").trim()) * 1024;
    } else if (value.endsWith("MB")) {
      return Long.parseLong(value.replace("MB", "").trim()) * 1024 * 1024;
    } else if (value.endsWith("GB")) {
      return Long.parseLong(value.replace("GB", "").trim()) * 1024L * 1024L * 1024L;
    } else if (value.endsWith("B")) {
      return Long.parseLong(value.replace("B", "").trim());
    }
    throw new IllegalArgumentException("Invalid byte size format: " + value);
  }

  @Override
  public JsonElement toJson() {
    return new JsonPrimitive(bytes);
  }

  @Override
  public Object value() {
    return bytes;
  }

  @Override
  public TokenType type() {
    return TokenType.BYTESIZE;
  }

  @Override
  public String toString() {
    return bytes + "B";
  }
}
