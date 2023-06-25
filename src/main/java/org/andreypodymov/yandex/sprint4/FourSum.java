package org.andreypodymov.yandex.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FourSum {

    static class Pair
    {
        public int x, y;

        Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static class LexicographicComparator implements Comparator<List<Long>> {
        @Override
        public int compare(List<Long> list1, List<Long> list2) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).compareTo(list2.get(i)) != 0) {
                    return list1.get(i).compareTo(list2.get(i));
                }
            }
            return 0;
        }
    }

    private static Set<List<Long>> solve(List<Long> arr, int target) {
        Map<Long, List<Pair>> map = new HashMap<>();
        Set<List<Long>> result = new TreeSet<>(new LexicographicComparator());
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                long val = target - (arr.get(i) + arr.get(j));
                if (map.containsKey(val)) {
                    for (Pair pair: map.get(val)) {
                        int x = pair.x;
                        int y = pair.y;
                        if ((x != i && x != j) && (y != i && y != j)) {
                            List<Long> rv = new ArrayList<>(List.of(arr.get(i), arr.get(j), arr.get(x), arr.get(y)));
                            Collections.sort(rv);
                            result.add(rv);
                        }
                    }
                }
                map.putIfAbsent(arr.get(i) + arr.get(j), new ArrayList<>());
                map.get(arr.get(i) + arr.get(j)).add(new Pair(i, j));
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num = readInt(reader);
            if (num <= 0) {
                System.out.println(0);
                return;
            }
            int target = readInt(reader);
            List<Long> list = readList(reader);
            Set<List<Long>> result = solve(list, target);
            System.out.println(result.size());
            result.forEach(i-> System.out.println(i.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "))));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        return  Arrays.stream(reader.readLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
