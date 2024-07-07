package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    private String format;

    @Parameters(index = "0", description = "The first file to compare.")
    private String filePath1;

    @Parameters(index = "1", description = "The second file to compare.")
    private String filePath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        if (filePath1 == null || filePath2 == null) {
            System.out.println("Please provide two file paths for comparison.");
            return 1;
        }

        try {
            String result = Differ.generate(filePath1, filePath2, format);
            System.out.println(result);
            return 0;
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return 1;
        }
    }
}

