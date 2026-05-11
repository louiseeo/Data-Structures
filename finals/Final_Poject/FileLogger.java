import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger {
    private static PrintWriter writer;

    public static void startLogging() throws IOException {
        writer = new PrintWriter(new FileWriter("logs.txt", true));
    }

    public static void log(String message) {
        System.out.println(message);
        writer.println(message);
        writer.flush();
    }

    public static void logAnswer(String message) {
        writer.println(message);
        writer.flush();
    }

    public static void logf(String format, Object... args) {
        System.out.printf(format, args); // terminal
        writer.printf(format, args); // file
        writer.flush();
    }

    public static void close() {
        writer.close();
    }
}
