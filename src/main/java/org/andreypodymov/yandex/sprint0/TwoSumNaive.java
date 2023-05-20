package org.andreypodymov.yandex.sprint0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwoSumNaive {

    // Если ответ существует, верните список из двух элементов
    // Если нет - то верните пустой список 
    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int first = arr.get(i);
                int second = arr.get(j);
                if (first + second == targetSum) {
                    return Arrays.asList(first,second);
                }
            }
        }
       return new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int targetSum = readInt(reader);
            List<Integer> result = twoSum(arr, targetSum);
            if (result.isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(result.get(0) + " " + result.get(1));
            }
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