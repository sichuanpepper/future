package com.future.round1;

/**
 * Created by someone on 5/30/17.
 */
public class MaxProduct152 {
    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int maxHere = nums[0];
        int minHere = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int tmpMaxHere = maxHere;
            maxHere = Math.max(nums[i] * maxHere, nums[i] * minHere);
            maxHere = Math.max(maxHere, nums[i]);
            minHere = Math.min(nums[i] * tmpMaxHere, nums[i] * minHere);
            minHere = Math.min(minHere, nums[i]);
            max = Math.max(maxHere, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-4, -3, -2}));
    }
}
