package com.future.foundation.algo;

import java.util.Arrays;

/**
 * Created by xingfeiy on 8/6/18.
 */
public class DPProblems {
    /**
     * https://leetcode.com/problems/coin-change/description/

     You are given coins of different denominations and a total amount of money amount. Write a function to compute the
     fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins,
     return -1.

     Example 1:

     Input: coins = [1, 2, 5], amount = 11
     Output: 3
     Explanation: 11 = 5 + 5 + 1
     Example 2:

     Input: coins = [2], amount = 3
     Output: -1
     Note:
     You may assume that you have an infinite number of each kind of coin.

     * Analyze:
     * It's a combination issue, back tracking can find all combinations, but this problem is required the fewest number.
     * And it's easy to observe that there are same sub-problems, so we can think about DP
     * How to describe the current status?
     *  - The coins we have
     *  - The target amount
     * f(coins, amount) = min{f(coins, amount - coin1), f(coins, amount - coin2), ...} + 1
     * Since we have an infinite number of each coin, that means we have same coin set for all sub problems.
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length < 1) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                if(i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     *
     * https://leetcode.com/problems/edit-distance/description/
     *
     Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

     You have the following 3 operations permitted on a word:

     Insert a character
     Delete a character
     Replace a character
     Example 1:

     Input: word1 = "horse", word2 = "ros"
     Output: 3
     Explanation:
     horse -> rorse (replace 'h' with 'r')
     rorse -> rose (remove 'r')
     rose -> ros (remove 'e')
     Example 2:

     Input: word1 = "intention", word2 = "execution"
     Output: 5
     Explanation:
     intention -> inention (remove 't')
     inention -> enention (replace 'i' with 'e')
     enention -> exention (replace 'n' with 'x')
     exention -> exection (replace 'n' with 'c')
     exection -> execution (insert 'u')
     *
     * Analyze:
     * The status depends on current position of first word and position of second word.
     * f(m, n) = f(m - 1, n - 1) modify
     *           f(m - 1, n) add
     *           f(m, n - 1) delete
     * Init:
     * if word1 is empty(m = 0), then dp[0][n] = n
     * if word2 is empty(n = 0), then dp[m][0] = m
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null || word1.equals(word2)) return 0;
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i <= word1.length(); i++) dp[i][0] = i;
        for(int i = 0; i <= word2.length(); i++) dp[0][i] = i;
        for(int i = 1; i <= word1.length(); i++) {
            for(int j = 1; j <= word2.length(); j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**
     * https://leetcode.com/problems/24-game/description/
     *
     * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated
     * through *, /, +, -, (, ) to get the value of 24.

     Example 1:
     Input: [4, 1, 8, 7]
     Output: True
     Explanation: (8-4) * (7-1) = 24
     Example 2:
     Input: [1, 2, 1, 2]
     Output: False
     Note:
     The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
     Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example,
     with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
     You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        if(nums == null || nums.length != 4) return false;
        boolean[] visited = new boolean[4];
        for(int i = 0; i < 4; i++) {
            visited[i]= true;
        }
        //todo
        return false;
    }

    private boolean judgePoint24Helper(int[] nums, boolean[] visited, int curVal, int taken) {
        if(taken == 4) return curVal == 24;
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
        }
        //todo
        return false;
    }

    /**
     * https://leetcode.com/problems/valid-parenthesis-string/description/
     *
     Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether
     this string is valid. We define the validity of a string by these rules:

     Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     Any right parenthesis ')' must have a corresponding left parenthesis '('.
     Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
     An empty string is also valid.
     Example 1:
     Input: "()"
     Output: True
     Example 2:
     Input: "(*)"
     Output: True
     Example 3:
     Input: "(*))"
     Output: True
     Note:
     The string size will be in the range [1, 100].

     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        return checkValidStringHelper(s, 0, 0);
    }

    private boolean checkValidStringHelper(String s, int cur, int count) {
        if(cur >= s.length()) return count == 0;
        if(count < 0) return false;
        if(s.charAt(cur) == '(') {
            return checkValidStringHelper(s, cur + 1, count + 1);
        } else if(s.charAt(cur) == ')') {
            return checkValidStringHelper(s, cur + 1, count - 1);
        }
        return checkValidStringHelper(s, cur + 1, count) || checkValidStringHelper(s, cur + 1, count + 1) ||
                checkValidStringHelper(s, cur + 1, count - 1);
    }

//    public boolean checkValidStringDP(String s) {}
}
