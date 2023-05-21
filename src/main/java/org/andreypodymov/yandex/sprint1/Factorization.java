package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Factorization {
    public static List<Integer> solve(int num) {
        List<Integer> result = new ArrayList<>();
        double sqrt = Math.sqrt(num);
        int val = num;
        int m = 2;
        while (val > 1 && m <= sqrt) {
            if (val % m == 0) {
                result.add(m);
                val /= m;
            }
            else if (m == 2) {
                m++;
            }
            else {
                m += 2;
            }
        }
        if (val != 1) {
            result.add(val);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num = readInt(reader);
            System.out.println(formatAnswer(solve(num)));
        }
    }

    private static String formatAnswer(List<Integer> array) {
        return array.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
