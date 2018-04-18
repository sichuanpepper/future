package com.future.round2;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/description/
 *
 Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True
 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.
 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

 * Created by xingfeiy on 4/17/18.
 */
public class Problem680 {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left++) != s.charAt(right--)) return helper(s, left, right + 1) || helper(s, left - 1, right);
        }
        return true;
    }

    private boolean helper(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Problem680 p = new Problem680();
//        System.out.println(p.validPalindrome("aba"));
//        System.out.println(p.validPalindrome("ababb"));
        System.out.println(p.validPalindrome("enveorysiwkzbfngqeijeynzlfuivzsbjgwrpgcawikmvsbtmxhokubhrahzpougclcfzmmwklgxfyeovygfjwdyg"));
    }
}
