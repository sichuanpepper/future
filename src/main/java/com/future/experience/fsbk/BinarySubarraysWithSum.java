package com.future.experience.fsbk;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/binary-subarrays-with-sum/
 *
 */
public class BinarySubarraysWithSum {
    public int numSubarraysWithSum(int[] A, int S) {
        if(A == null || A.length < 1) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, res = 0;
        for(int n : A) {
            sum += n;
            res += map.getOrDefault(sum - S, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
