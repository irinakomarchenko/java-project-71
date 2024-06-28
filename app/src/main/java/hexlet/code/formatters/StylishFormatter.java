package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.List;

public class StylishFormatter implements Formatter {
    @Override
    public String format(List<DiffProperty> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffProperty property : diff) {
            switch (property.getType()) {
                case ADDED -> result.append("  + ").append(property.getKey()).append(": ").append(property
                        .getNewValue()).append("\n");
                case REMOVED -> result.append("  - ").append(property.getKey()).append(": ").append(property
                        .getOldValue()).append("\n");
                case CHANGED -> {
                    result.append("  - ").append(property.getKey()).append(": ").append(property.getOldValue())
                            .append("\n");
                    result.append("  + ").append(property.getKey()).append(": ").append(property.getNewValue())
                            .append("\n");
                }
                case UNCHANGED -> result.append("    ").append(property.getKey()).append(": ").append(property
                        .getOldValue()).append("\n");
                default -> throw new IllegalArgumentException("Unexpected property type: " + property.getType());
            }
        }
        result.append("}");
        return result.toString();
    }
}
