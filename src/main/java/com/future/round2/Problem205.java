package com.future.round2;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/isomorphic-strings/description/
 Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters.
 No two characters may map to the same character but a character may map to itself.

 Example 1:

 Input: s = "egg", t = "add"
 Output: true
 Example 2:

 Input: s = "foo", t = "bar"
 Output: false
 Example 3:

 Input: s = "paper", t = "title"
 Output: true
 Note:
 You may assume both s and t have the same length.
 * Created by xingfeiy on 6/18/18.
 */
public class Problem205 {
    /**
     * Analyze:
     * We don't have to map the characters, we just need to compare the position.
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null) return false;
        if(s.equals(t)) return true;
        int[] sPos = new int[256], tPos = new int[256];
        Arrays.fill(sPos, -1);
        Arrays.fill(tPos, -1);
        for(int i = 0; i < s.length(); i++) {
            if(sPos[s.charAt(i)] != tPos[t.charAt(i)]) return false;
            sPos[s.charAt(i)] = i;
            tPos[t.charAt(i)] = i;
        }
        return true;
    }
}
