package org.andreypodymov.yandex.sprint4.last;

import java.util.*;

public class A {
    public static Map<String, Map<Integer, Integer>> fullIndex(String[][] files) {
        Map<String, Map<Integer, Integer>> index = new HashMap<>();

        for (int filename = 0; filename < files.length; filename++) {
            String[] words = files[filename];
            Map<String, Integer> wordCount = new HashMap<>();

            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                String word = entry.getKey();
                int weight = entry.getValue();

                if (!index.containsKey(word)) {
                    index.put(word, new HashMap<>());
                }

                index.get(word).put(filename, weight);
            }
        }

        return index;
    }

    public static List<Integer> freeTextQuery(Map<String, Map<Integer, Integer>> index, String string, int limit, int nDocs) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < nDocs; i++) {
            result.add(new int[]{0, i});
        }

        String[] words = string.split(" ");

        for (String word : new HashSet<>(Arrays.asList(words))) {
            if (index.containsKey(word)) {
                Map<Integer, Integer> wordIndex = index.get(word);
                for (Map.Entry<Integer, Integer> entry : wordIndex.entrySet()) {
                    int filename = entry.getKey();
                    int weight = entry.getValue();
                    result.get(filename)[0] -= weight;
                }
            }
        }

        List<Integer> queryResult = new ArrayList<>();
        result.sort(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < result.size(); i++) {
            int weight = result.get(i)[0];
            int indexValue = result.get(i)[1];

            if (weight < 0) {
                queryResult.add(indexValue);
            }

            if (queryResult.size() == limit) {
                break;
            }
        }

        return queryResult;
    }

    public static void main(String[] args) {
        String[][] files = {
                {"word1", "word2", "word3"},
                {"word2", "word3", "word4"},
                {"word1", "word4", "word5"}
        };

        Map<String, Map<Integer, Integer>> index = fullIndex(files);

        // Print the index
        for (Map.Entry<String, Map<Integer, Integer>> entry : index.entrySet()) {
            String word = entry.getKey();
            Map<Integer, Integer> fileCount = entry.getValue();

            System.out.print(word + ": ");
            for (Map.Entry<Integer, Integer> fileEntry : fileCount.entrySet()) {
                int filename = fileEntry.getKey();
                int count = fileEntry.getValue();
                System.out.print("[" + filename + ": " + count + "] ");
            }
            System.out.println();
        }
    }
}
