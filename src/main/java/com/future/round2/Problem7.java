package com.future.round2;

/**
 * https://leetcode.com/problems/reverse-integer/description/
 *
 * Reverse digits of an integer.

 Example1: x = 123, return 321
 Example2: x = -123, return -321

 click to show spoilers.

 Note:
 The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
 * Created by someone on 10/29/17.
 */
public class Problem7 {

    /**
     * Analyze:
     * 1. Assumed the input is sighed integer, what is it's unsigned integer?
     *    - there's no unsigned integer in Java.
     * 2. The input could be negative, can we process it as positive?
     *   - The mod of a negative is a negative or zero, and a negative plus a negative which always resulted a negative.
     *   - If there are some operations about product or divide, that's the different story.
     * 3. How to handle the overflow issue?
     *   - Use long as temporary result.
     * @param x
     * @return
     */
    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) ? 0 : (int)res;
    }
}
