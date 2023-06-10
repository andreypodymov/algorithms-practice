package org.andreypodymov.yandex.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;

public class BracketSequence {

    private static final Map<Character, Character> pairsMap =
            Map.of('{','}', '[', ']', '(', ')');

    public static boolean solve(char[] chars) {
        if (chars.length == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            char current = chars[i];
            if (pairsMap.containsKey(current)) {
                stack.push(current);
            }
            else if (pairsMap.containsValue(current)) {
                if (stack.isEmpty()) {
                    return false;
                }
                else {
                    char latestOpened = stack.pop();
                    if (!pairsMap.containsKey(latestOpened)) {
                        return false;
                    }
                    if (pairsMap.get(latestOpened) != current) {
                        return false;
                    }
                }

            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(formatAnswer(solve(readCharList(reader))));
        }
    }

    private static String formatAnswer(boolean result) {
        return result ? "True" : "False";
    }

    private static char[] readCharList(BufferedReader reader) throws IOException {
        return reader.readLine().toCharArray();
    }
}
