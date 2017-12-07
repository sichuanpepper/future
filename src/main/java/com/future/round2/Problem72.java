package com.future.round2;

/**
 * https://leetcode.com/problems/edit-distance/description/
 *
 * Created by someone on 11/16/17.
 */
public class Problem72 {
    /**
     * There are three operations to covert str1 to str2, insert, delete, modify.
     * for instance,
     * Insert, "abc" and "abcd", we need to insert into a character.
     * Delete, "abc" and "ab", we need to delete one character.
     * Modify, "abc" and "abd", we need to modify one character.
     * Let's use f(m, n) represent the edit distance of str1 in length m and str2 in length n.
     * if the character str.charAt(m) == str.charAt(n), nothing to do, just take it away, f(m, n) = f(m - 1, n 1);
     * else, there are three possibilities: (We are using case "abc" and "abd")
     * Insert a character in first string ("abcd", "abd"), so f(m, n) = f(m, n - 1) + 1
     * Delete a character in first string("ab", "abd"), so f(m, n) = f(m - 1, n) + 1
     * Modify a character in first string("abd", "abd"), so f(m, n) = f(m - 1, n - 1) + 1
     *
     * We pick up the minimum way.
     *
     * @param str1
     * @param str2
     * @return
     */
    public int minDistance(String str1, String str2) {
        if(str1 == null || str1.equals(str2)) return 0;
        if(str1.length() < 1) return str2.length();
        if(str2.length() < 1) return str1.length();

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for(int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        dp[0][0] = 0;
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
