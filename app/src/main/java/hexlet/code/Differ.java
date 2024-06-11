package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.List;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        List<String> file1Lines = Files.readAllLines(Paths.get(filePath1));
        List<String> file2Lines = Files.readAllLines(Paths.get(filePath2));

        return file1Lines.stream()
                .filter(line -> !file2Lines.contains(line))
                .collect(Collectors.joining("\n"));
    }
}
