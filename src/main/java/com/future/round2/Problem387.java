package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/description/

 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 Note: You may assume the string contain only lowercase letters.

 * Created by xingfeiy on 1/29/18.
 */
public class Problem387 {

    public int firstUniqChar(String s) {
        if(s == null || s.length() < 1) return -1;
        int[] array = new int[256];
        for(char ch : s.toCharArray()) array[ch - '0']++;
        for(int i = 0; i < s.length(); i++) {
            if(array[s.charAt(i) - '0'] == 1) return i;
        }
        return -1;
    }

    public int onePass(String s) {
        if(s == null || s.length() < 1) return -1;
        int[] array = new int[256];
        for(int i = 0; i < s.length(); i++) {
            if(array[s.charAt(i) - '0'] > 0) {
                array[s.charAt(i) - '0'] = -1;
            } else if(array[s.charAt(i) - '0'] == 0) {
                array[s.charAt(i) - '0'] = i + 1;
            }
        }
        int first = Integer.MAX_VALUE;
        for(int i : array) {
            if(i > 0) first = Math.min(first, i);
        }
        return first == Integer.MAX_VALUE ? -1 : first - 1;
    }
}
