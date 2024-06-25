package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.List;

public class PlainFormatter implements Formatter {
    @Override
    public String format(List<DiffProperty> diff) {
        StringBuilder result = new StringBuilder();
        for (DiffProperty property : diff) {
            switch (property.getType()) {
                case ADDED -> result.append("Property '").append(property.getKey()).append("' was added with value: ").append(property.getNewValue()).append("\n");
                case REMOVED -> result.append("Property '").append(property.getKey()).append("' was removed").append("\n");
                case CHANGED -> result.append("Property '").append(property.getKey()).append("' was updated. From ").append(property.getOldValue()).append(" to ").append(property.getNewValue()).append("\n");
            }
        }
        return result.toString().trim();
    }
}
