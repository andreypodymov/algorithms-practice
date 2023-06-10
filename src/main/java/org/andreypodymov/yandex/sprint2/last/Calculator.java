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
    run-report: 88084533
    contest: 22781. Sprint 2 final B
    author: arpodymov

    -- ПРИНЦИП РАБОТЫ --
    Обратная польская нотация с использованием стека. Алгоритм реализован как указано в задании.
    Known issues: все классы в одном файле (чисто для контеста), операции не хранят приоритет
    (не нужно для уже подготовленной польской записи, но могло бы пригодиться для перевода из обычной).

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

interface BinaryOperation {
    int apply(int x, int y);
}

class Addition implements BinaryOperation {
    @Override
    public int apply(int x, int y) {
        return x + y;
    }
}

class Subtraction implements BinaryOperation {
    @Override
    public int apply(int x, int y) {
        return x - y;
    }
}

class Multiplication implements BinaryOperation {
    @Override
    public int apply(int x, int y) {
        return x * y;
    }
}

class Division implements BinaryOperation {
    @Override
    public int apply(int x, int y) {
        return (int) Math.floor((double) x / y);
    }
}

public class Calculator {
    private static final Map<String, BinaryOperation> operations = Map.of(
            "+", new Addition(),
            "-", new Subtraction(),
            "*", new Multiplication(),
            "/", new Division()
    );

    private static boolean isOperation(String operand) {
        return operations.containsKey(operand);
    }

    private static int solve(List<String> operands) {
        Stack<String> stack = new Stack<>();
        for (String operand : operands) {
            if (!isOperation(operand)) {
                // Подразумеваем, что запись корректна и считаем, что раз не операция - значит операнд = число.
                stack.push(operand);
            }
            else {
                // Данный блок будет работать без исключений и NPE только для корректной исходной записи.
                int latest = Integer.parseInt(stack.pop());
                int previous = Integer.parseInt(stack.pop());
                int result = operations.get(operand).apply(previous, latest);
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