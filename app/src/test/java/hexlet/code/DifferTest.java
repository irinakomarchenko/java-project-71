package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    void testGenerateStylishYaml() throws Exception {
        String filepath1 = "src/test/resources/filepath1.yml";
        String filepath2 = "src/test/resources/filepath2.yml";
        String expectedOutput = "{\n"
                + "  follow: false\n"
                + "  host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        String result = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(expectedOutput, result);
    }

    @Test
    void testGeneratePlainYaml() throws Exception {
        String filepath1 = "src/test/resources/filepath1.yml";
        String filepath2 = "src/test/resources/filepath2.yml";
        String expectedOutput = "Property 'follow' was removed\n"
                + "Property 'proxy' was removed\n"
                + "Property 'timeout' was updated. From 50 to 20\n"
                + "Property 'verbose' was added with value: true";

        String result = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(expectedOutput, result);
    }

    @Test
    void testGenerateJsonYaml() throws Exception {
        String filepath1 = "src/test/resources/filepath1.yml";
        String filepath2 = "src/test/resources/filepath2.yml";
        String expectedOutput = "[\n"
                + "  {\"key\":\"follow\",\"oldValue\":false,\"newValue\":null,\"type\":\"removed\"},\n"
                + "  {\"key\":\"host\",\"oldValue\":\"hexlet.io\",\"newValue\":\"hexlet.io\",\"type\":\"unchanged\"},\n"
                + "  {\"key\":\"proxy\",\"oldValue\":\"123.234.53.22\",\"newValue\":null,\"type\":\"removed\"},\n"
                + "  {\"key\":\"timeout\",\"oldValue\":50,\"newValue\":20,\"type\":\"updated\"},\n"
                + "  {\"key\":\"verbose\",\"oldValue\":null,\"newValue\":true,\"type\":\"added\"}\n"
                + "]";

        String result = Differ.generate(filepath1, filepath2, "json");
        assertEquals(expectedOutput, result);
    }
}
