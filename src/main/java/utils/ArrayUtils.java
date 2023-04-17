package utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Andrei Podymov <arpodymov@gmail.com>
 */
public class ArrayUtils {
    private ArrayUtils() {}

    public static Integer[] generateRandomArray(int n, int min, int max) {
        Integer array[] = new Integer[n];
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

    public static boolean isArraySorted(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i+1] < arr[i]) {
                return false;
            }
        }
        return true;
    }
}
