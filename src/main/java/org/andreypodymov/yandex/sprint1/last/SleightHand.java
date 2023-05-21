package org.andreypodymov.yandex.sprint1.last;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    run-report: 87563170
    contest: 22450. Sprint 1 final B
    author: arpodymov
 */

public class SleightHand {

    private static final int MAX_DIGIT = 9;
    private static final int PLAYERS_AMOUNT = 2;
    private static final int FIELD_SIZE = 4;

    public static int solve(char[][] field, int simultaneousPossible) {
        int[] amounts = new int[MAX_DIGIT]; // Indexes from 0 to 8 when digits goes from 1 to 9, so -1 required.
        for (int i = 0; i < FIELD_SIZE; ++i) {
            for (int j = 0; j < FIELD_SIZE; ++j) {
                char value = field[i][j];
                if (value != '.') {
                    amounts[Character.getNumericValue(value) - 1]++;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < MAX_DIGIT; i++) {
            if (amounts[i] !=0 && amounts[i] <= simultaneousPossible * PLAYERS_AMOUNT) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int simultaneousPossible = readInt(reader);
            System.out.println(solve(readCharArray(reader), simultaneousPossible));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static char[][] readCharArray(BufferedReader reader) throws IOException {
        char[][] result = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; ++i) {
            result[i] = reader.readLine().toCharArray();
        }
        return result;
    }
}
