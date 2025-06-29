package com.test.algo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 Вы являетесь одним из разработчиков программного обеспечения боевой информационной системы для ракетного крейсера нового поколения РК-2000.
 Один из компонентов этой системы отвечает за решение задач тактического маневрирования. В настоящее время вы занимаетесь решением задачи о сближении с целью.

Заданы координаты x0 и y0 цели в начальный момент времени, а также вектор (Vx; Vy) ее скорости.
Считается, что цель движется равномерно и прямолинейно. В начальный момент времени РК-2000 находится в начале координат.
Его максимальная скорость равна V.

Необходимо выяснить, может ли РК-2000 через заданное время t оказаться ровно на заданном расстоянии d от цели.
Для простоты считайте, что РК-2000 может мгновенно изменять свою скорость.
 */

public class Task492 {
    private static final String INPUT = "INPUT.TXT";
    private static final String OUTPUT = "OUTPUT.TXT";
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static long x0, y0, vx, vy, v, t, d;

    public static void main(String[] args) throws IOException {
        readData();
        String result = solve();
        writeResult(result);
    }

    private static String solve() {
        long xt = x0 + vx * t;
        long yt = y0 + vy * t;
        long distance = xt * xt + yt * yt;
        long maxAvailable = d + v * t;
        long maxDistance = maxAvailable * maxAvailable;
        long minAvailable = Math.max(0, d - v * t);
        long minDistance = minAvailable * minAvailable;
        if (minDistance <= distance && distance <= maxDistance) {
            return YES;
        }
        return NO;
    }

    private static void readData() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(INPUT))) {
            x0 = scanner.nextLong();
            y0 = scanner.nextLong();
            vx = scanner.nextLong();
            vy = scanner.nextLong();
            v = scanner.nextLong();
            t = scanner.nextLong();
            d = scanner.nextLong();
        }
    }

    private static void writeResult(String result) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT))) {
            bufferedWriter.append(result);
        }
    }
}
