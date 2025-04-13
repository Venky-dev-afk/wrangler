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
        String numericPart = value.replaceAll("[^0-9.]", "");
        String unit = value.replaceAll("[0-9.]", "");

        try {
            double numericValue = Double.parseDouble(numericPart);
            switch (unit) {
                case "KB":
                    return (long) (numericValue * 1024);
                case "MB":
                    return (long) (numericValue * 1024 * 1024);
                case "GB":
                    return (long) (numericValue * 1024L * 1024L * 1024L);
                case "TB":
                    return (long) (numericValue * 1024L * 1024L * 1024L * 1024L);
                case "B":
                    return (long) numericValue;
                default:
                    throw new IllegalArgumentException("Invalid byte size format: " + value);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric value in byte size format: " + value, e);
        }
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
