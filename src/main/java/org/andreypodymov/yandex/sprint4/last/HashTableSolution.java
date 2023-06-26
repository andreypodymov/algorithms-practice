package org.andreypodymov.yandex.sprint4.last;

import java.io.*;
import java.util.Map;
/*
    run-report: 88553988
    contest: 24414. Sprint 4 final B
    author: arpodymov
ПРИНЦИП РАБОТЫ
    Структура HashTable - неупорядоченная коллекция ассоциаций между ключами и значениями.
    Мы используем Bucket, состоящий из key и value, чтобы создать класс HashTable.
    Hash функция - Jenkins hash function.
    В качестве техники разрешения коллизий используется линейное пробирование с функцией повторного хэширования.
ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ
    Решение проблемы коллизий в хэш-таблице требует систематического подхода.
    Один из методов - открытая адресация - заключается в поиске свободного бакета для элемента, создавшего коллизию.
    Мы перемещаемся по бакетам, начиная с оригинальной позиции хэш-значения, пока не найдем пустой.
    Этот метод, известный как линейное пробирование, позволяет разрешать коллизии.
ВРЕМЕННАЯ СЛОЖНОСТЬ
    Когда мы хотим найти элемент, мы используем хэш-функцию
    Эта операция поиска имеет O(1) - на вычисление хэш-значения требуется константное время,
    как и на переход по найденному индексу.
    В случае неэффективной хэш-фукнции может быть вырождение до O(n).
ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
    Map, максимальный размер которого определяется заданным числом N, занимает O(N) памяти.
 */
public class HashTableSolution {

    public interface MyMap {
        void put(int key, Integer value);
        Integer get(int key);
        Integer delete(int key);
    }

    public static class HashTable implements MyMap {
        public static class HashEntry {
            private final int key;
            private final Integer value;

            public HashEntry(int key, Integer value) {
                this.key = key;
                this.value = value;
            }

            public int getKey() {
                return key;
            }

            public Integer getValue() {
                return value;
            }
        }

        private final static int TABLE_SIZE = 10000000;
        private final HashEntry[] buckets;

        public HashTable() {
            buckets = new HashEntry[TABLE_SIZE];
        }

        @Override
        public Integer get(int key) {
            int position = findPosition(hash(key), key);
            if (buckets[position] == null) {
                return null;
            }
            return buckets[position].getValue();
        }

        @Override
        public Integer delete(int key) {
            Integer data = get(key);
            if (data != null) {
                put(key, null);
                return data;
            }
            return null;
        }

        @Override
        public void put(int key, Integer value) {
            int position = findPosition(hash(key), key);
            buckets[position] = new HashEntry(key, value);
        }

        private static int hash(int key) {
            key += (key << 12);
            key ^= (key >>> 22);
            key += (key << 4);
            key ^= (key >>> 9);
            key += (key << 10);
            key ^= (key >>> 2);
            key += (key << 7);
            key ^= (key >>> 12);
            return key;
        }

        private int rehash(int hash, int step) {
            return hash(hash) + step + 3 * step * step;
        }

        private int findPosition(int hash, int key) {
            int position = Math.floorMod(hash, TABLE_SIZE);
            int step = 1;
            while (buckets[position] != null && buckets[position].getKey() != key) {
                hash = rehash(hash, step);
                position = Math.floorMod(hash, TABLE_SIZE);
                step++;
            }
            return position;
        }
    }

    interface CLICommand {
        void execute(HashTable deque, int key, Integer value, BufferedWriter writer) throws IOException;
    }

    static class PutCommand implements CLICommand {
        @Override
        public void execute(HashTable deque, int key, Integer value, BufferedWriter writer) {
            deque.put(key, value);
        }
    }

    static class GetCommand implements CLICommand {
        @Override
        public void execute(HashTable deque, int key, Integer value, BufferedWriter writer) throws IOException {
            Integer result = deque.get(key);
            if (result != null) {
                writelnInt(writer, result);
            }
            else {
                writelnString(writer, "None");
            }
        }
    }

    static class DeleteCommand implements CLICommand {
        @Override
        public void execute(HashTable deque, int key, Integer value, BufferedWriter writer) throws IOException {
            Integer result = deque.delete(key);
            if (result != null) {
                writelnInt(writer, result);
            }
            else {
                writelnString(writer, "None");
            }
        }
    }

    private static final java.util.Map<String, CLICommand> commandsMap = Map.of(
            "get", new GetCommand(),
            "put", new PutCommand(),
            "delete", new DeleteCommand()
    );

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int commandsSize = readInt(reader);
            HashTable hashTable = new HashTable();
            for (int i = 0; i < commandsSize; i++) {
                String[] commandLine = readLine(reader);
                CLICommand cliCommand = commandsMap.get(commandLine[0]);
                Integer key = null;
                Integer value = null;
                if (commandLine.length >= 2) {
                    key = Integer.parseInt(commandLine[1]);
                }
                if (commandLine.length == 3) {
                    value = Integer.parseInt(commandLine[2]);
                }
                cliCommand.execute(hashTable, key, value, writer);
            }
        }
    }

    private static String[] readLine(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static void writelnInt(BufferedWriter writer, int value) throws IOException {
        writelnString(writer, String.valueOf(value));
    }

    private static void writelnString(BufferedWriter writer, String value) throws IOException {
        writer.write(value);
        writer.newLine();
    }
}
