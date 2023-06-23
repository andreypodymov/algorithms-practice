package org.andreypodymov.yandex.sprint3.last.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/*
    run-report: 88336118
    contest: 23815. Sprint 3 final B
    author: arpodymov

ПРИНЦИП РАБОТЫ
    Для реализации сортировки без использования дополнительной памяти выбирается опорный элемент.
    Затем два указателя (left и right) двигаются навстречу друг другу, чтобы разделить элементы на две группы: меньшие опорного и большие опорного.
    После этого указатели меняются местами и продолжают двигаться, пока не встретятся.

ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ
    Быстрая сортировка разделяет коллекцию на две примерно равные части, используя опорный элемент.
    Элементы меньше опоры перемещаются влево, а элементы больше опоры - вправо.
    Этот процесс повторяется для двух частей коллекции до полной сортировки.
    Можно сортировать по любому выбранному свойству, не обязательно целым числам.

ВРЕМЕННАЯ СЛОЖНОСТЬ
    На каждом уровне рекурсии в потребуется O(n) операций;
    Глубина рекурсии зависит от входных данных и способа выбора опорного элемента.
    В худшем случае составляет O(n), когда один из подмассивов пуст на каждом уровне рекурсии.
    В лучшем случае - O(log n), когда в качестве опорного элемента выбирается средний элемент.
    Итого:
    Лучший случай - О(n) * O(log n) = О(nlog n)
    Среднее - О(n * log n)
    Худший случай - О(n) * O(n) = О(n^2)

ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
   Не используем доп. массивы. Итого: O(1)
 */

public class Solution {
    private final static Random random = new Random();

    static class User implements Comparable<User> {
        private final String username;
        private final int solved;
        private final int errors;

        public User(String username, int solved, int errors) {
            this.username = username;
            this.solved = solved;
            this.errors = errors;
        }

        @Override
        public int compareTo(User other) {
            if (this.solved != other.solved) {
                return Integer.compare(other.solved, this.solved);
            }
            if (this.errors != other.errors) {
                return Integer.compare(this.errors, other.errors);
            }
            return this.username.compareTo(other.username);
        }

        @Override
        public String toString() {
            return username;
        }
    }

    public static void quicksort(List<User> users, int low, int high) {
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;

        User pivot = users.get(random.nextInt(high - low + 1) + low);
        while (left <= right) {
            while (users.get(left).compareTo(pivot) < 0) {
                left++;
            }
            while (users.get(right).compareTo(pivot) > 0) {
                right--;
            }
            if (left <= right) {
                User temp = users.get(left);
                users.set(left, users.get(right));
                users.set(right, temp);
                left++;
                right--;
            }
        }
        quicksort(users, low, right);
        quicksort(users, left, high);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<User> users = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<String> input = readList(reader);
                String username = input.get(0);
                int solved = Integer.parseInt(input.get(1));
                int errors = Integer.parseInt(input.get(2));
                users.add(new User(username, solved, errors));
            }
            quicksort(users, 0, n - 1);
            users.forEach(System.out::println);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return  Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }
}

