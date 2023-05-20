package org.andreypodymov.sorting;

public class SelectionSort implements ArraySort <Integer> {

    @Override
    public void sort(Integer[] arr) {
        int min, temp;
        for (int i = 0; i < arr.length ; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}
