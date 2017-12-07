package com.future.round2;

/**
 * https://leetcode.com/problems/shortest-palindrome/description/
 *
 * Created by someone on 11/26/17.
 */
public class Problem214 {
    /**
     * Analyze:
     * The maximum length of shortest palindrome is 2 * s.length - 1;
     * 1. Get the reverse string of s, let's call it r
     * 2. Traverse the character in r from 0 to last.
     *  - Add the character in the front of s and end of r, compare r and s, if equals, found it.
     * But..., JDK doesn't provide reverse function.
     *
     * Alternative way:
     * 1. Since we are allowed add characters in front of s, so we can traverse character from end of string, and compare it
     *   from front, if same, turns out the character, otherwise, turns out the character in back forward.
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        //todo KMP
        return "";
    }
}
