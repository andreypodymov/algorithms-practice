package org.andreypodymov.yandex.sprint0;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Zipper {

    private static List<Integer> zip(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            result.add(a.get(i));
            result.add(b.get(i));
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> a = readList(reader);
            List<Integer> b = readList(reader);
            printList(zip(a, b), writer);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return  Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static<T> void printList(List<T> list, Writer writer) {
        list.forEach(elem -> {
                    try {
                        writer.write(String.valueOf(elem));
                        writer.write(" ");
                    } catch (IOException e) {

                    }
                }
        );
    }
}
