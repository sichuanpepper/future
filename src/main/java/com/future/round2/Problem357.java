package com.future.round2;

/**
 * https://leetcode.com/problems/count-numbers-with-unique-digits/description/
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

 Example:
 Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100,
 excluding [11,22,33,44,55,66,77,88,99])
 * Created by xingfeiy on 1/24/18.
 */
public class Problem357 {
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        int res = 10;
        int availableNums = 9;
        int uniqueNums = 9;
        for(int i = 2; i <= n && availableNums > 0; i++) {
            uniqueNums *= availableNums--;
            res += uniqueNums;
        }
        return res;
    }
}
