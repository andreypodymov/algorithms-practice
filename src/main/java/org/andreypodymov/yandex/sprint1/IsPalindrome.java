package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IsPalindrome {
    public static boolean isPalindrome(String value) {
        String prepared = value.replaceAll("[^A-Za-z]+","").toLowerCase();
        char[] chars = prepared.toCharArray();
        int length = chars.length;
        int middleCharIndex = length / 2;
        for (int i = 0; i < middleCharIndex; i++) {
            if (chars[i] != chars[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = readString(reader);
            String strResult = isPalindrome(str) ? "True" : "False";
            System.out.println(strResult);
        }
    }

    private static String readString(BufferedReader reader) throws IOException {
        return reader.readLine();
    }
}
