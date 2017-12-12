package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/description/
 *
 *
 Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example,
 Given n = 3, there are a total of 5 unique BST's.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3
 * Created by xingfeiy on 12/10/17.
 */
public class Problem96 {
    /**
     * Analyze:
     * - All numbers can be the head of BSTs.
     * - For each number, the smaller numbers go to the left and larger numbers go to the right.
     * So, f(n) = f(n - 1) * f(0) + f(n - 2) * f(1) + f(n - 3) * f(2)... where f(0) = 1
     *  f(0) = 1
     *  f(1) = 1
     *  f(2) = f(1) * f(0) + f(0) * f(1) = 2 * f(0) * f(1)
     *  f(3) = f(2) * f(0) + f(1) * f(1) + f(0) * f(2) = 2 * f(0) * f(2) + f(1) * f(1)
     *  f(4) = f(3) * f(0) + f(2) * f(1) + f(1) * f(2) + f(0) * f(3)  = 2 * f(0) * f(3) + 2 * f(1) * f(2)
     *  f(5) = f(4) * f(0) + f(3) * f(1) + f(2) * f(2) + f(3) * f(1) + f(0) * f(4)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if(n < 2) return 1;
        int res = 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <=n; i++) {
            int start = 0, end = i - 1;
            while (end >= 0) {
                dp[i] += dp[start++] * dp[end--];
            }
        }
        return dp[n];
    }

}
