package sorting;

/**
 * @author Andrei Podymov <arpodymov@gmail.com>
 */
public class InsertionSort implements ArraySort <Integer> {

    @Override
    public void sort(Integer[] arr) {
        int i, j, key;
        for (i = 1; i < arr.length; i++) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
}
