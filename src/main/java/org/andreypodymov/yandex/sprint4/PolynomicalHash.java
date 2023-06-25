package org.andreypodymov.yandex.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolynomicalHash {

    public static long solve(int a, int m, String inputString) {
        int tmp = inputString.charAt(inputString.length() - 1);
        int length = a;
        for (int i = inputString.length() - 2; i >= 0; i--) {
            char c = inputString.charAt(i);
            tmp = Math.floorMod((tmp + (int) c * length), m);
            length = Math.floorMod(length * a,  m);
        }
        return tmp;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = readInt(reader);
            int m = readInt(reader);
            String s = readString(reader);
            System.out.println(solve(a, m, s));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static String readString(BufferedReader reader) throws IOException {
        return reader.readLine();
    }
}
