package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ListForm {
    public static String solve(int size, List<Integer> form, int add) {
        List<Integer> addDigits = String.valueOf(add).chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());
        if (form.size() < addDigits.size()) {
            List<Integer> temp = form;
            form = addDigits;
            addDigits = temp;
        }
        int delta = form.size() - addDigits.size();
        for (int i = 0; i < delta; i++) {
            addDigits.add(0, 0);
        }
        StringJoiner joiner = new StringJoiner(" ");
        int carry = 0;
        for (int i = form.size() - 1; i >= 0; --i) {
            int c = form.get(i) + addDigits.get(i) + carry;
            if (c >= 10) {
                c -= 10;
                carry = 1;
            }
            else {
                carry = 0;
            }
            joiner.add(String.valueOf(c));
        }
        if (carry == 1) {
            joiner.add(String.valueOf(carry));
        }
        return new StringBuilder(joiner.toString()).reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int size = readInt(reader);
            List<Integer> form = readList(reader);
            int add = readInt(reader);
            System.out.println((solve(size, form, add)));
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
