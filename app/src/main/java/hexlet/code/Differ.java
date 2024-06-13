package hexlet.code;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String generate(String filePath1, String filePath2) throws IOException {
        JsonNode file1Json = MAPPER.readTree(Files.readAllBytes(Paths.get(filePath1)));
        JsonNode file2Json = MAPPER.readTree(Files.readAllBytes(Paths.get(filePath2)));

        Map<String, JsonNode> combinedKeys = new TreeMap<>();
        file1Json.fieldNames().forEachRemaining(key -> combinedKeys.put(key, file1Json.get(key)));
        file2Json.fieldNames().forEachRemaining(key -> combinedKeys.put(key, file2Json.get(key)));


        StringBuilder result = new StringBuilder("{\n");

        for (String key : combinedKeys.keySet()) {
            JsonNode value1 = file1Json.get(key);
            JsonNode value2 = file2Json.get(key);

            if (value1 != null && value2 != null && value1.equals(value2)) {
                result.append("    ").append(key).append(": ").append(value1).append("\n");
            } else if (value1 != null && value2 != null) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else if (value1 != null) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else if (value2 != null) {
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
        }

        result.append("}");

        return result.toString();
    }
}


