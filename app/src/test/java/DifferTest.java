import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static final String FILE1_PATH = "src/test/resources/file1.json";
    private static final String FILE2_PATH = "src/test/resources/file2.json";
    private static final String EXPECTED_RESULT = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    @Test
    void testGenerate() throws IOException {
        String result = Differ.generate(FILE1_PATH, FILE2_PATH);
        assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    void testGenerateWithEqualFiles() throws IOException {
        String result = Differ.generate(FILE1_PATH, FILE1_PATH);
        String expected = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}";
        assertEquals(expected, result);
    }

    @Test
    void testGenerateWithEmptyFile() throws IOException {
        String emptyFilePath = "src/test/resources/empty.json";
        String result = Differ.generate(FILE1_PATH, emptyFilePath);
        String expected = "{\n"
                + "  - follow: false\n"
                + "  - host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "}";
        assertEquals(expected, result);
    }

    @Test
    void testGenerateWithNonExistentFile() {
        String nonExistentFilePath = "src/test/resources/nonexistent.json";
        IOException exception = assertThrows(IOException.class, () -> {
            Differ.generate(FILE1_PATH, nonExistentFilePath);
        });


        String expectedMessage = "src/test/resources/nonexistent.json";
        String actualMessage = exception.getMessage().replace("\\", "/");
        assertEquals(expectedMessage, actualMessage);
    }
}
