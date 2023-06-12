package org.andreypodymov.yandex.sprint2.last;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    run-report: 88156245
    contest: 22781. Sprint 2 final A
    author: arpodymov

    -- ПРИНЦИП РАБОТЫ --
    Струкртура, которая объединяет стек и очередь называется дек. Для разработки используем кольцевой буфер, построенный
    поверх массива.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Решение будет работать, благадоря алгоритму заполнения и извлечения даннных из массива
    с использованием остатка от деления

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Добавление в дек спереди/сздаи O(1);
    Извлечение из дека спереди/сзади O(1);
    Проверки пуст/полон ли дек O(1);
    Итого: O(1)

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Дек построен поверх массива. Для N элементов занимает O(N) памяти.
    Итого: O(N)

    -- KNOWN ISSUES
    CLICommand содержит nullable item, что не очень красиво по дизайну для pop операций.
 */

class FullDequeException extends Exception {
    public FullDequeException(String message) {
        super(message);
    }
}

class EmptyDequeException extends Exception {
    public EmptyDequeException(String message) {
        super(message);
    }
}

interface DequeInterface {
    void pushBack(int item) throws FullDequeException;
    void pushFront(int item) throws FullDequeException;
    int popFront() throws EmptyDequeException;
    int popBack() throws EmptyDequeException;
}

class Deque implements DequeInterface {
    public int[] queue;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public Deque(int capacity) {
        this.queue = new int[capacity];
        this.capacity = capacity;
        this.head = 0;
        this.tail = 1;
        this.size = 0;
    }

    @Override
    public void pushBack(int item) throws FullDequeException {
        checkFullQueue();
        queue[tail] = item;
        tail = getNewIndex(tail, 1);
        size++;
    }

    @Override
    public void pushFront(int item) throws FullDequeException {
        checkFullQueue();
        queue[head] = item;
        head = getNewIndex(head, -1);
        size++;
    }

    @Override
    public int popFront() throws EmptyDequeException {
        checkEmptyQueue();
        head = getNewIndex(head, 1);
        int result = queue[head];
        size--;
        return result;
    }

    @Override
    public int popBack() throws EmptyDequeException {
        checkEmptyQueue();
        tail = getNewIndex(tail, -1);
        int result = queue[tail];
        size--;
        return result;
    }

    private void checkFullQueue() throws FullDequeException {
        if (this.size >= this.capacity) {
            throw new FullDequeException("Deque is full!");
        }
    }

    private void checkEmptyQueue() throws EmptyDequeException {
        if (this.size == 0) {
            throw new EmptyDequeException("Deque is empty!");
        }
    }

    private int getNewIndex(int index, int change) {
        // Используем Residual остаток от деления, по умолчанию % = Remainder
        return Math.floorMod(index + change, capacity);
    }
}

public class DequeCLIExecutor {
    interface CLICommand {
        void execute(Deque deque, Integer item, BufferedWriter writer) throws IOException;
    }

    static class PushBackCommand implements CLICommand {
        @Override
        public void execute(Deque deque, Integer item, BufferedWriter writer) throws IOException {
            try {
                deque.pushBack(item);
            } catch (FullDequeException e) {
                writelnString(writer,"error");
            }
        }
    }

    static class PushFrontCommand implements CLICommand {
        @Override
        public void execute(Deque deque, Integer item, BufferedWriter writer) throws IOException{
            try {
                deque.pushFront(item);
            } catch (FullDequeException e) {
                writelnString(writer,"error");
            }
        }
    }

    static class PopFrontCommand implements CLICommand {

        @Override
        public void execute(Deque deque, Integer item, BufferedWriter writer) throws IOException {
            try {
                writelnInt(writer,deque.popFront());
            } catch (EmptyDequeException e) {
                writelnString(writer,"error");
            }
        }
    }

    static class PopBackCommand implements CLICommand {
        @Override
        public void execute(Deque deque, Integer item, BufferedWriter writer) throws IOException {
            try {
                writelnInt(writer,deque.popBack());
            } catch (EmptyDequeException e) {
                writelnString(writer,"error");
            }
        }
    }

    private static final Map<String, CLICommand> commandsMap = Map.of(
            "push_front", new PushFrontCommand(),
            "push_back", new PushBackCommand(),
            "pop_front", new PopFrontCommand(),
            "pop_back", new PopBackCommand()
    );

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int commandsSize = readInt(reader);
            int capacity = readInt(reader);
            Deque deque = new Deque(capacity);
            for (int i = 0; i < commandsSize; i++) {
                List<String> readLine = readStringList(reader);
                // Не валидируем, подразумевая корректный ввод из 1 или 2 подстрок
                CLICommand cliCommand = commandsMap.get(readLine.get(0));
                // Для pop операций, второй подстроки не будет!
                Integer pushValue = null;
                if (readLine.size() == 2) {
                    pushValue = Integer.parseInt(readLine.get(1));
                }
                cliCommand.execute(deque, pushValue, writer);
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readStringList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }

    private static void writelnInt(BufferedWriter writer, int value) throws IOException {
        writelnString(writer, String.valueOf(value));
    }

    private static void writelnString(BufferedWriter writer, String value) throws IOException {
        writer.write(value);
        writer.newLine();
    }
}
