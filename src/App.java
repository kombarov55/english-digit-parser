import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Repository repository = new Repository();
        CmdReader reader = new CmdReader(repository, System.in, System.out);
        reader.readLoop();
    }
}
