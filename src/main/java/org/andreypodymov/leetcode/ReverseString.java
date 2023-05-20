package org.andreypodymov.leetcode;

/**
 *
 *   https://leetcode.com/explore/learn/card/recursion-i/250/principle-of-recursion/1440/
 *   Write a function that reverses a string. The input string is given as an array of characters s.
 */

public class ReverseString {
    public static void reverseStringIterative(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2 ; i++) {
            char temp = s[i];
            s[i] = s[n - i - 1];
            s[n - i - 1] = temp;
        }
    }

    public static void reverseStringTwoPtr(char[] s) {
        int a_ptr = 0;
        int b_ptr = s.length - 1;

        while (a_ptr < b_ptr) {
            char temp = s[a_ptr];
            s[a_ptr] = s[b_ptr];
            s[b_ptr] = temp;
            a_ptr++;
            b_ptr--;
        }
    }

    public static void reverseStringRecursive(char[] s, int i, int j) {
        if (i >= j) {
            return;
        }
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        reverseStringRecursive(s, i+1, j-1);
    }
}