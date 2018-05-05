package com.future.foundation.algo;

import com.future.round2.Problem1;

/**
 * Created by xingfeiy on 5/1/18.
 */
public class SubSetProblems {
    /**
     * Given a non-negative integer array, Find if there's a subset that sums to target.
     *
     * Follow up:
     * 1. If negative element is possible?
     * 2. Is there's any different between unique and duplicated cases of given array?
     * @param nums
     * @param target
     * @return
     */
    public boolean findSubset(int[] nums, int target) {
        return true;
    }

    /**
     * The straightforward way is recursive solution.
     * For any element i in array, there are two options, include or exclude.
     *  - include, the original problem becomes to subproblem, find (target - nums[i]) in rest elements.
     *  - exclude, the original problem becomes to subproblem, find target in rest elements.
     *
     * Then, what's the base cases:
     *  - If the target is 0, then returns true since for that subproblem, we just need provide an empty sub array.
     *  - If the target less than 0, returns false since there's no negative elements.
     *  - If given array is empty and target > 0, that means there's no subset can sum to target, return false;
     *
     *  Time complexity: O(2^n), since each element has two options, so the total could be 2 * 2 * 2 *...
     *  Space complexity: O(1)
     * @param nums
     * @param target
     * @return
     */
    private boolean findSubsetRecursive(int[] nums, int target) {
        return findSubsetRecursive(nums, 0, target);
    }

    private boolean findSubsetRecursive(int[] nums, int start, int target) {
        if(target == 0) return true;
        if(start >= nums.length || target < 0) return false;
        for(int i = start; i < nums.length; i++) {
            if(findSubsetRecursive(nums, i + 1, target - nums[i]) || findSubsetRecursive(nums, i + 1, target)) return true;
        }
        return false;
    }

    /**
     * Based on the recursive solution above, it's easy to come out the DP solution.
     * f(n, s) = f(n - 1, s) or f(n - 1, s - nums[n]), any element can be included or excluded.
     * so here we need a 2d matrix.
     *
     * Time complexity: O(m * n), where m is the length of array, and n is target.
     * Space complexity: O(m * n)
     * @param nums
     * @param target
     * @return
     */
    private boolean findSubsetDP(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        //init base cases, if target is 0, all subsets can make it.
        for(int i = 0; i <= nums.length; i++) dp[i][0] = true;

        for(int i = 1; i <= nums.length; i++) {
            for(int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; //excluded.
                if(j - nums[i - 1] >= 0) dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]]; //included.
            }
        }
        return dp[nums.length][target];
    }

    /**
     * The knapsack is a classic DP problem, it's actually a subset problem also.
     * The problem is find a subset that make a maximum sum with capacity limited.
     * - If each element in stones can be used at most one time
     *  ex [2, 1, 4, 10], capacity = 8, should return 7.
     *  f(c) = max{f(c-s1) + s1, f(c-s2) + s2...}
     * - If each element in stones can be used unlimited times.
     *
     * @param stones
     * @param capacity
     * @return
     */
    public int knapsackV1(int[] stones, int capacity) {
        int[] dp = new int[capacity + 1];
        for(int i = 1; i <= capacity; i++) {
            for(int s : stones) {
                if(i - s >= 0) dp[i] = Math.max(dp[i], dp[i - s] + s);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        SubSetProblems p = new SubSetProblems();
        System.out.println(p.findSubsetRecursive(new int[0], 3)); //false
        System.out.println(p.findSubsetRecursive(new int[]{5, 2, 8, 1}, 3)); //true
        System.out.println(p.findSubsetRecursive(new int[]{5, 2, 8, 1}, 4)); //false

        System.out.println(p.findSubsetDP(new int[0], 3)); //false
        System.out.println(p.findSubsetDP(new int[]{5, 2, 8, 1}, 3)); //true
        System.out.println(p.findSubsetDP(new int[]{5, 2, 8, 1}, 4)); //false

        System.out.println(p.knapsackV1(new int[]{5, 2, 8, 1}, 4));  //3
        System.out.println(p.knapsackV1(new int[]{5, 5, 8, 8}, 4)); //0
    }
}
