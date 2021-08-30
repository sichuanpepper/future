package com.future.generic;

import java.util.Arrays;

/**
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 *
 * Input: nums = [4,2,3,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 * Thoughts:
 * We need to find triplets meet the conditions: a + b > c and a - b < c
 * The straightforward way is, sort array first, choose all a, b pairs and calculate how many c.
 * Since the array is sort in ascending order, a - b must be smaller than c, so we just need care about a + b > c
 * ex: [2, 2, 3, 4]
 * a   b   c
 * 2   2   3  Y
 * 2   2   4  N
 * 2   3   4  Y
 * 2   3   4  Y
 */
public class ValidTriangleNumber {
    /**
     * The brute force solution, run time is O(n^3)
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for(int a = 0; a < nums.length - 2; a++) {
            for(int b = a + 1; b < nums.length - 1; b++) {
                for(int c = b + 1; c < nums.length; c++) {
                    if(nums[a] + nums[b] <= nums[c]) {
                        break;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * One way to optimize is do the binary search for third for loop
     * @param nums
     * @return
     */
    public int triangleNumberV2(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for(int a = 0; a < nums.length - 2; a++) {
            for(int b = a + 1; b < nums.length - 1; b++) {
                int start = b + 1, end = nums.length - 1;
                while(start < end) {
                    int mid = start + (end - start) / 2;
                    if(nums[a] + nums[b] < nums[mid]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
                res += (nums[start] < nums[a] + nums[b]) ? start - b : start - b - 1;
            }
        }
        return res;
    }

    /**
     * Sort array by ascending order, we go through all number from largest to smallest.
     * for each number n, we need to find a window(l, r to represent left side and right side) on the left,
     * if l + r > n, then there are total r - l pairs
     * if l + r < n, there's no valid pair in the window, to make l + r bigger, we have to move l.
     * @param nums
     * @return
     */
    public int triangleNumberV3(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for(int i = nums.length - 1; i > 1; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if(nums[l] + nums[r] > nums[i]) {
                    res += (r - l);
                    //then find the next valid window
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }
}
