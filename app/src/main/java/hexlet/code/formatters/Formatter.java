package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.List;

public interface Formatter {
    String format(List<DiffProperty> diff) throws Exception;
}
