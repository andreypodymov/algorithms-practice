package org.andreypodymov.yandex.sprint3.last.A;

/*
    run-report: 88373328
    contest: 23815. Sprint 3 final A
    author: arpodymov

ПРИНЦИП РАБОТЫ
    - Улучшенная рекурсивная версия без поиска pivot с использованием средней точки
    - https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/

ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ
    Бинарный поиск – это алгоритм поиска с использованием принципа "разделяй и влавствуй",
    который используется для поиска целевых элементов в массиве, где элементы  расположены в порядке возрастания.

ВРЕМЕННАЯ СЛОЖНОСТЬ
    Бинарный поиск O(log n).
    Итого: O(log n)

ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
    Бинарный поиск O(1).
    Итого: O(1)
 */

public class Solution {
    private static int search(int arr[], int l, int h, int key) {
        if (l > h) {
            return -1;
        }
        int mid = (l + h) / 2;
        if (arr[mid] == key) {
            return mid;
        }
        if (arr[l] <= arr[mid]) {
            if (key >= arr[l] && key <= arr[mid]) {
                return search(arr, l, mid - 1, key);
            }
            return search(arr, mid + 1, h, key);
        }
        if (key >= arr[mid] && key <= arr[h]) {
            return search(arr, mid + 1, h, key);
        }
        return search(arr, l, mid - 1, key);
    }

    public static int brokenSearch(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }
}

