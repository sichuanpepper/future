package com.future.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 *
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 *
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase English letters ('a' to 'z') only.
 *
 * Analyze:
 * Solution 1:
 * Start it from simple, let's say we have a string
 * "abc", it generated 3 substring, "a", "b", "c"
 * "abcb", it generated 2 substring, "a", "bcb"
 * "abcbc", -> "a", "bcbc"
 *
 * For each character:
 *  - Not appeared before, generate a interval by itself.
 *  - Else, merge intervals and put the new interval back.
 *  Then stack applied
 *
 * Solution 2:
 * Each character is able to extend the current substring, the edge depends on the last position of this character.
 * So we need to the last position of each character.
 */
public class Problem763 {
    public List<Integer> partitionLabels(String S) {
        int[] lastPos = new int[26];
        for(int i = 0; i < S.length(); i++) {
            lastPos[S.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int last = 0, start = 0;
        for(int i = 0; i < S.length(); i++) {
            last = Math.max(last, lastPos[S.charAt(i) - 'a']);
            if(last == i) {
                res.add(last - start + 1);
                start = last + 1;
            }
        }
        return res;
    }

}
