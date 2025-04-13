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
        value = value.trim().toUpperCase();
        String numericPart = value.replaceAll("[^0-9.]", "");
        String unit = value.replaceAll("[0-9.]", "");

        try {
            double numericValue = Double.parseDouble(numericPart);
            switch (unit) {
                case "MS":
                    return (long) numericValue;
                case "S":
                    return (long) (numericValue * 1000);
                case "M":
                    return (long) (numericValue * 60 * 1000);
                case "H":
                    return (long) (numericValue * 60 * 60 * 1000);
                default:
                    throw new IllegalArgumentException("Invalid time duration format: " + value);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric value in time duration format: " + value, e);
        }
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
