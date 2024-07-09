package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter extends Formatter {


    public final String format(List<DiffProperty> diff) {
        return format(diff, 1);
    }

    private String format(List<DiffProperty> diff, int level) {
        StringBuilder result = new StringBuilder("{\n");
        String indent = "    ".repeat(level - 1);

        List<DiffProperty> sortedDiff = diff.stream()
                .sorted((a, b) -> a.getKey().compareTo(b.getKey()))
                .collect(Collectors.toList());

        for (DiffProperty property : sortedDiff) {
            switch (property.getType()) {
                case ADDED:
                    result.append(indent).append("  + ").append(property.getKey())
                            .append(": ").append(formatValue(property.getNewValue(), level))
                            .append("\n");
                    break;
                case REMOVED:
                    result.append(indent).append("  - ").append(property.getKey())
                            .append(": ").append(formatValue(property.getOldValue(), level))
                            .append("\n");
                    break;
                case UNCHANGED:
                    result.append(indent).append("    ").append(property.getKey())
                            .append(": ").append(formatValue(property.getOldValue(), level))
                            .append("\n");
                    break;
                case CHANGED:
                    result.append(indent).append("  - ").append(property.getKey())
                            .append(": ").append(formatValue(property.getOldValue(), level))
                            .append("\n");
                    result.append(indent).append("  + ").append(property.getKey())
                            .append(": ").append(formatValue(property.getNewValue(), level))
                            .append("\n");
                    break;
                case COMPOUND:
                    result.append(indent).append("    ").append(property.getKey())
                            .append(": ").append(format(property.getChildren(), level + 1))
                            .append("\n");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + property.getType());
            }
        }
        result.append(indent).append("}");
        return result.toString();
    }

    private String formatValue(Object value, int level) {
        if (value == null) {
            return "null";
        } else if (value instanceof List || value instanceof Map) {
            return formatComplexValue(value);
        } else {
            return value.toString();
        }
    }

    private String formatComplexValue(Object value) {
        if (value instanceof List<?> list) {
            return list.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ", "[", "]"));
        } else if (value instanceof Map<?, ?> map) {
            return map.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining(", ", "{", "}"));
        }
        return value.toString();
    }
}
