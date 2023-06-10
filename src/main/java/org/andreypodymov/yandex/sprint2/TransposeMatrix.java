package org.andreypodymov.yandex.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TransposeMatrix {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int x = readInt(reader);
            int y = readInt(reader);
            int[][] matrix = readIntArray(reader, x, y);
            matrix = transpose(matrix, x, y);
            printMatrix(matrix, y, x);
        }
    }

    private static void printMatrix(int[][] matrix, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] transpose(int[][] matrix, int x, int y) {
        int[][] newMatrix = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        return newMatrix;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[][] readIntArray(BufferedReader reader, int x, int y) throws IOException {
        int[][] result = new int[x][y];
        for (int i = 0; i < x; ++i) {
            result[i] = readIntArray(reader, y);
        }
        return result;
    }

    private static int[] readIntArray(BufferedReader reader, int size) throws IOException {
        int[] result = new int[size];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; ++i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return result;
    }
}
