import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CmdReader {

    private Repository repository;
    private EngDigitParser engDigitParser = new EngDigitParser();

    private InputStream in;
    private PrintStream out;

    public CmdReader(Repository repository, InputStream in, PrintStream out) {
        this.repository = repository;
        this.in = in;
        this.out = out;
    }

    public void readLoop() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;

        out.println("Здравствуйте! Вводите числа на английском");

        do {
            line = reader.readLine();
            try {
                int value = engDigitParser.parse(line);
                repository.add(value);
                out.println("Введено число " + value);
                repository.print();

            } catch (InvalidEngNumberException|NullPointerException e) {
                out.println("Неверное число: " + line);
            }

        } while (!line.equals("exit"));
    }
}
