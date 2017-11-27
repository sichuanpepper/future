package com.future.round2;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.

 * Created by someone on 11/26/17.
 */
public class Problem14 {
    /**
     * Analyze:
     * We can pick up any string in the array as base, and go through all characters one by one, check if the character
     * existed in other all strings or not.
     *
     * Time complexity: O(mn) where m is length of first string and n is length of array.
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length < 1) return "";
        int p = 0;
        while(p < strs[0].length()) {
            for(int i = 1; i < strs.length; i++) {
                //ATTENTION: check if the pointer is valid here.
                if(p >= strs[i].length() || strs[i].charAt(p) != strs[0].charAt(p)) return strs[0].substring(0, p);
            }
            p++;
        }
        return strs[0].substring(0, p);
    }
}
