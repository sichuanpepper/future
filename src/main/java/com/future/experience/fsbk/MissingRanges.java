package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.lintcode.com/problem/missing-ranges/
 */
public class MissingRanges {
    /*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if(nums == null || nums.length < 1) {
            res.add(toString(lower, upper));
            return res;
        }
        if(lower < nums[0]) {

            res.add(toString(lower, nums[0] - 1));
        }
        for(int i = 1; i < nums.length; i++) {
            //corner case, integer overflow
            long diffOfVal = (long)nums[i] - (long)nums[i - 1];
            if(diffOfVal > 1) {
                int missedLower = Math.max(nums[i - 1] + 1, lower);
                int missedUpper = Math.min(nums[i] - 1, upper);
                res.add(toString(missedLower, missedUpper));
            }
        }
        if(upper > nums[nums.length - 1]) {
            res.add(toString(nums[nums.length - 1] + 1, upper));
        }
        return res;
    }

    private String toString(int lower, int upper) {
        if(lower == upper) {
            return Integer.toString(lower);
        } else {
            return lower + "->" + upper;
        }
    }

    public static void main(String[] args) {
        MissingRanges p = new MissingRanges();
        List<String> res = p.findMissingRanges(new int[]{-2147483648,2147483647}, -2147483648, 2147483647);
        res.forEach(System.out::println);
    }
}
