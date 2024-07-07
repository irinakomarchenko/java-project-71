package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.yml, src/test/resources/file2.yml, src/test/resources/expectedStylish.txt, "
                    +  "stylish",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, src/test/resources/expectedPlain.txt, plain",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, src/test/resources/expectedJson.txt, json",
        "src/test/resources/file3.json, src/test/resources/file4.json, src/test/resources/expectedStylishNotFlat.txt, "
                +  "stylish",
        "src/test/resources/file3.json, src/test/resources/file4.json, src/test/resources/expectedPlainNotFlat.txt,"
             + " plain",
        "src/test/resources/file3.json, src/test/resources/file4.json, src/test/resources/expectedJsonNotFlat.txt, json"
    })
    void testGenerate(String filepath1, String filepath2, String expectedResultFilepath, String format) throws
            Exception {
        String expectedResult = Files.readString(Paths.get(expectedResultFilepath));
        String actualResult = Differ.generate(filepath1, filepath2, format);

        System.out.println("ACTUAL:");
        System.out.println(actualResult);
        System.out.println("EXPECTED:");
        System.out.println(expectedResult);
        System.out.println("END");
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file_wo_ext_1, src/test/resources/file_wo_ext_2, plain, "
                + "File extension cannot be determined.",
        "src/test/resources/file1.json, src/test/resources/file2.json, wrong, Unsupported format: wrong",
    })
    void testGenerateException(String filepath1, String filepath2, String format, String exceptionMsg) throws
            Exception {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Differ.generate(filepath1, filepath2, format);
        });

        assertEquals(exceptionMsg, exception.getMessage());
    }

}
