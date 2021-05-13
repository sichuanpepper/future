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


    /**
     * The pre-sum version works for all cases, but in this case, the array contains 0 and 1 only.
     * When we slide window, the sum of window grows from 0 to S, we are able to know how many windows that sum to a value between 0 to S.
     * The question is find the number of sum S, if we subtract the number of [0, S - 1], then we got the number of sum S.
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSumV2(int[] A, int S) {
        return allSumTo(A, S) - allSumTo(A, S - 1);
    }

    private int allSumTo(int[] A, int S) {
        if(S < 0) {
            return 0;
        }

        int p1 = 0, p2 = 0, res = 0;
        while(p2 < A.length) {
            S -= A[p2];
            while(S < 0) {
                S += A[p1++];
            }
            res += (p2 - p1 + 1);  // for each qualified window with length l, there are (p2 - p1 + 1) sub array end with p2.
            p2++;
        }
        return res;
    }
}
