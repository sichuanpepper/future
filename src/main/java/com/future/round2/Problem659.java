package com.future.round2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/split-array-into-consecutive-subsequences/
 *
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences
 * such that each subsequence consists of consecutive integers and has length at least 3.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 * Example 2:
 *
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * Example 3:
 *
 * Input: [1,2,3,4,4,5]
 * Output: False
 *
 * Analyze:
 * For each element, there are two options, either append it to the previous subsequence or start a new subsequence.
 * - To start a new subsequence, we have to check number + 1, and number + 2, if the new subsequence is possible, then number + 3 has a previous subsequence.
 */
public class Problem659 {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> possibleSubseq = new HashMap<>();
        for(int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        for(int num : nums) {
            if(counter.get(num) == 0) continue;

            if(possibleSubseq.containsKey(num)) { //if it's possible to append it to the previous subsequence.
                counter.put(num, counter.get(num) - 1);
                int possibleCnt = possibleSubseq.get(num) - 1;
                if(possibleCnt > 0) {
                    possibleSubseq.put(num, possibleCnt);
                } else {
                    possibleSubseq.remove(num);
                }
                possibleSubseq.put(num + 1, possibleSubseq.getOrDefault(num + 1, 0) + 1);
            } else if(counter.getOrDefault(num + 1, 0) > 0 && counter.getOrDefault(num + 2, 0) > 0) { // if it's possible to start a new sebsequence
                counter.put(num, counter.get(num) - 1);
                counter.put(num + 1, counter.get(num + 1) - 1);
                counter.put(num + 2, counter.get(num + 2) - 1);
                possibleSubseq.put(num + 3, possibleSubseq.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
