package com.test.algo;

import java.io.*;

/*
 * Требуется написать программу, которая из цифр двух натуральных чисел создает наименьшее возможное число, сохраняя при этом порядок следования цифр в этих числах.
 */
public class Task548 {
    private static final String INPUT = "INPUT.TXT";
    private static final String OUTPUT = "OUTPUT.TXT";
    private static String one;
    private static String two;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        readData();
        merge();
        writeResult();
    }

    private static void merge() {
        int i = 0;
        int j = 0;
        while (i < one.length() && j < two.length()) {
            if (firstIsSmaller(i, j)) {
                result.append(one.charAt(i++));
            } else {
                result.append(two.charAt(j++));
            }
        }
        result.append(one.substring(i));
        result.append(two.substring(j));
    }

    private static boolean firstIsSmaller(int i, int j) {
        for (; i < one.length() && j < two.length(); i++, j++) {
            char oneDigit = one.charAt(i);
            char twoDigit = two.charAt(j);
            if (oneDigit != twoDigit) {
                return oneDigit < twoDigit;
            }
        }
        return i != one.length();
    }

    private static void readData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT))) {
            one = reader.readLine();
            two = reader.readLine();
        }
    }

    private static void writeResult() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT))) {
            writer.write(result.toString());
        }
    }
}
