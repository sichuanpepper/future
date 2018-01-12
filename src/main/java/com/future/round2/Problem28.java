package com.future.round2;

/**
 * https://leetcode.com/problems/implement-strstr/description/
 *
 Implement strStr().

 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2
 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1

 * Created by someone on 11/26/17.
 */
public class Problem28 {
    /**
     * Analyze:
     * Two pointers,
     * The first pointer always points the character in haystack which equals the first character in needle.
     * The second pointer is keep traversing the following equaled characters until:
     * 1. All characters in needle have been traversed, return first pointer.
     * 2. All characters in haystack have been traversed, return -1.
     * 3. Found the different character, reset the whole search.
     *  - Problem here is how to reset the whole search? example:
     *      "aaac", "aac", we found the character in position 3 are different, second pointer points index 2. So we have
     *      to restart the search from the next of first pointer...
     *
     * Actually, we can simplify the logic here, use two loops.
     * first loop is traversing the character in haystack.
     * second loop is traversing the character in needle to find the matches.
     *  - in the second loop,
     *      - Boundary check, if the index is beyond the haystack's boundary, return -1.
     *      - Found it, return the index in first loop.
     *
     * Test cases:
     * Given "" and "", return 0;
     * Given "" and "a" return -1;
     * Given "a" and "" return 0;
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null) return -1;
        if(haystack.equals(needle) || needle.length() < 1) return 0;

        for(int i = 0; i < haystack.length(); i++) {
            for(int j = 0; j < needle.length(); j++) {
                //boundary check
                if(i + j >= haystack.length()) return -1;
                if(haystack.charAt(i + j) != needle.charAt(j)) break;
                if(j == needle.length() - 1) return i;
            }
        }
        return -1;
    }
}
