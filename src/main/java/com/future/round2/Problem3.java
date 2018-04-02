package com.future.round2;

/**
 * Given a string, find the length of the longest substring without repeating characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
 "pwke" is a subsequence and not a substring.

 * Created by someone on 10/10/17.
 *
 * [Tags] longest substring
 */
public class Problem3 {
    public static int lengthOfLongestSubstring(String s) {
        return solution1(s);
    }

    /**
     * Sliding window.
     * Two pointers, left and right, left init to 0 and right init to 1
     * if there's no repeating character between left and right, right move.
     * if there's repeating character between left and right, left move.
     *
     * @param s
     * @return
     */
    public static int solution1(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }

        int left = 0;
        int right = 1;
        int longest = 1;
        boolean[] existed = new boolean[256];
        existed[s.charAt(left)] = true;
        while (right < s.length()) {
            if(existed[s.charAt(right)]) {
                existed[s.charAt(left++)] = false;
            } else {
                longest = Math.max(longest, right - left + 1);
                existed[s.charAt(right++)] = true;
            }
        }
        return longest;
    }

    /**
     * 02/01/2018
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_v2(String s) {
        if(s == null || s.length() < 1) return 0;
        boolean[] existed = new boolean[256];
        int left = 0, right = 0, longest = 0;
        while(right < s.length()) {
            if(!existed[s.charAt(right)]) {
                existed[s.charAt(right++)] = true;
            } else {
                existed[s.charAt(left++)] = false;
            }
            longest = Math.max(longest, right - left);
        }
        return longest;
    }


    public static void main(String[] args) {
        System.out.println(solution1(""));
        System.out.println(solution1("abcabcbb"));
        System.out.println(solution1("bbbb"));
        System.out.println(solution1("pwwkew"));
    }
}
