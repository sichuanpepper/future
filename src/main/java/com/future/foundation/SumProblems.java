package com.future.foundation;

import com.future.utils.DisplayUtils;

import javax.xml.ws.Dispatch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingfeiy on 10/4/17.
 */
public class SumProblems {
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
      You may assume that each input would have exactly one solution, and you may not use the same element twice.
      Example:
      Given nums = [2, 7, 11, 15], target = 9,
      Because nums[0] + nums[1] = 2 + 7 = 9,
      return [0, 1].

     Analyze: 1. If the given array is sorted?
              2. Can we use same element multiple times?
     * @param nums
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        if(nums == null || nums.length < 1) return new int[2];

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if(nums[left] + nums[right] == target) {
                //is it possible that nums[left] + nums[right] > Integer.MAX_VALUE?
                return new int[]{nums[left], nums[right]};
            } else if(nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }

    public static int[] twoSum2(int[] nums, int target) {
        if(nums == null || nums.length < 1) return new int[2];

        //(key, value) => (Differentials, index)
        Map<Long, Integer> map = new HashMap<>();
        for(int i : nums) {
            long diff = target - i;
            if(map.containsKey(diff)) {
                return new int[]{nums[map.get(diff)], nums[i]};
            }
            map.put(diff, i);
        }
        return new int[2];
    }

    public static int[] threeSum(int[] nums, int target) {
        if(nums == null || nums.length < 1) return new int[3];

        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {
            int diff = target - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if(nums[left] + nums[right] == diff) {
                    return new int[]{nums[i], nums[left], nums[right]};
                } else if(nums[left] + nums[right] > diff) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return new int[3];
    }

    public static int[] threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) return new int[3];
        Arrays.sort(nums);
        int minDiff = target - nums[0] - nums[1] - nums[2];
        int[] res = new int[]{nums[0], nums[1], nums[2]};
        for(int i = 0; i < nums.length - 2; i++) {
            int diff = target - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int curDiff = Math.abs(diff - nums[left] - nums[right]);
                if(curDiff < minDiff) {
                    minDiff = curDiff;
                    res = new int[]{nums[i], nums[left], nums[right]};
                }
                if(curDiff == 0) {
                    return new int[]{nums[i], nums[left], nums[right]};
                } else if(curDiff > 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 7, 11, 15};
        int[] nums2 = new int[]{2, 7, 11, 15, -1};
        int[] nums3 = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2};
        int[] nums4 = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
        DisplayUtils.printArray(twoSum1(nums1, 9));
        DisplayUtils.printArray(twoSum1(nums1, 3));
        DisplayUtils.printArray(twoSum1(nums3, 9));
        DisplayUtils.printArray(threeSum(nums2, 8));
        DisplayUtils.printArray(threeSum(nums2, 29));
        DisplayUtils.printArray(threeSumClosest(nums2, 29));
        DisplayUtils.printArray(threeSumClosest(nums2, 8));
    }
}
