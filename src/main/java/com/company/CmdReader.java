package com.company;

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

        out.println("Здравствуйте! Вводите числа на английском. Для выхода введите 'exit'. Чтобы посмотреть состояние памяти введите ls");

        while (true) {
            line = reader.readLine();
            if (line == null) continue;
            if (line.equals("exit")) break;
            if (line.equals("ls")) {
                repository.print();
                continue;
            }
            try {
                int value = engDigitParser.parse(line);
                repository.add(value);
                out.println(value);
            } catch (InvalidEngNumberException|NullPointerException e) {
                out.println("Неверное число: " + line);
            }
        }
    }
}
