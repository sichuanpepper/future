package com.future.round2;

/**
 * <url>https://leetcode.com/problems/wildcard-matching/description/</url>
 *
 * Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 *
 * Created by someone on 12/3/17.
 */
public class Problem44 {
    /**
     * Analyze:
     * Usually we can use DP to solved such kind of questions.
     * Let's use f(m, n) to represent the result of s(m) and p(n), so we can get:
     * f(m, n) =
     *           if p(n) == '?', f(m, n) = f(m - 1, n - 1).
     *           if p(n) == '*', f(m, n) = f(0, n - 1) || f(1, n - 1) || ... || f(m, n - 1)
     *           if p(n) == other_char, f(m, n) = f(m - 1, n - 1) && other_char == s(m)
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(p == null) return s == null; //both are null
        if(p.length() < 1) return p.equals(s); //both are empty
        //use equals
        if(p.equals("*") || p.equals(s)) return true; // p matches any.
        if(s == null || s.length() < 1) return false; // s is empty.


        //default by false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //init dp
        dp[0][0] = true; //s is empty and p is empty
        for(int i = 1; i < p.length() + 1; i++) {  //s is empty and p is leading with '*'
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }

        for(int i = 1; i < s.length() + 1; i++) {
            for(int j = 1; j < p.length() + 1; j++) {
                if(p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p.charAt(j - 1) == '*') {
                    int k = 0;
                    while (k <= i) {
                        if(dp[k][j - 1]) {
                            dp[i][j] = true;
                            break;
                        }
                        k++;
                    }
                } else {
                    dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
