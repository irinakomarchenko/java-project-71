package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter extends Formatter {


    public final String format(List<DiffProperty> diff) {
        return diff.stream()
                .sorted(Comparator.comparing(DiffProperty::getKey))
                .map(this::formatProperty)
                .filter(property -> !property.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    private String formatProperty(DiffProperty property) {
        return switch (property.getType()) {
            case ADDED -> "Property '" + property.getKey() + "' was added with value: "
                    + formatValue(property.getNewValue());
            case REMOVED -> "Property '" + property.getKey() + "' was removed";
            case CHANGED -> "Property '" + property.getKey() + "' was updated. From "
                    + formatValue(property.getOldValue()) + " to " + formatValue(property.getNewValue());
            case UNCHANGED, COMPOUND -> "";
            default -> throw new IllegalStateException("Unexpected value: " + property.getType());
        };
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }
        return value.toString();
    }
}
