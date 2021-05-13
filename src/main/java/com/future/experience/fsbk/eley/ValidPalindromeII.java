package com.future.experience.fsbk.eley;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 * Thoughts:
 * - If there's
 *
 *
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        return helper(s, 0, s.length() - 1, 1);
    }

    private boolean helper(String s, int left, int right, int limit) {
        if(left >= right) {
            return true;
        }
        if(s.charAt(left) == s.charAt(right)) {
            return helper(s, left + 1, right - 1, limit);
        } else {
            if(limit < 1) {
                return false;
            }

            return helper(s, left + 1, right, limit - 1) || helper(s, left, right - 1, limit - 1);
        }
    }
}
