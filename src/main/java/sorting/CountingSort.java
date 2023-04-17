package sorting;

/**
 * @author Andrei Podymov <arpodymov@gmail.com>
 */
public class CountingSort implements ArraySort <Integer> {

    @Override
    public void sort(Integer[] arr) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int freq[] = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            freq[arr[i] - min]++;
        }

        int idx = 0;
        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i]; j++) {
                arr[idx++] = i + min;
            }
        }
    }
}
