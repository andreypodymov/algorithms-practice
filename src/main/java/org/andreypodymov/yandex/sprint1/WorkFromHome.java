package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class WorkFromHome {
    public static String solve(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        Stack<Integer> st = new Stack<>();
        while (num > 0) {
            st.push(num % 2);
            num = num / 2;
        }
        while (!(st.isEmpty())) {
            sb.append(st.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num = readInt(reader);
            System.out.println(solve(num));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
