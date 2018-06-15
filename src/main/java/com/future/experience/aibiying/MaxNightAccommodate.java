package com.future.experience.aibiying;

/**
 * Created by xingfeiy on 6/13/18.
 */
public class MaxNightAccommodate {
    public int max(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int f1 = nums[0]; // exclude max
        int f2 = Math.max(nums[0], nums[1]); // max so far
        for (int i = 2; i < n; i++) {
            int f = Math.max(f1 + nums[i], f2);
            f1 = f2;
            f2 = f;
        }
        return f2;
    }
}
