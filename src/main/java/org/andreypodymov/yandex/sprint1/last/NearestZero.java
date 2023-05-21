package org.andreypodymov.yandex.sprint1.last;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
    run-report: 87563120
    contest: 22450. Sprint 1 final A
    author: arpodymov
 */

public class NearestZero {
    public static int[] solve(int[] nums, int size) {
        // Count != 0
        for(int i = 0; i < size; ++i) {
            if (nums[i] != 0) {
                nums[i] = size;
            }
        }
        // Forward
        for(int i = 1; i < size; ++i) {
            nums[i] = nums[i] == 0 ? 0 : nums[i - 1] + 1;
        }
        // Backward
        for (int i = size - 2; i >= 0; --i) {
            nums[i] = nums[i] == 0 ? 0 : Math.min(nums[i + 1] + 1, nums[i]);
        }
        return nums;
    }

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int size = readInt(reader);
            int[] result = solve(readIntArray(reader, size), size);
            writer.write(formatAnswer(result));
        }
    }

    private static String formatAnswer(int[] array) {
        return Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
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
}