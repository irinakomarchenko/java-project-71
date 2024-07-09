package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    private static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() { });
    }

    private static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() { });
    }

    public static Map<String, Object> parse(String content, String dataFormat) throws Exception {
        switch (dataFormat.toLowerCase()) {
            case "yml":
            case "yaml":
                return parseYaml(content);
            case "json":
                return parseJson(content);
            default:
                System.err.println("Unknown format: '" + dataFormat + "'");
                break;
        }

        throw new Exception("Unknown format: '" + dataFormat + "'");
    }
}
