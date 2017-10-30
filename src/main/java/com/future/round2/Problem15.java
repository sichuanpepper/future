package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.

 Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 * Created by someone on 10/11/17.
 */
public class Problem15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) return res;

        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if(nums[left] + nums[right] == -nums[i]) {
                    res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    while (left < nums.length - 1 && nums[left] == nums[left - 1]) left++;
                    while (right > i + 1 && nums[right] == nums[right + 1]) right--;
                } else if(nums[left] + nums[right] < -nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        for(List<Integer> list : threeSum(new int[]{-1, 0, 1, 2, -1, -4})) {
            DisplayUtils.printList(list);
        }
    }

}
