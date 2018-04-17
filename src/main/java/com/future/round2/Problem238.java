package com.future.round2;

/**
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of
 all the elements of nums except nums[i].

 Solve it without division and in O(n).

 For example, given [1,2,3,4], return [24,12,8,6].

 Follow up:
 Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose
 of space complexity analysis.)

 * Created by xingfeiy on 4/9/18.
 */
public class Problem238 {
    /**
     * Analyze:
     * Should we care about integer overflow issue?
     *
     * For any element i in array, it's easy to know the res[i] = left product * right product.
     * So, we can compute the product in two pass.
     * first pass, from left to right, we can get the left product.
     * second pass, from right to left, we can get the right product.
     * then, problem solved.
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for(int i = 1; i < nums.length; i++) res[i] = nums[i] * res[i - 1];
        int rightProduct = 1;
        for(int i = res.length - 1; i >= 0; i--) {
            res[i] = ((i > 0) ? res[i - 1] : 1) * rightProduct;
            rightProduct *= nums[i];
        }
        return res;
    }
}
