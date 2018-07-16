package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xingfeiy on 7/5/18.
 */
public class HouseRobber {
    /**
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
     * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
     * it will automatically contact the police if two adjacent houses were broken into on the same night.

     Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
     money you can rob tonight without alerting the police.

     Example 1:

     Input: [1,2,3,1]
     Output: 4
     Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     Total amount you can rob = 1 + 3 = 4.
     Example 2:

     Input: [2,7,9,3,1]
     Output: 12
     Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
     Total amount you can rob = 2 + 9 + 1 = 12.
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        int preTwo = 0, preOne = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int max = Math.max(nums[i] + preTwo, preOne);
            preTwo = preOne;
            preOne = max;
        }
        return preOne;
    }


    public List<Integer> robWithResults(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        int preTwo = 0, preOne = nums[0];
        int[] path = new int[nums.length];
        for(int i = 1; i < nums.length; i++) {
            int max;
            if(nums[i] + preTwo > preOne) {
                path[i] = i - 2;
                max = nums[i] + preTwo;
            } else {
                path[i] = i - 1;
                max = preOne;
            }
            preTwo = preOne;
            preOne = max;
        }

        int i = path.length - 1;
        while (i >= 0) {
            if(path[i] == (i - 1)) {
                i--;
            } else {
                res.add(nums[i]);
                i = i - 2;
            }
        }

        return res;
    }

//    private int max = 0;
//    private List<Integer> finalRes = new ArrayList<>();
//    private void helper(int[] nums, int start, int curMax, List<Integer> res) {
//        if(start >= nums.length) {
//            if(curMax > max) finalRes = new ArrayList<>(res);
//            return;
//        }
//
//    }

    /**
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
     * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile,
     * adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were
     * broken into on the same night.

     Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
     money you can rob tonight without alerting the police.

     Example 1:

     Input: [2,3,2]
     Output: 3
     Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
     because they are adjacent houses.
     Example 2:

     Input: [1,2,3,1]
     Output: 4
     Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     Total amount you can rob = 1 + 3 = 4.
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        if(nums.length == 1) return nums[0];

        //rob first one, that means we can't rob last one
        int preTwo = 0, preOne = nums[0];
        for(int i = 1; i < nums.length - 1; i++) {
            int curMax = Math.max(nums[i] + preTwo, preOne);
            preTwo = preOne;
            preOne = curMax;
        }
        int max = preOne;

        preTwo = 0;
        preOne = nums[1];
        for(int i = 2; i < nums.length; i++) {
            int curMax = Math.max(nums[i] + preTwo, preOne);
            preTwo = preOne;
            preOne = curMax;
        }
        return Math.max(max, preOne);
    }

    public static void main(String[] args) {
        HouseRobber h = new HouseRobber();
        DisplayUtils.printList(h.robWithResults(new int[]{1,2,3,1}));
        DisplayUtils.printList(h.robWithResults(new int[]{2,7,9,3,1}));
    }
}
