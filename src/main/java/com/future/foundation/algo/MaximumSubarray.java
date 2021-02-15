package com.future.foundation.algo;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumSubarray {
    /**
     * https://leetcode.com/problems/maximum-subarray/
     *
     * Analyze:
     * For any element in the array, we care about what the maximum result can be contributed by previous subarray.
     * So the question also is what's the maximum result can be contribute to next element.
     * If the current sum is positive, it's valuable to contribute to next, otherwise it's useless, likes contribute 0.
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0], maxSoFar = Math.max(nums[0], 0);
        for(int i = 1; i < nums.length; i++) {
            max = Math.max(maxSoFar + nums[i], max);
            maxSoFar = Math.max(maxSoFar + nums[i], 0);
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/maximum-product-subarray/
     *
     * Since it's product, two negative numbers product a positive number, so both maximum result and minimum result are
     * valuable to contribute to next.
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0], maxSoFar = nums[0], minSoFar = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int tmp = maxSoFar;
            maxSoFar = Math.max(Math.max(nums[i] * maxSoFar, nums[i] * minSoFar), nums[i]);
            minSoFar = Math.min(Math.min(tmp * nums[i], nums[i] * minSoFar), nums[i]);
            max = Math.max(max, maxSoFar);
        }
        return max;
    }
}
