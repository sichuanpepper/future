package com.future.experience.facebook;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 5/11/18.
 */
public class Permutation {
    /**
     * Given a positive integer array, return all product possibilities
     * example:
     * nums = [2,3,4]
     * return [2, 3, 4, 6, 8, 12, 24], which includes all elements self and all possible products.
     * 2*3=6, 2*4=8, 3*4 =12, 2*3*4=24
     *
     * DFS
     * @param nums
     * @return
     */
    public List<Integer> products(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        helper(nums, 0, 1, res);
        return res;
    }

    private void helper(int[] nums, int start, int curVal, List<Integer> res) {
        if(start > 0) res.add(curVal);
        if(start >= nums.length) return;
        for(int i = start; i < nums.length; i++) {
            helper(nums, i + 1, curVal * nums[i], res);
        }
    }

    public static void main(String[] args) {
        Permutation p = new Permutation();
        DisplayUtils.printList(p.products(new int[]{2, 3, 4}));
        DisplayUtils.printList(p.products(new int[]{1}));
        DisplayUtils.printList(p.products(new int[]{1, 1, 1}));
        DisplayUtils.printList(p.products(new int[]{-1, 0, 1}));
    }
}
