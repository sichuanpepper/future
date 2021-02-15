package com.future.experience.linying;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 *
 *
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 *
 */
public class LongestPalindromicSubsequence {
    /**
     * dp[i][j] = dp[i + 1][j - 1] + 2, if char(i) == char(j)
     *          = max(dp[i + 1][j], dp[i][j - 1])
     * i depends on i + 1, so we have to calculate i from larger to smaller
     * j depends on j - 1, so we have to calculate j from smaller to larger
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        for(int start = s.length() - 1; start >= 0; start--) {
            for(int end = start + 1; end < s.length(); end++) {
                if(s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                } else {
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
