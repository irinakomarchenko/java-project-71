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

        combinedKeys.forEach((key, value) -> {
            JsonNode value1 = file1Json.get(key);
            JsonNode value2 = file2Json.get(key);

            switch (compareValues(value1, value2)) {
                case "unchanged" -> result.append("    ").append(key).append(": ").append(value1).append("\n");
                case "changed" -> {
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                }
                case "removed" -> result.append("  - ").append(key).append(": ").append(value1).append("\n");
                case "added" -> result.append("  + ").append(key).append(": ").append(value2).append("\n");
                default -> throw new IllegalStateException("Unexpected value: " + compareValues(value1, value2));
            }
        });

        result.append("}");

        return result.toString();
    }

    private static String compareValues(JsonNode value1, JsonNode value2) {
        if (value1 != null && value2 != null && value1.equals(value2)) {
            return "unchanged";
        }
        if (value1 != null && value2 != null) {
            return "changed";
        }
        if (value1 != null) {
            return "removed";
        }
        return "added";
    }
}



