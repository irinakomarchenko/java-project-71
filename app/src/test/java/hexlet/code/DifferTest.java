package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.yml, src/test/resources/file2.yml, src/test/resources/expectedStylish.txt, stylish",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, src/test/resources/expectedPlain.txt, plain",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, src/test/resources/expectedJson.txt, json"
    })
    void testGenerate(String filepath1, String filepath2, String expectedResultFilepath, String format)
            throws Exception {
        String expectedResult = normalizeString(Files.readString(Paths.get(expectedResultFilepath)));
        String actualResult = normalizeString(Differ.generate(filepath1, filepath2, format));

        assertEquals(expectedResult, actualResult);
    }

    private String normalizeString(String input) {
        return input.trim().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }
}
