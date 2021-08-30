package com.future.generic;

import com.future.utils.DisplayUtils;

public class MaxSumOfSubarray {
    /**
     * Given an integer array, find the subarray with maximum sum.
     * [10, 20, -30, 20] -> 30
     * [10, 20, -30, 50] -> 50
     * [10, 20, -20, 30] -> 40
     *
     * Thoughts:
     * Go through numbers in the array, use two pointers to represent a window, and accumulate the numbers in the window.
     * - if current sum is positive, it's possible to find larger sum by extending window
     * - if current sum is non-positive, the current window is useless since it makes next window smaller.
     * input: [10, 20, -40, 20]
     * cur  :  10, 30, -10, 20
     * max  :  10, 30, 30,  30
     * @param array
     * @return
     */
    public static int findMaxSum(int[] array) {
        int cur = 0, max = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++) {
            cur += array[i];
            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return max;
    }

    /**
     * The followup question, return the range instead of max value.
     *
     * Thoughts:
     * Compare to previous question, we need an additional info, the start position of current window.
     * @param array
     * @return
     */
    public static int[] findMaxSumRange(int[] array) {
        int cur = 0, max = Integer.MIN_VALUE, start = -1;
        int[] res = new int[2];
        for(int i = 0; i < array.length; i++) {
            if(start == -1) {
                start = i;
            }
            cur += array[i];
            if(cur > max) {
                max = cur;
                res = new int[]{start, i};
            }
            if(cur < 0) {
                start = -1;
                cur = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        DisplayUtils.printArray(findMaxSumRange(new int[]{10, 20, 30}));
        DisplayUtils.printArray(findMaxSumRange(new int[]{10, 20, -20, 30}));
        DisplayUtils.printArray(findMaxSumRange(new int[]{10, 20, -30, 50}));
        DisplayUtils.printArray(findMaxSumRange(new int[]{10, 20, -40, 50}));
        DisplayUtils.printArray(findMaxSumRange(new int[]{-10, 10, 20, -40, 50}));
    }

}
