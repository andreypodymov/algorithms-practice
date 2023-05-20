package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionValue {
    public static int functionValue(int a, int x, int b, int c) {
        return a * x * x + b * x + c;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> readList = readList(reader);
            System.out.println(functionValue(readList.get(0), readList.get(1), readList.get(2), readList.get(3)));
        }
    }
    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
