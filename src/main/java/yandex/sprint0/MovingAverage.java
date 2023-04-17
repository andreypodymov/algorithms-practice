package yandex.sprint0;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MovingAverage {

    private static List<Double> movingAverage(int n, List<Integer> arr, int windowSize) {

        List<Double> result = new ArrayList<>();
        int currentSum = 0;
        for (int i = 0; i < windowSize; i++) {
            currentSum += arr.get(i);
        }
        result.add(((double)currentSum / windowSize));
        for (int i = 0; i < n - windowSize; i++) {
            currentSum -= arr.get(i);
            currentSum += arr.get(i+windowSize);
            result.add(((double)currentSum / windowSize));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int windowSize = readInt(reader);
            List<Double> result = movingAverage(n, arr, windowSize);
            for (double elem : result) {
                writer.write(elem + " ");
            }
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