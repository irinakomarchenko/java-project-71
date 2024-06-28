package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.List;

public class PlainFormatter implements Formatter {
    @Override
    public String format(List<DiffProperty> diff) {
        StringBuilder result = new StringBuilder();
        for (DiffProperty property : diff) {
            switch (property.getType()) {
                case ADDED -> result.append("Property '").append(property.getKey())
                        .append("' was added with value: ").append(formatValue(property.getNewValue())).append("\n");
                case REMOVED -> result.append("Property '").append(property.getKey()).append("' was removed")
                        .append("\n");
                case CHANGED -> result.append("Property '").append(property.getKey())
                        .append("' was updated. From ").append(formatValue(property.getOldValue()))
                        .append(" to ").append(formatValue(property.getNewValue())).append("\n");
                case UNCHANGED -> {
                    // Do nothing for unchanged properties
                }
                default -> throw new IllegalArgumentException("Unexpected property type: " + property.getType());
            }
        }
        return result.toString().trim();
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        return value instanceof String ? "'" + value + "'" : String.valueOf(value);
    }
}
