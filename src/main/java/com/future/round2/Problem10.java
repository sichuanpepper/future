package com.future.round2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true

 --add by myself
 "aab", ".*b"

 * Created by someone on 10/11/17.
 */
public class Problem10 {
    public boolean isMatch(String s, String p) {
        //added one more size since the consideration of empty strings.
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true; // "" matches ""

        //init dp matrix.
        for(int i = 1; i < dp[0].length; i++) {
            if(p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1] || dp[0][i - 2];
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') { // abc vs abc or abc vs ab.
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p.charAt(j - 1) == '*'){
                    if(s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') { // abc vs abcd*
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        // abbb vs abbbb*, repeat b zero time dp[i][j] = dp[i][j - 2]
                        // abbb vs abb*, repeat b one time, dp[i][j] = dp[i - 1][j - 1]
                        // abbb vs ab*, repeat b more times, dp[i][j] = dp[i - 1][j]

                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    public static void main(String[] args) {
        Problem10 p = new Problem10();
        System.out.println(p.isMatch("", ""));
        System.out.println(p.isMatch("aa", "a"));
        System.out.println(p.isMatch("aa", "aa"));
        System.out.println(p.isMatch("aa", ".*"));
        System.out.println(p.isMatch("aa", "a*"));
        System.out.println(p.isMatch("aa", "a."));
        System.out.println(p.isMatch("aab", "c*a*b"));
        System.out.println(p.isMatch("aab", ".*b"));

        System.out.println("abc" == "abc");
        System.out.println(new String("abc") == "abc");
        System.out.println(new String("abc").equals("abc"));
        System.out.println(new String("a") == "a");
        System.out.println(Character.toString('a') == "a");
        System.out.println('a' == 'a');
        System.out.println("a".charAt(0) == 'a');
    }
}
