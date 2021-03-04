package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 *
 * Analyze:
 * It's an issue to find the maximum consecutive ones, one common solution is sliding window.
 * We extend the window to the right if the right value is 1.
 * In this problem, we have a budget to flip 0 to 1.
 *
 */
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {
        int res = 0, left = 0, right = 0;
        while(right < A.length) {
            if(A[right] == 0) {
                if(K > 0) {
                    K--;
                } else {
                    while(left < right && A[left] != 0) left++;
                    left++;
                }
            }

            res = Math.max(res, right -left + 1);
            right++;
        }
        return res;
    }
}
