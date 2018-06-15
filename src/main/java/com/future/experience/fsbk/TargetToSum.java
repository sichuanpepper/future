package com.future.experience.fsbk;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xingfeiy on 5/14/18.
 */
public class TargetToSum {
    /**
     * Given a unsorted array, and an integer target, find if there's a subarray sum to target
     * Analyze:
     * Presum? sum[m] - sum[n] = sum[n + 1, m]
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean subArraySum(int[] nums, int target) {
        if(nums == null || nums.length < 1) return false;
        Set<Integer> sums = new HashSet<>();
        sums.add(0);
        for(int i = 0; i < nums.length; i++) {
            nums[i] += (i > 0) ? nums[i - 1] : 0;
            if(sums.contains(nums[i] - target)) return true;
            sums.add(nums[i]);
        }
        return false;
    }


    public static void main(String[] args) {
        TargetToSum p = new TargetToSum();
        System.out.println(p.subArraySum(new int[]{1, 2, 3, 2, 1}, 0));  //false
        System.out.println(p.subArraySum(new int[]{1, 2, 3, 2, 1}, 7)); //true
        System.out.println(p.subArraySum(new int[]{1, 2, 3, 2, 1}, 17)); //false
        System.out.println(p.subArraySum(new int[]{1, 2, 7, 2, 1}, 7)); //true
        System.out.println(p.subArraySum(new int[]{7, 2, 1, 3, 4}, 17));//true

        System.out.println(p.subArraySum(new int[]{3, -2, 7, 1}, 6));//true
        System.out.println(p.subArraySum(new int[]{-3, -2, -7, 1}, 6));//false
        System.out.println(p.subArraySum(new int[]{-3, -2, -7, 1}, -8));//true
    }
}
