package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/split-array-with-same-average/
 *
 * In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)
 *
 * Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.
 *
 * Example :
 * Input:
 * [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
 * Note:
 *
 * The length of A will be in the range [1, 30].
 * A[i] will be in the range of [0, 10000].
 *
 * Analyze:
 * A straightforward way is, try to find all subset in list B, then we know what we have rest in list C, we can compare the avg.
 */
public class Problem805 {
    public boolean splitArraySameAverage(int[] A) {
        int sum = 0;
        for(int a : A) {
            sum += a;
        }
        return helper(A, 0, 0, 0, sum);
    }

    private boolean helper(int[] A, int start, int sum, int cnt, int total) {
        if(start >= A.length) {
            if(cnt < 1 || cnt == A.length) {
                return false;
            }
            double avgB = (double)sum / cnt;
            double avgC = (double)(total - sum) / (A.length - cnt);
            return Double.compare(avgB, avgC) == 0;
        }

        for(int i = start; i < A.length; i++) {
            if(helper(A, i + 1, sum + A[i], cnt + 1, total)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Problem805 p = new Problem805();
        System.out.println(p.splitArraySameAverage(new int[]{1, 6, 1}));
    }
}
