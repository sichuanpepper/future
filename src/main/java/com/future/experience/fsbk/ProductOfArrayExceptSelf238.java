package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 */
public class ProductOfArrayExceptSelf238 {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int preProduct = 1;
        for(int i = 0; i< nums.length; i++) {
            res[i] = preProduct;
            preProduct *= nums[i];
        }

        preProduct = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            res[i] *= preProduct;
            preProduct *= nums[i];
        }

        return res;
    }
}
