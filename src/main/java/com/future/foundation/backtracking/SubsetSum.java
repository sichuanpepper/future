package com.future.foundation.backtracking;


import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by someone on 7/25/17.
 */
public class SubsetSum {
    /**
     * Likes we got the array 1, 2, 3, 4, and target 5
     * @param nums
     * @param target
     * @return
     */
    public void subsetSum(int[] nums, int target) {
        List<List<Integer>> res = helper(nums, 0, target, new ArrayList<>());
        for(List<Integer> l : res) {
            DisplayUtils.printList(l);
        }
    }

    private List<List<Integer>> helper(int[] nums, int start, int target, List<Integer> tracker) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = start; i < nums.length; i++) {
            target -= nums[i];
            tracker.add(nums[i]);
            if(target == 0) {
                res.add(new ArrayList<>(tracker));
            }
            res.addAll(helper(nums, i + 1, target, tracker));
            target += nums[i];
            tracker.remove(tracker.size() - 1);
        }
        return res;
    }




    public static void main(String[] args) {
        int[] nums = new int[]{3, 34, 4, 12, 5, 2};
//        int[] nums = new int[]{0, 0, 0, 0};
        new SubsetSum().subsetSum(nums, 9);
//        new SubsetSum().subsetSum(nums, 8);
    }
}
