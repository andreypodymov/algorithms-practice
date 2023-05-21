package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LongestWord {
    public static Object[] solve(List<String> words) {
        int maxLength = 0;
        String maxWord = "";
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
                maxWord = word;
            }
        }
        return new Object[]{maxWord, maxLength};
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            readInt(reader);
            Object[] result = solve(readList(reader));
            System.out.println(result[0]);
            System.out.println(result[1]);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return  Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }
}
