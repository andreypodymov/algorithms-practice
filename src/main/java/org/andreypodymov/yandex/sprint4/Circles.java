package org.andreypodymov.yandex.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class Circles {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Set<String> set = new LinkedHashSet<>();
            int num = readInt(reader);
            for (int i = 0; i < num; i++) {
                String value = readString(reader);
                set.add(value);
            }
            set.forEach(System.out::println);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static String readString(BufferedReader reader) throws IOException {
        return reader.readLine();
    }
}
