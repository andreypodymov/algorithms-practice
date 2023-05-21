package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LongestWord {
    public static void longestWord(List<String> words) {
        int maxLength = 0;
        String maxWord = "";
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
                maxWord = word;
            }
        }
        System.out.println(maxWord);
        System.out.println(maxLength);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            readInt(reader);
            List<String> str = readList(reader);
            longestWord(str);
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
