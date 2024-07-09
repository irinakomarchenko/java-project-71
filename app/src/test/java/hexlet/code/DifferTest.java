package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private String readFromFile(String filepath) throws Exception {
        return Files.readString(Paths.get(filepath));
    }

    private String getFixturePath(String filename) {
        return "src/test/resources/" + filename;
    }

    private void assertGeneratedResult(String filepath1, String filepath2, String expectedResultFilepath, String format)
            throws Exception {
        String expectedResult = readFromFile(expectedResultFilepath);
        String actualResult = Differ.generate(filepath1, filepath2, format);

        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.yml, file2.yml, expectedStylish.txt, stylish",
        "file1.yaml, file2.yml, expectedStylish.txt, stylish",
        "file1.yml, file2.yml, expectedPlain.txt, plain",
        "file1.yml, file2.yml, expectedJson.txt, json",
        "file3.json, file4.json, expectedStylishNotFlat.txt, stylish",
        "file3.json, file4.json, expectedPlainNotFlat.txt, plain",
        "file3.json, file4.json, expectedJsonNotFlat.txt, json"
    })
    void testGenerate(String file1, String file2, String expectedResultFile, String format) throws Exception {
        String filepath1 = getFixturePath(file1);
        String filepath2 = getFixturePath(file2);
        String expectedResultFilepath = getFixturePath(expectedResultFile);

        assertGeneratedResult(filepath1, filepath2, expectedResultFilepath, format);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.yml, file2.yml, expectedStylish.txt",
    })
    void testGenerateDefaultFormat(String file1, String file2, String expectedResultFile) throws Exception {
        String filepath1 = getFixturePath(file1);
        String filepath2 = getFixturePath(file2);
        String expectedResultFilepath = getFixturePath(expectedResultFile);

        assertGeneratedResult(filepath1, filepath2, expectedResultFilepath, "stylish");
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, file_wo_ext_2, plain, File extension cannot be determined.",
        "file_wo_ext_1, file2.json, plain, File extension cannot be determined.",
        "file1.json, file2.json, wrong, Unsupported format: wrong",
    })
    void testGenerateException(String file1, String file2, String format, String exceptionMsg) {
        String filepath1 = getFixturePath(file1);
        String filepath2 = getFixturePath(file2);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> Differ
                .generate(filepath1, filepath2, format));
        assertEquals(exceptionMsg, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "json",
        "yml"
    })
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format);
        String filePath2 = getFixturePath("file2." + format);

        assertGeneratedResult(filePath1, filePath2, getFixturePath("expectedStylish.txt"), "stylish");

    }
}
