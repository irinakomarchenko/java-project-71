package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    private String format;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionRequested;

    @Parameters(index = "0", description = "The first file to compare.", defaultValue = "")
    private String filePath1;

    @Parameters(index = "1", description = "The second file to compare.", defaultValue = "")
    private String filePath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        if (filePath1.isEmpty() || filePath2.isEmpty()) {
            System.out.println("Please provide two file paths for comparison.");
            return 1;
        }

        try {
            String result = Differ.generate(filePath1, filePath2);
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

