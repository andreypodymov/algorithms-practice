package org.andreypodymov.yandex.sprint4.last;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
/*
    run-report: 88577828
    contest: 24414. Sprint 4 final A
    author: arpodymov
ПРИНЦИП РАБОТЫ
    Отображение, которое каждому объекту ставит в соответствие его расположение, называют «поисковым индексом».
    При разработка поиского движка, сначала строим индекс, потом ищем с его использованием.
ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ
    Поисковый индекс index корректно создается методом fullIndex, который учитывает веса слов в каждом файле и сохраняет их в индексе.
    Метод freeTextQuery выполняет поиск в индексе на основе запроса string. Он обновляет релевантность каждого файла в массиве result путем вычитания веса каждого слова запроса из соответствующего элемента.
    Ограничение количества результатов выполняется путем сортировки массива result по убыванию весов и выбора limit наиболее релевантных результатов.
    Таким образом, код правильно выполняет индексацию файлов, выполняет поиск запросов и возвращает релевантные результаты, ограниченные заданным количеством.
ВРЕМЕННАЯ СЛОЖНОСТЬ
    N - количество документов, M - количество запросов.
    Построение индекса - О(N^2).
    Запрос - О(M)
ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
    Для построения индекса - О(N) памяти
 */
public class SearchEngine {
    private static final int DOCS_LIMIT = 5;

    private static Map<String, Map<Integer, Integer>> fullIndex(String[][] files) {
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

    private static List<Integer> query(Map<String, Map<Integer, Integer>> index,
                                      String query,
                                      int limit,
                                      int nFiles) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < nFiles; i++) {
            result.add(new int[]{0, i});
        }
        String[] words = query.split(" ");
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
        for (int[] ints : result) {
            int weight = ints[0];
            int indexValue = ints[1];
            if (weight < 0) {
                queryResult.add(indexValue);
            }
            if (queryResult.size() == limit) {
                break;
            }
        }
        return queryResult;
    }

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int nFiles = readInt(reader);
            String[][] files = new String[nFiles][];
            for (int i = 0; i < nFiles; i++) {
                String[] line = readLine(reader);
                files[i] = line;
            }
            Map<String, Map<Integer, Integer>> index = fullIndex(files);
            int nQuery = readInt(reader);
            for (int i = 0; i < nQuery; i++) {
                String query = reader.readLine();
                List<Integer> result = query(index, query, DOCS_LIMIT, nFiles);
                writer.write(formatResult(result));
                writer.write(System.lineSeparator());
            }
        }
    }

    private static String formatResult(List<Integer> result) {
        return result.stream().map(t -> String.valueOf(t + 1))
                .collect(Collectors.joining(" "));
    }

    private static String[] readLine(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
