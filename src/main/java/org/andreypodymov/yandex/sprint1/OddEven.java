package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OddEven {
    public static boolean isSameOddEven(List<Integer> list) {
        int oddCount = 0;
        int evenCount = 0;
        for (Integer i : list) {
            if (i % 2 == 0) {
                evenCount++;
            }
            else {
                oddCount++;
            }
        }
        return oddCount == 0 || evenCount == 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String stringResult = isSameOddEven(readList(reader)) ? "WIN" : "FAIL";
            System.out.println(stringResult);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return  Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
