package org.andreypodymov.codewars;

import java.util.Arrays;

/**
    https://www.codewars.com/kata/are-they-the-same
    Given two arrays a and b write a function comp(a, b) (orcompSame(a, b)) that checks whether the two arrays have the "same" elements,
    with the same multiplicities. "Same" means, here, that the elements in b are the elements in a squared, regardless of the order.
 */

public class AreSame {

    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        int[] sortedA = Arrays.stream(a).map(x -> x*x).sorted().toArray();
        int[] sortedB = Arrays.stream(b).sorted().toArray();
        return Arrays.equals(sortedA, sortedB);
    }
}
