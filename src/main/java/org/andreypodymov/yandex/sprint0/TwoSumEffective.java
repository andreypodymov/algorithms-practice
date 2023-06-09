package org.andreypodymov.yandex.sprint0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andreypodymov
 */
public class TwoSumEffective {
    // Если ответ существует, верните список из двух элементов
    // Если нет - то верните пустой список
    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        Collections.sort(arr);
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            int leftNum = arr.get(left);
            int rightNum = arr.get(right);
            int sum = leftNum + rightNum;
            if (sum == targetSum) {
                return Arrays.asList(leftNum, rightNum);
            }
            if (sum > targetSum) {
                right--;
            }
            else {
                left++;
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
