package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 * Created by someone on 10/10/17.
 */
public class Problem1 {
    public static int[] solution1(int[] nums, int target) {
        if(nums == null || nums.length < 1) {
            return new int[2];
        }
        //key is the difference and value is the index
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{nums[map.get(nums[i])], nums[i]};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return new int[2];
    }

    public static int[] solution2(int[] nums, int target) {
        if(nums == null || nums.length < 1) {
            return new int[2];
        }

        //sort first
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if(nums[left] + nums[right] == target) {
                return new int[]{nums[left], nums[right]};
            } else if(nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }


    public static void main(String[] args) {
        DisplayUtils.printArray(solution1(new int[0], 5));
        DisplayUtils.printArray(solution1(new int[]{2, 7, 11, 15}, 9));
        DisplayUtils.printArray(solution1(new int[]{2, 7, 11, 15}, 33));
        DisplayUtils.printArray(solution1(new int[]{-2, 7, 11, 0}, 7));

        System.out.println("=====================");

        DisplayUtils.printArray(solution2(new int[0], 5));
        DisplayUtils.printArray(solution2(new int[]{2, 7, 11, 15}, 9));
        DisplayUtils.printArray(solution2(new int[]{2, 7, 11, 15}, 33));
        DisplayUtils.printArray(solution2(new int[]{-2, 7, 11, 0}, 7));
    }
}
