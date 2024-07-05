package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String fileExtension) throws IOException {
        ObjectMapper objectMapper = fileExtension.equals("yaml") || fileExtension.equals("yml")
                ? new ObjectMapper(new YAMLFactory()) : new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }

}

