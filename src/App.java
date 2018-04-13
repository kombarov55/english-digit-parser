import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class App {
    public static void main(String[] args) throws IOException {
        Repository repository = new Repository(System.out);
        InputStream in = System.in;
        PrintStream out = System.out;

        new Thread(() -> {
            try {
                CmdReader reader = new CmdReader(repository, in, out);
                reader.readLoop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(5000);
                    repository.removeMin();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
