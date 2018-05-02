package com.future.experience.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/description/
 *
 Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 Find all unique triplets in the array which gives the sum of zero.

 * Created by xingfeiy on 4/19/18.
 */
public class ThreeSum {

    /**
     * Analyze:
     *  - Is it sorted array?
     *  - does it contains any duplicated?
     *
     * Sort this array, for any element nums[i], find if there are two elements after i which sum to -nums[i]
     * consider the duplicated case.
     *
     * Two sum problem, for sorted array, we can use two pointer, one start from left and one start from right.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if(nums[left] + nums[right] == -nums[i]) {
                    res.add(Arrays.asList(new Integer[]{nums[i], nums[left++], nums[right--]}));
                    while(left < nums.length && nums[left] == nums[left - 1]) left++;
                    while(right > left && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] > -nums[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
