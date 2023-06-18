package org.andreypodymov.yandex.sprint3.last.A;

/*
    run-report: 88335686
    contest: 23815. Sprint 3 final A
    author: arpodymov

ПРИНЦИП РАБОТЫ
    - Ищем опорную точку
    - Разделим массив на два отсортированных массива
    - Выполним бинарный поиск

ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ
    Бинарный поиск – это алгоритм поиска с использованием принципа "разделяй и влавствуй",
    который используется для поиска целевых элементов в массиве, где элементы  расположены в порядке возрастания.

ВРЕМЕННАЯ СЛОЖНОСТЬ
    Бинарный поиск O(log n).
    Поиск опорной точки в массиве O(log n)
    Итого: O(log n)

ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
    Бинарный поиск O(1).
    Поиск опорной точки в массиве O(1)
    Итого: O(1)
 */

public class Solution {
    public static int binarySearch(int[] arr, int target, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            int candidate = arr[mid];
            if (candidate == target) {
                return mid;
            }
            if (candidate > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int findPivot(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= arr[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high != 0 ? high : arr.length - 1;
    }

    public static int brokenSearch(int[] nums, int target) {
        int pivot = findPivot(nums);
        if (nums[pivot] == target) {
            return pivot;
        }
        if (nums[0] <= target) {
            return binarySearch(nums, target, 0, pivot - 1);
        } else {
            return binarySearch(nums, target, pivot + 1, nums.length - 1);
        }
    }
}

