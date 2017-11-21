package com.future.round2;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.

 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * Created by xingfeiy on 11/20/17.
 */
public class Problem125 {
    /**
     * Analyze:
     * Just use two pointers, one start from left and one start from right, and skip non-alphanumeric.
     *
     * The question is simple, but there are some "Keng"
     * "considering only alphanumeric characters and ignoring cases."
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s == null || s.length() < 1) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if(!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if(!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else if(Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        return true;
    }
}
