package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class СhaoticWeather {
    public static int solve(List<Integer> values) {
        int result = 0;
        if (values.size() <= 1) {
            return 1;
        }
        for (int i = 1; i < values.size() - 1; i++) {
            int now = values.get(i);
            int prev = values.get(i-1);
            int next = values.get(i + 1);
            if (now > prev && now > next) {
                result++;
            }
        }
        if (values.get(0) > values.get(1)) {
            result++;
        }
        if (values.get(values.size()-1) > values.get(values.size()-2)) {
            result++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            readInt(reader);
            System.out.println(solve(readList(reader)));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
