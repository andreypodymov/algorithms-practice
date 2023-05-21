package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySum {
    public static String solve(String a, String b) {
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        int delta = a.length() - b.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < delta; i++) {
            sb.append("0");
        }
        b = sb + b;
        sb = new StringBuilder();
        int carry = 0;
        for (int i = a.length() - 1; i >= 0; --i) {
            int c = Character.getNumericValue(b.charAt(i)) + Character.getNumericValue(a.charAt(i)) + carry;
            if (c > 1) {
                c -= 2;
                carry = 1;
            }
            else {
                carry = 0;
            }
            sb.append(c);
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(solve(a, b));
        }
    }
}
