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
 * 3. Число?
 *    - число: sum += num
 *    - нет:
 *      - Суффикс ty?
 *        - Есть: sum += num * 10
 *        - Нет:
 *          - Есть в [hundred, thousand]?
 *            - Есть: модификатор к следующему числу = 100 | 1000
 *            - Нет: exception
 */
public class EngDigitParser {

    public int parse(String line) {
        Integer multiplier = null;
        int sum = 0;

        List<String> parts = Arrays.asList(line.split(" "));
        Collections.reverse(parts);
        for (String part : parts) {
            Integer num = dictionarySingle.get(part);
            if (num != null) {
                if (multiplier == null) {
                    sum += num;
                } else {
                    sum += num * multiplier;
                    multiplier = null;
                }
            } else {
                if (part.endsWith("ty")) {
                    num = dictionaryFurther.get(part);
                    sum += num;
                } else {
                    multiplier = dictionaryMultipliers.get(part);
                    if (multiplier == null) {
                        throw new InvalidEngNumberException(line);
                    }
                }
            }
        }

        return sum;
    }



    private static Map<String, Integer> dictionarySingle = new HashMap<>();
    static {
        dictionarySingle.put("one", 1);
        dictionarySingle.put("two", 2);
        dictionarySingle.put("three", 3);
        dictionarySingle.put("four", 4);
        dictionarySingle.put("five", 5);
        dictionarySingle.put("six", 6);
        dictionarySingle.put("seven", 7);
        dictionarySingle.put("eight", 8);
        dictionarySingle.put("nine", 9);
    }
    private static Map<String, Integer> dictionaryDouble = new HashMap<>();
    static {
        dictionaryDouble.put("ten", 10);
        dictionaryDouble.put("eleven", 11);
        dictionaryDouble.put("twelve", 12);
        dictionaryDouble.put("thirteen", 13);
        dictionaryDouble.put("fourteen", 14);
        dictionaryDouble.put("fiveteen", 15);
        dictionaryDouble.put("sixteen", 16);
        dictionaryDouble.put("seventeen", 17);
        dictionaryDouble.put("eighteen", 18);
        dictionaryDouble.put("nineteen", 19);
    }

    private static Map<String, Integer> dictionaryFurther = new HashMap<>();
    static {
        dictionaryFurther.put("twenty", 20);
        dictionaryFurther.put("thirty", 30);
        dictionaryFurther.put("forty", 40);
        dictionaryFurther.put("fifty", 50);
        dictionaryFurther.put("sixty", 60);
        dictionaryFurther.put("seventy", 70);
        dictionaryFurther.put("eighty", 80);
        dictionaryFurther.put("ninety", 90);
    }

    private static Map<String, Integer> dictionaryMultipliers = new HashMap<>();
    static {
        dictionaryMultipliers.put("hundred", 100);
        dictionaryMultipliers.put("thousand", 1000);

    }

}
