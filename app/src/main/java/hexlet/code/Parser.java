package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parse(String content, String extension) throws IOException {
        ObjectMapper objectMapper;
        if ("json".equals(extension)) {
            objectMapper = new ObjectMapper();
        } else if ("yaml".equals(extension) || "yml".equals(extension)) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + extension);
        }
        return objectMapper.readValue(content, Map.class);
    }
}
