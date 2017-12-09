package com.future.round2;

/**
 * https://leetcode.com/problems/ugly-number-ii/description/
 *
 * Write a program to find the n-th ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

 Note that 1 is typically treated as an ugly number, and n does not exceed 1690.


 * Created by xingfeiy on 12/7/17.
 */
public class Problem264 {
    /**
     * Analyze:
     *
     * 1*2, 2*2, 3*2, 4*2, 5*2, 6*2, 8*2...
     * 1*3, 2*3, 3*3, 4*3,...
     * 1*5, 2*5, 3*5, 4*5...
     *
     * As above, we can know that one ugly number can generate 3 ugly numbers by times 2, 3, 5.
     * So we pick up the minimum one from the heads of 3 lines, and move the head to next in the picked line.
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if(n < 1) return 0;
        int index2 = 0, index3 = 0, index5 = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[index2] * 2, dp[index3] * 3), dp[index5] * 5);
            if(dp[i] == dp[index2] * 2) index2++;
            if(dp[i] == dp[index3] * 3) index3++;  //can't use if else since we have to duplicate it.
            if(dp[i] == dp[index5] * 5) index5++;
        }
        return dp[n - 1];
    }
}
