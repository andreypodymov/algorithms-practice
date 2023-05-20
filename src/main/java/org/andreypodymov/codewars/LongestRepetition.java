package org.andreypodymov.codewars;
/*
    For a given string s find the character c (or C) with longest consecutive repetition and return.
    https://www.codewars.com/kata/586d6cefbcc21eed7a001155/train/java
 */
public class LongestRepetition {

    public static Object[] longestRepetition(String s) {
        if (s.length() == 0) {
            return new Object[]{"", 0};
        }
        int n = s.length();
        char it = s.charAt(0);
        char maxChar = it;
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 1; i < n; i++) {
            char iterated = s.charAt(i);
            if (iterated == it) {
                ++currentLength;
            }
            else {
                it = iterated;
                currentLength = 1;
            }
            if (currentLength > maxLength) {
                maxChar = iterated;
                maxLength = currentLength;
            }
        }
        return new Object[]{String.valueOf(maxChar), maxLength};
    }
}
