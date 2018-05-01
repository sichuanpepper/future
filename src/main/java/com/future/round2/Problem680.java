package com.future.round2;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/description/
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

 * Created by xingfeiy on 4/24/18.
 */
public class Problem680 {
    public boolean validPalindrome(String s) {
        if(s == null || s.length() < 2) return true;
        return helper(s, 0, s.length() - 1, 1);
    }

    private boolean helper(String s, int left, int right, int delete) {
        while (left < right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if(delete == 0) return false;
                return helper(s, left + 1, right, delete - 1) || helper(s, left, right - 1, delete - 1);
            }
        }
        return true;
    }

    public boolean validPalindromev2(String s) {
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
