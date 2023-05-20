package org.andreypodymov.yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Neighbours {
    public static String neighbours(int[][] matrix, int n, int m, int y, int x) {
        List<Integer> result = new ArrayList<>();
        if (y - 1 >= 0) {
            result.add(matrix[y-1][x]);
        }
        if (y + 1 < n) {
            result.add(matrix[y+1][x]);
        }
        if (x - 1 >= 0) {
            result.add(matrix[y][x-1]);
        }
        if (x + 1 < m) {
            result.add(matrix[y][x+1]);
        }
        Collections.sort(result);
        List<String> resultList = result.stream().map(Object::toString).collect(Collectors.toList());
        return String.join(" ", resultList);
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            int m = readInt(reader);
            int [][] matrix = new int[n][m];
            for (int i = 0; i < n; i++) {
                List<Integer> readList = readList(reader);
                matrix[i] = readList.stream().mapToInt(Integer::intValue).toArray();
            }
            int y = readInt(reader);
            int x = readInt(reader);
            System.out.println(neighbours(matrix, n, m, y, x));
        }
    }
    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return  Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
