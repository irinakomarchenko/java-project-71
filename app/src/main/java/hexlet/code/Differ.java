package hexlet.code;


import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public final class Differ {

    private Differ() {
        throw new AssertionError();
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String file1Content = readFileToString(filepath1);
        String file2Content = readFileToString(filepath2);

        String fileExtension1 = getFileExtension(filepath1);
        String fileExtension2 = getFileExtension(filepath2);

        if (fileExtension1 == null || fileExtension2 == null) {
            throw new IllegalArgumentException("File extension cannot be determined.");
        }

        Map<String, Object> map1 = Parser.parse(file1Content, fileExtension1);
        Map<String, Object> map2 = Parser.parse(file2Content, fileExtension2);

        List<Node> diff = TreeDiffer.compareData(map1, map2);
        List<DiffProperty> diffProperties = Node.toDiffProperties(diff);

        return Formatter.format(format, diffProperties);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String readFileToString(String filepath) throws IOException {
        Path path = Path.of(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    private static String getFileExtension(String filepath) {
        String fullPath = Path.of(filepath).toAbsolutePath().normalize().toString();
        int index = fullPath.lastIndexOf('.');
        return index == -1 ? null : fullPath.substring(index + 1).toLowerCase();
    }
}
