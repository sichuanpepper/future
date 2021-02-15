package com.future.round2;

/**
 * https://leetcode.com/problems/longest-mountain-in-array/
 *
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 *
 * Input: arr = [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 104
 *
 */
public class Problem845 {
    /**
     * The problem actually is find the peak and valley, for each start number, we are trying to find its peak and valley.
     *
     * Questions to be clarify: if there's only going up or going down, does it count mountain? likes 1, 2, 3 or 3, 2, 1?
     * @param arr
     * @return
     */
    public int longestMountain(int[] arr) {
        int start = 0, res = 0;
        while(start < arr.length) {
            int peak = findPeak(arr, start);
            if(peak == start) {
                start = peak + 1;
                continue;
            }

            int valley = findValley(arr, peak);
            if(valley == peak) {
                start = valley + 1;
                continue;
            }

            res = Math.max(valley - start + 1, res);
            start = valley;
        }
        return res;
    }

    private int findPeak(int[] arr, int start) {
        int pos = start + 1;
        while(pos < arr.length && arr[pos] > arr[pos - 1]) {
            pos++;
        }
        return pos - 1;
    }

    private int findValley(int[] arr, int start) {
        int pos = start + 1;
        while(pos < arr.length && arr[pos] < arr[pos - 1]) {
            pos++;
        }
        return pos - 1;
    }
}
