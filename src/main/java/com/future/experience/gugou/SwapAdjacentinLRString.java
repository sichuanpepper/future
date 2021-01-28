package com.future.experience.gugou;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/swap-adjacent-in-lr-string/
 *
 * BAD example!
 *
 *
 */
public class SwapAdjacentinLRString {
    public boolean canTransform(String start, String end) {
        if(start.equals(end)) {
            return true;
        }
        if(start.length() != end.length()) {
            return false;
        }

        int left = 0, right = 0;
        for(int i = 0; i < start.length(); i++) {
            char sChar = start.charAt(i);
            char eChar = end.charAt(i);
            if(sChar == 'X' && eChar == 'X') {
                continue;
            }
            if(eChar == 'L') {
                left++;
            }
            if(sChar == 'L') {
                if(right > 0 || --left < 0) {
                    return false;
                }
            }
            if(sChar == 'R') {
                right++;
            }
            if(eChar == 'R') {

                if(left > 0 || --right < 0) {
                    return false;
                }
            }
        }
        return left == 0 && right == 0;
    }
}
