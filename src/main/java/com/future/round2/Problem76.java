package com.future.round2;

/**
 * https://leetcode.com/problems/minimum-window-substring/description/

 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".

 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

 * Created by xingfeiy on 3/9/18.
 */
public class Problem76 {
    public String minWindow(String s, String t) {
        if(s == null || t == null || s.length() < t.length()) return "";
        int left = 0, right = 0, start = 0, end = Integer.MAX_VALUE, count = t.length();
        int[] map = new int[256];
        for(char ch : t.toCharArray()) map[ch]++;

        while(right < s.length()) {
            if(map[s.charAt(right++)]-- > 0) count--;
            while(count == 0) {
                if(right - left < end - start) {
                    start = left;
                    end = right;
                }
                if(map[s.charAt(left++)]++ == 0) count++;
            }

        }
        return end == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }
}
