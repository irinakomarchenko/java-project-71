package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffProperty;

import java.util.List;

public class JsonFormatter implements Formatter {

    @Override
    public String format(List<DiffProperty> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff);
    }
}
