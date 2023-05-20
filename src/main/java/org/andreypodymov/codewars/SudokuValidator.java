package org.andreypodymov.codewars;

import java.util.HashSet;
import java.util.Set;

/* https://www.codewars.com/kata/529bf0e9bdf7657179000008
Sudoku Background
Sudoku is a game played on a 9x9 grid. The goal of the game is to fill all cells of the grid with digits from 1 to 9, so that each column, each row, and each of the nine 3x3 sub-grids (also known as blocks) contain all of the digits from 1 to 9.
(More info at: http://en.wikipedia.org/wiki/Sudoku)

Sudoku Solution Validator
Write a function validSolution/ValidateSolution/valid_solution() that accepts a 2D array representing a Sudoku board, and returns true if it is a valid solution, or false otherwise. The cells of the sudoku board may also contain 0's, which will represent empty cells. Boards containing one or more zeroes are considered to be invalid solutions.

The board is always 9 cells by 9 cells, and every cell only contains integers from 0 to 9.
 */
public class SudokuValidator {

    public static boolean check(int[][] sudoku) {
        return checkColumnsAndRows(sudoku, 9) && checkBlocks(sudoku, 9 ,3);
    }

    protected static boolean checkColumnsAndRows(int[][] sudoku, int n) {
        Set<Integer> columnSet = new HashSet<Integer>();
        Set<Integer> rowSet = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isDigitInRange(sudoku[i][j], n) || !isDigitInRange(sudoku[j][i], n)) {
                    return false;
                }
                columnSet.add(sudoku[i][j]);
                rowSet.add(sudoku[j][i]);
            }
            if (columnSet.size() != n || rowSet.size() != n) {
                return false;
            }
            columnSet.clear();
            rowSet.clear();
        }
        return true;
    }

    protected static boolean checkBlocks(int[][] sudoku, int n, int blockSize) {
        Set<Integer> blockSet = new HashSet<Integer>();

        for (int y = 0; y < n; y += blockSize) {
            for (int x = 0; x < n; x += blockSize) {
                for (int i = x; i < x + blockSize; i++) {
                    for (int j = y; j < y + blockSize; j++) {
                        if (!isDigitInRange(sudoku[i][j], n)) {
                            return false;
                        }
                        blockSet.add(sudoku[i][j]);
                    }
                }
                if (blockSet.size() != n) {
                    return false;
                }
                blockSet.clear();
            }
        }
        return true;
    }

    protected static boolean isDigitInRange(int elem, int n) {
        return elem >= 1 && elem <= n;
    }
}
