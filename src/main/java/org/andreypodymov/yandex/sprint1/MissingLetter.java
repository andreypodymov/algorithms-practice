package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

public class MissingLetter {
    public static char solve(String a, String b) {
        int aSum =  CharBuffer.wrap(a.toCharArray()).chars().sum();
        int bSum =  CharBuffer.wrap(b.toCharArray()).chars().sum();
        return (char) Math.abs(aSum - bSum);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(solve(a, b));
        }
    }
}
