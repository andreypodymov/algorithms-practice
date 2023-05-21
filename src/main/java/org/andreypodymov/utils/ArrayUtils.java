package org.andreypodymov.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Andrei Podymov <arpodymov@gmail.com>
 */
public class ArrayUtils {
    private ArrayUtils() {}

    public static int[] generateRandomArray(int n, int min, int max) {
        int array[] = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return array;
    }

    public static char[] generateRandomArray(int n) {
        char array[] = new char[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            char c = (char)(r.nextInt(26) + 'a');
            array[i] = c;
        }
        return array;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readIntArray(BufferedReader reader, int size) throws IOException {
        int[] result = new int[size];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; ++i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return result;
    }

    public static List<Integer> readIntList(BufferedReader reader) throws IOException {
        return  Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static boolean isArraySorted(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i+1] < arr[i]) {
                return false;
            }
        }
        return true;
    }
}
