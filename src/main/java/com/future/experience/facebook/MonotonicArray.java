package com.future.experience.facebook;

/**
 * Created by xingfeiy on 5/6/18.
 */
public class MonotonicArray {
    /**
     * Given an array, return the monotonic of this array.
     * 1 monotonic increasing
     * -1 monotonic decreasing
     * 0 others
     *
     * Notes: the array may contain duplicated
     *        if there's only one element, we consider it as monotonic increasing.
     *
     * @param nums
     * @return
     */
    public int monotonic(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        if(nums.length < 2) return 1;
        int p = 1;
        int mono = Integer.MAX_VALUE;
        while (p < nums.length) {
            if(nums[p] == nums[p - 1]) {
                p++;
                continue;
            } if(nums[p] > nums[p - 1]) {
                if(mono == -1) return 0;
                mono = 1;
            } else {
                if(mono == 1) return 0;
                mono = -1;
            }
            p++;
        }
        return nums[nums.length - 1] < nums[0] ? -1 : 1;
    }

    public static void main(String[] args) {
        MonotonicArray p = new MonotonicArray();
        System.out.println(p.monotonic(new int[]{1, 2, 3, 3, 4}));
        System.out.println(p.monotonic(new int[]{1}));
        System.out.println(p.monotonic(new int[]{1, 1, 1}));
        System.out.println(p.monotonic(new int[]{3, 3, 3, 2, 1}));
        System.out.println(p.monotonic(new int[]{3, 3, 3, 2, 3, 2, 2}));
    }
}
