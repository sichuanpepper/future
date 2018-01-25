package com.future.round2;

import java.util.Arrays;

/**
 https://leetcode.com/problems/perfect-squares/description/

 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 * Created by xingfeiy on 1/23/18.
 */
public class Problem279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++) {
            if(i * i < dp.length) dp[i * i] = 1;
            int k = 1;
            while(i - k * k >= 0) {
                dp[i] = Math.min(dp[i], dp[i - k * k] + 1);
                k++;
            }
        }
        return dp[n];
    }
}
