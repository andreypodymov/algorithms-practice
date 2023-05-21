package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FourPow {
    public static boolean solve(int num) {
        double result = log(4, num);
        return Math.round(result) == result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num = readInt(reader);
            String strResult = solve(num) ? "True" : "False";
            System.out.println(strResult);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static double log(double base, double logNumber) {
        return Math.log(logNumber) / Math.log(base);
    }
}
