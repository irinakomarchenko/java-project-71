package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.List;

public class Formatter {
    private static final StylishFormatter STYLISH_FORMATTER = new StylishFormatter();
    private static final JsonFormatter JSON_FORMATTER = new JsonFormatter();
    private static final PlainFormatter PLAIN_FORMATTER = new PlainFormatter();

    public static String format(String format, List<DiffProperty> diff) throws Exception {
        return switch (format) {
            case "stylish" -> STYLISH_FORMATTER.format(diff);
            case "json" -> JSON_FORMATTER.format(diff);
            case "plain" -> PLAIN_FORMATTER.format(diff);
            default -> throw new RuntimeException("Unsupported format: " + format);
        };
    }
}
