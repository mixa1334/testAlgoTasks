package com.test.algo;

import java.io.*;
import java.util.*;

/*
Требуется получить точное значение частного А/В для двух натуральных чисел A и B.
Входные данные
В единственной строке входного файла INPUT.TXT записано частное двух натуральных чисел,
не превышающих 1000. Числа разделены символом «/» без лишних пробелов.
Выходные данные
В выходной файл OUTPUT.TXT нужно вывести точное значение A/B без лишних точек, нулей и пробелов.
В случае присутствия бесконечной записи числа следует вывести период в скобках.
Например, неправильно выведены числа: 08.92, 3.20, 120.6(6), 0.(33), 5.(0), 2. , .3, 0.33(03) .
Их следует выводить как 8.92, 3.2, 120.(6), 0.(3), 5, 2, 0.3, 0.3(30) .
*/

public class Task109 {
    private static final String INPUT = "INPUT.TXT";
    private static final String OUTPUT = "OUTPUT.TXT";
    private static int a;
    private static int b;
    private static int fraction;

    public static void main(String[] args) throws IOException {
        readData();
        String result = calculate();
        writeResult(result);
    }

    private static String calculate() {
        StringBuilder result = new StringBuilder();
        fraction = calculateIntegerPart(result);
        if (fraction == 0) {
            return result.toString();
        }
        result.append(".");
        calculateFractionalPart(result);
        return result.toString();
    }

    private static int calculateIntegerPart(StringBuilder result) {
        result.append(a / b);
        return a % b;
    }

    private static void calculateFractionalPart(StringBuilder result) {
        HashMap<Integer, Integer> existNumbersPosition = new HashMap<>();
        StringBuilder fractionalPart = new StringBuilder();
        for (int i = 0; fraction != 0 && !existNumbersPosition.containsKey(fraction); i++) {
            existNumbersPosition.put(fraction, i);
            fraction *= 10;
            fractionalPart.append(fraction / b);
            fraction %= b;
        }
        if (existNumbersPosition.containsKey(fraction)) {
            int repeatIndex = existNumbersPosition.get(fraction);
            result.append(fractionalPart.substring(0, repeatIndex));
            result.append("(");
            result.append(fractionalPart.substring(repeatIndex, fractionalPart.length()));
            result.append(")");
        } else {
            result.append(fractionalPart);
        }
    }

    private static void readData() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT))) {
            String[] numbers = bufferedReader.readLine().split("/");
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[1]);
        }
    }

    private static void writeResult(String result) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT))) {
            bufferedWriter.append(result);
        }
    }
}
