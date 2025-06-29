package com.test.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
 * Заданы три числа: a, b, c. Необходимо выяснить, можно ли так переставить цифры в числах a и b, чтобы в сумме получилось c.
 */
public class Test346 {
    private static final String INPUT = "INPUT.TXT";
    private static final String OUTPUT = "OUTPUT.TXT";
    private static char[] digitsA;
    private static boolean[] usedADigits;
    private static byte[] availableDigitsB = new byte[10];
    private static byte sizeBWithoutZero = 0;
    private static long c;
    private static String result = "NO";
    private static boolean solved;

    public static void main(String[] args) throws IOException {
        readData();
        findSolutions(new StringBuilder(), 0);
        writeResult();
    }

    private static void readData() throws IOException {
        String[] input;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT))) {
            input = bufferedReader.readLine().split(" ");
        }
        digitsA = input[0].toCharArray();
        usedADigits = new boolean[digitsA.length];
        Arrays.sort(digitsA);
        for (char ch : input[1].toCharArray()) {
            byte bDigit = (byte) Character.getNumericValue(ch);
            if (bDigit != 0) {
                sizeBWithoutZero++;
            }
            availableDigitsB[bDigit] += 1;
        }
        c = Long.parseLong(input[2]);
    }

    private static void findSolutions(StringBuilder current, int depth) {
        if (depth == digitsA.length) {
            long newA = Long.parseLong(current.toString());
            solved = createNewBIfPossible(newA);
            return;
        }
        for (int i = 0; i < digitsA.length && !solved; i++) {
            if (checkIfDigitIsUsedOrDuplicate(i)) {
                continue;
            }
            usedADigits[i] = true;
            current.append(digitsA[i]);
            findSolutions(current, depth + 1);
            current.deleteCharAt(current.length() - 1);
            usedADigits[i] = false;
        }
    }

    private static boolean checkIfDigitIsUsedOrDuplicate(int i) {
        int prev = i - 1;
        return usedADigits[i] || (prev >= 0 && digitsA[i] == digitsA[prev] && !usedADigits[prev]);
    }

    private static boolean createNewBIfPossible(long a) {
        String newB = Long.toString(c - a);
        if (a > c || newB.length() < sizeBWithoutZero) {
            return false;
        }
        byte sizeNewBWithoutZero = 0;
        byte[] availableDigits = Arrays.copyOf(availableDigitsB, availableDigitsB.length);
        for (char ch : newB.toCharArray()) {
            byte digit = (byte) Character.getNumericValue(ch);
            availableDigits[digit]--;
            if (digit != 0) {
                sizeNewBWithoutZero++;
            }
            if (availableDigits[digit] < 0) {
                return false;
            }
        }
        if (sizeNewBWithoutZero != sizeBWithoutZero) {
            return false;
        }
        result = "YES" + System.lineSeparator() + a + " " + newB;
        return true;
    }

    private static void writeResult() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT))) {
            bufferedWriter.append(result);
        }
    }
}
