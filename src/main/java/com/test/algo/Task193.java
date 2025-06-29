package com.test.algo;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
На поле N×M клеток (N строк и M столбцов) положили K прямоугольников один поверх другого в случайном порядке.
Длины сторон прямоугольников выражаются целым числом клеток. Прямоугольники не выходят за границы поля.
Границы прямоугольников совпадают с границами клеток поля.

Получившуюся ситуацию записали в таблицу чисел (каждой клетке поля соответствует клетка таблицы).
Если клетка поля не закрыта прямоугольником, то в соответствующую клетку таблицы записали число 0.
Если же клетка закрыта одним или несколькими прямоугольниками,
то в соответствующую клетку таблицы записали число, соответствующее номеру самого верхнего прямоугольника, закрывающего эту клетку.

Требуется написать программу, которая определит положение и размеры прямоугольников.
Гарантируется, что во входных данных содержится информация, которой достаточно для однозначного определения размеров прямоугольников.

*/
public class Task193 {
    private static final String INPUT = "INPUT.TXT";
    private static final String OUTPUT = "OUTPUT.TXT";
    private static final String RESULT = "%d %d %d %d";
    private static final int MAX_VALUE = 256;
    private static int K;
    private static int[] minX = new int[MAX_VALUE];
    private static int[] maxX = new int[MAX_VALUE];
    private static int[] minY = new int[MAX_VALUE];
    private static int[] maxY = new int[MAX_VALUE];
    private static boolean[] visible = new boolean[MAX_VALUE];
    private static int hiddenMinX = MAX_VALUE;
    private static int hiddenMaxX;
    private static int hiddenMinY = MAX_VALUE;
    private static int hiddenMaxY;

    public static void main(String[] args) throws IOException {
        Arrays.fill(minX, MAX_VALUE);
        Arrays.fill(minY, MAX_VALUE);
        resolve();
        writeResult();
    }

    private static void resolve() throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(INPUT))) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            K = scanner.nextInt();
            for (int y = N - 1; y >= 0; y--) {
                for (int x = 0; x < M; x++) {
                    int rectangle = scanner.nextInt();
                    if (rectangle != 0) {
                        updateRectangleInfo(rectangle, x, y);
                    }
                }
            }
        }
    }

    private static void updateRectangleInfo(int rectangle, int x, int y) {
        minX[rectangle] = Math.min(minX[rectangle], x);
        maxX[rectangle] = Math.max(maxX[rectangle], x);
        minY[rectangle] = Math.min(minY[rectangle], y);
        maxY[rectangle] = Math.max(maxY[rectangle], y);
        visible[rectangle] = true;
        hiddenMinX = Math.min(hiddenMinX, minX[rectangle]);
        hiddenMaxX = Math.max(hiddenMaxX, maxX[rectangle]);
        hiddenMinY = Math.min(hiddenMinY, minY[rectangle]);
        hiddenMaxY = Math.max(hiddenMaxY, maxY[rectangle]);
    }

    private static void writeResult() throws IOException {
        try (PrintWriter printWriter = new PrintWriter(OUTPUT)) {
            String hiddenRectangle = String.format(RESULT, hiddenMinX, hiddenMinY, hiddenMaxX + 1, hiddenMaxY + 1);
            for (int i = 1; i <= K; i++) {
                String result = hiddenRectangle;
                if (visible[i]) {
                    result = String.format(RESULT, minX[i], minY[i], maxX[i] + 1, maxY[i] + 1);
                }
                printWriter.println(result);
            }
        }
    }
}
