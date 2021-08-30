package com.future.experience.fsbk.eley;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-number/
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 *
 * Thoughts:
 * Since there's only one number missed, expected sum - actual sum = missing number
 *
 * If there are two missing number?
 *
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += (i + 1 - nums[i]);
        }
        return sum;
    }


    public List<Integer> missingMultNumber(int[] nums, int n) {
        int posOfZero = -1, posOfN = -1;
        for(int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if(index == n) {
                posOfN = i;
                continue;
            }
            nums[index] = -nums[index];
            if(nums[index] == 0) {
                posOfZero = index;
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0 || (nums[i] == 0 && posOfZero < 0)) {
                res.add(i);
            }
        }
        if(posOfN < 0) {
            res.add(nums.length);
        }
        return res;
    }

    public static void main(String[] args) {
        MissingNumber p = new MissingNumber();
//        DisplayUtils.printList(p.missingMultNumber(new ));
    }
}
