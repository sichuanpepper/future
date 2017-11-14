package com.future.round2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/description/
 * <p>
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * If there isn't one, return 0 instead.
 * <p>
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * <p>
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * <p>
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * <p>
 * Follow Up:
 * Can you do it in O(n) time?
 * Created by someone on 10/29/17.
 */
public class Problem325 {
    /**
     * Since the follow up question required do it in O(n) time, that means you can't sort array.
     * <p>
     * And there's an important characterize of sum of array, which is
     * sum(m) - sum(n) = num, then sum(n, m) = num where n is excluded and m is included.
     * ex, given array[1, 2, 3, 4], the sum array is[1, 3, 6, 10]
     * sum(2) - sum(1) = 3, which equals sum(1, 2) =  3
     * sum(3) - sum(-1) = 10, which equals sum(-1, 3) = 1 + 2 + 3 + 4
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        //key => sum, value => the index
        Map<Integer, Integer> map = new HashMap<>();
        //add a fake value in map to handle the subarray which started from first element. (the first element is excluded)
        map.put(0, -1);
        int curSum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            //find required
            if(map.containsKey(curSum - k)) {
                max = Math.max(max, i - map.get(curSum - k));
            }
            if(!map.containsKey(curSum)) map.put(curSum, i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
        System.out.println(maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1));
        System.out.println(maxSubArrayLen(new int[]{-2, -1, 2, 1}, 8));
    }
}
