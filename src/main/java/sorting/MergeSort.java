package sorting;

/**
 * @author Andrei Podymov <arpodymov@gmail.com>
 */
public class MergeSort implements ArraySort <Integer> {

    @Override
    public void sort(Integer[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public void mergeSort(Integer[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private void merge(Integer[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int left[] = new int[n1 + 1];
        int right[] = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            left[i] = arr[p+i];
        }

        for (int j = 0; j < n2; j++) {
            right[j] = arr[q+j+1];
        }

        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
        }
    }
}
