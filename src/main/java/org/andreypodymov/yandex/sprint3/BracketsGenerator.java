package org.andreypodymov.yandex.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BracketsGenerator {

    private static void solve(int c, int n1, int n2, String prefix) {
        if (n1 == 0 && n2 == 0) {
            System.out.println(prefix);
        }
        else {
            if (n1 > 0) {
                solve(c+1, n1 - 1, n2, prefix + "(");
            }
            if (c > 0 && n2 > 0) {
                solve(c-1, n1, n2 - 1, prefix + ")");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num = readInt(reader);
            solve(0, num, num, "");
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
