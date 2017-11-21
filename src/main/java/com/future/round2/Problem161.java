package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/one-edit-distance/description/
 *
 * Given two strings S and T, determine if they are both one edit distance apart.
 *
 * Created by someone on 11/16/17.
 */
public class Problem161 {

    /**
     * Analyze:
     * The problem is determine if it's one edit distance, one edit distance could be:
     * insert, delete, modify.
     * So we can traversal the characters in both s and t and comapare it, when we encounter the different character,
     * We can try to do one of three operation, and then compare the rest substring in both s and t.
     * Example: "abce" and "abde"
     * We will find character 'c' doesn't equal 'd', then
     * if s have same length with t, modify charcter 'c' to 'd' and then compare the rest part("e" and "e").
     * if they have different length, remove character from longer one, and then compare rest part.
     *
     * The difference length between s and t should be less than 2.
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        if(s == null || t == null || s.equals(t)) return false;
        if(Math.abs(s.length() - t.length()) > 1) return false;
        if(s.length() < 1) return t.length() == 1;
        if(t.length() < 1) return s.length() == 1;
        int minLength = Math.min(s.length(), t.length());
        for(int i = 0; i < minLength; i++) {
            if(s.charAt(i) != t.charAt(i)) {  // we encountered different character.
                //if they have same length, the rest substring after this different character should be same.
                if(s.length() == t.length()) return (i == s.length() - 1)
                        || (s.substring(i + 1, s.length())).equals(t.substring(i + 1, t.length()));
                else {
                    //if they have different length.
                    // if s is longer than t, the substring after character(i) in s should be same as substring(i, end) in t.
                    if(s.length() > t.length()) return  (s.substring(i + 1, s.length())).equals(t.substring(i, t.length()));
                    else return (s.substring(i, s.length())).equals(t.substring(i + 1, t.length()));
                }
            }
        }
        //since the for loop end with minimum length, so it's possible after for loop, there's one character left at the end of string.
        return Math.abs(s.length() - t.length()) == 1;
    }

    /**
     * Analyze:
     * One edit distance apart means one add, one delete or one modify
     * likes: "abc" and "ab" is one edit distance.
     *  "abc" and "acc" is one edit distance.
     *  So basically there are two situations, the given strings have same length, means one modify.
     *  Or, the given strings have different length, means one add or delete.
     *
     *  Let's practice the edit distance here first, and then back to the original problem.
     *  But this solution is too heavy.
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance2(String s, String t) {
        if(s == null || t == null || s.equals(t)) return false;
        if(s.length() < 1) return t.length() == 1;
        if(t.length() < 1) return s.length() == 1;

        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for(int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        dp[0][0] = 0;

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[s.length()][t.length()] ==  1;
    }

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
    public int editDistance(String str1, String str2) {
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


    public static void main(String[] args) {
        Problem161 p = new Problem161();

        System.out.println(p.isOneEditDistance("a", "ba"));
        System.out.println(p.editDistance("", ""));
        System.out.println(p.editDistance("abc", ""));
        System.out.println(p.editDistance("abc", "ab"));
        System.out.println(p.editDistance("abc", "abe"));
        System.out.println(p.editDistance("abc", "abcd"));
        System.out.println(p.editDistance("a", "ba"));
    }

}
