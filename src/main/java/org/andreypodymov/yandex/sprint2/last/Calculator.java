package org.andreypodymov.yandex.sprint2.last;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/*
    run-report: 88084428
    contest: 22781. Sprint 2 final B
    author: arpodymov

    -- ПРИНЦИП РАБОТЫ --
    Обратная польская нотация с использованием стека. Алгоритм реализован как указано в задании.
    Known issues: интерфейс и имплементации и вообще все в одном файле (сделано для контеста),
    операции не хранят приоритет (не нужно для уже подготовленной польской записи, но могло бы пригодиться
    для перевода из обычной).

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    За один проход будут гарантровано обработаны все элементы исходной обратной польской записи. Это происходит
    благадоря проверке каждой подстроки, явлется ли новая подстрока операндом или числом на каждой итерации.
    Благодаря LIFO прицнипу стека операции будут выполнены в нужном порядке.

   -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
   Проход по массиву из N операндов - O(N)
   Проверка операнда - O(1)
   Добавление в стек - O(1)
   Извлечение из стека элемента - O(1)
   Выполнение бинарной операции - O(1)
   Итого: O(N)

   -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
   Стек будет наполняться от 1 до N элементов в процессе выполнения.
   Итого: O(N)
 */

public class Calculator {

    public interface BinaryOperation {
        int apply(int x, int y);
    }

    public static class Addition implements BinaryOperation {
        @Override
        public int apply(int x, int y) {
            return x + y;
        }
    }

    public static class Subtraction implements BinaryOperation {
        @Override
        public int apply(int x, int y) {
            return x - y;
        }
    }

    public static class Multiplication implements BinaryOperation {
        @Override
        public int apply(int x, int y) {
            return x * y;
        }
    }

    public static class Division implements BinaryOperation {
        @Override
        public int apply(int x, int y) {
            return (int) Math.floor((double) x / y);
        }
    }

    private final static Map<String, BinaryOperation> operationMap = Map.of(
            "+", new Addition(),
            "-", new Subtraction(),
            "*", new Multiplication(),
            "/", new Division()
    );

    private static boolean isOperation(String operand) {
        return operationMap.containsKey(operand);
    }

    private static int solve(List<String> operands) {
        Stack<String> stack = new Stack<>();
        for (String operand : operands) {
            if (!isOperation(operand)) {
                stack.push(operand);
            }
            else {
                int latest = Integer.parseInt(stack.pop());
                int previous = Integer.parseInt(stack.pop());
                int result = operationMap.get(operand).apply(previous, latest);
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(solve(readList(reader)));
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }
}
