import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Repository {

    private List<Integer> data = new ArrayList<>();

    private PrintStream out;

    public Repository(PrintStream out) {
        this.out = out;
    }

    public synchronized void removeMin() {
        OptionalInt min = data.stream()
                .mapToInt(it -> it)
                .min();

        min.ifPresent(value -> {
            data = data.stream()
                    .filter(it -> it != value)
                    .collect(Collectors.toList());
        });
    }

    public synchronized void add(Integer  value) {
        data.add(value);
    }

    public synchronized void print() {
        out.println("Текущее состояние памяти: ");
        out.println(data);
    }
}
