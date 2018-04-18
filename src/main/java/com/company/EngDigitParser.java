package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * one
 * twelve
 * twenty one
 * one hundred five
 * two thousand ninety nine
 *
 * one thousand
 * one hundred
 *
 * 1. Разделить по пробелам
 * 2. Развернуть
 * 3. Число < 9? (sum += num; если есть multiplier, то sum += num * multiplier) + далее десятокмультипликатор
 *    Число [9, 19]? вставить + далее нельзя использовать сотни
 *    Десяток? sum += num * 10 + далее число [1, 9] + далее мультипликатор
 *    Мультипликатор? модификатор к следующему числу = 100 | 1000 + далее число + конец
 */
public class EngDigitParser {

    private enum State {
        SingleNumber, DoubleNumber, Dozen, Hundred, Thousand
    }

    private State determineState(String s) {
        if (numbers.containsKey(s)) return State.SingleNumber;
        if (doubleNumbers.containsKey(s)) return State.DoubleNumber;
        if (dozens.containsKey(s)) return State.Dozen;
        if (s.equalsIgnoreCase("hundred")) return State.Hundred;
        if (s.equalsIgnoreCase("thousand")) return State.Thousand;
        throw new InvalidEngNumberException(s);
    }


    public int parse(String line) {
        Integer multiplier = null;
        int sum = 0;
        List<State> allowedNextStates = Arrays.asList(State.values());

        List<String> parts = Arrays.asList(line.split(" "));
        Collections.reverse(parts);
        for (String part : parts) {

            State currentState = determineState(part);
            if (!allowedNextStates.contains(currentState)) throw new InvalidEngNumberException(part);
            switch (currentState) {
                case SingleNumber:
                    if (multiplier == null) {
                        sum += numbers.get(part);
                        allowedNextStates = Arrays.asList(State.Dozen, State.Hundred, State.Thousand);

                    } else {
                        sum += numbers.get(part) * multiplier;
                        if (multiplier == 100) {
                            multiplier = null;
                            allowedNextStates = Collections.singletonList(State.Thousand);
                        } else {
                            multiplier = null;
                            allowedNextStates = Collections.emptyList();
                        }

                    }
                    break;
                case DoubleNumber:
                    sum += doubleNumbers.get(part);
                    allowedNextStates = Arrays.asList(State.Dozen, State.Hundred, State.Thousand);
                    break;
                case Dozen:
                    sum += dozens.get(part);
                    allowedNextStates = Arrays.asList(State.Hundred, State.Thousand);
                    break;
                case Hundred:
                    multiplier = 100;
                    allowedNextStates = Collections.singletonList(State.SingleNumber);
                    break;
                case Thousand:
                    multiplier = 1000;
                    allowedNextStates = Collections.singletonList(State.SingleNumber);
                    break;

                default: throw new InvalidEngNumberException();
            }


        }

        // значит ожидалось число и его не дали
        if (multiplier != null) throw new InvalidEngNumberException();

        return sum;

    }



    private static Map<String, Integer> numbers = new HashMap<>();
    static {
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
    }

    private static Map<String, Integer> doubleNumbers = new HashMap<>();
    static {
        doubleNumbers.put("ten", 10);
        doubleNumbers.put("eleven", 11);
        doubleNumbers.put("twelve", 12);
        doubleNumbers.put("thirteen", 13);
        doubleNumbers.put("fourteen", 14);
        doubleNumbers.put("fifteen", 15);
        doubleNumbers.put("sixteen", 16);
        doubleNumbers.put("seventeen", 17);
        doubleNumbers.put("eighteen", 18);
        doubleNumbers.put("nineteen", 19);
    }

    private static Map<String, Integer> dozens = new HashMap<>();
    static {
        dozens.put("twenty", 20);
        dozens.put("thirty", 30);
        dozens.put("forty", 40);
        dozens.put("fifty", 50);
        dozens.put("sixty", 60);
        dozens.put("seventy", 70);
        dozens.put("eighty", 80);
        dozens.put("ninety", 90);
    }

}
