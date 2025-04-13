package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Represents a parsed time duration (e.g., "10s", "5m", "2h").
 * Internally stored as milliseconds.
 */
public class TimeDuration implements Token {
  private final long milliseconds;

  public TimeDuration(String value) throws IllegalArgumentException {
    this.milliseconds = parse(value);
  }

  public long getMilliseconds() {
    return milliseconds;
  }

  private long parse(String value) {
    value = value.trim().toLowerCase();
    if (value.endsWith("ms")) {
      return Long.parseLong(value.replace("ms", "").trim());
    } else if (value.endsWith("s")) {
      return Long.parseLong(value.replace("s", "").trim()) * 1000;
    } else if (value.endsWith("m")) {
      return Long.parseLong(value.replace("m", "").trim()) * 60 * 1000;
    } else if (value.endsWith("h")) {
      return Long.parseLong(value.replace("h", "").trim()) * 60 * 60 * 1000;
    }
    throw new IllegalArgumentException("Invalid time duration format: " + value);
  }

  @Override
  public JsonElement toJson() {
    return new JsonPrimitive(milliseconds);
  }

  @Override
  public Object value() {
    return milliseconds;
  }

  @Override
  public TokenType type() {
    return TokenType.TIMEDURATION;
  }

  @Override
  public String toString() {
    return milliseconds + "ms";
  }
}
