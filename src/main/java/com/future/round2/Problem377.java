package com.future.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xingfeiy on 5/23/18.
 */
public class Problem377 {
    public int combinationSum4(int[] nums, int target) {
        if(target == 0) return 1;
        if(target < 0) return 0;
        int res = 0;
        for(int num : nums) {
            res += combinationSum4(nums, target - num);
        }
        return res;
    }


    /**
     * Top down dp
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4DP1(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return dp1Heper(nums, target, dp);
    }

    private int dp1Heper(int[] nums, int target, int[] dp) {
        if(target == 0) return 1;
        if(target < 0) return 0;
        if(dp[target] > -1) return dp[target];
        int res = 0;
        for(int num : nums) {
            res += combinationSum4(nums, target - num);
        }
        dp[target] = res;
        return res;
    }

    /**
     * Bottom up
     * Overlapping sub-problems
     * Optimal structure
     * If we want to reduce 2d dp array to 1d array, consider the metric can be increased 1 each time as row
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4DP2(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];
        for(int i = 0; i < dp.length; i++) dp[i][0] = 1;
        for(int i = 1; i < dp.length; i++) { // numbers
            for(int j = 1; j < dp[0].length; j++) {  //target
                dp[i][j] = dp[i - 1][j] + ((j - nums[i - 1] >= 0) ? dp[i][j - nums[i - 1]] : 0);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    /**
     * The difference between combinationSum4DP3 and combinationSum4DP2.
     * - combinationSum4DP2 return unique combination.
     * - combinationSum4DP3 return unique permutation
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4DP3(int[] nums, int target) {
        int[][] dp = new int[target + 1][nums.length + 1];
        for(int i = 0; i < dp.length; i++) dp[i][0] = 1;
        for(int i = 1; i < dp.length; i++) { //target
            for(int j = 1; j < dp[0].length; j++) { //number
                dp[i][j] = dp[i][j - 1] + ((i - nums[j - 1] >= 0) ? dp[i - nums[j - 1]][j] : 0);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }


    public List<List<Integer>> combinationSumList(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1 || target < 0) return res;
        helper(nums, target, new ArrayList<>(), res);
        return res;
    }

    private void helper(int[] nums, int target, List<Integer> curRes, List<List<Integer>> res) {
        if(target < 0) return;
        if(target == 0) {
            res.add(new ArrayList<>(curRes));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            curRes.add(nums[i]);
            helper(nums, target - nums[i], curRes, res);
            curRes.remove(curRes.size() - 1);
        }
    }

    public static void main(String[] args) {
        Problem377 p = new Problem377();
        System.out.println(p.combinationSum4(new int[]{1, 2, 3}, 4));
        System.out.println(p.combinationSum4DP1(new int[]{1, 2, 3}, 4));
        System.out.println(p.combinationSum4DP2(new int[]{1, 2, 3}, 4));
        System.out.println(p.combinationSum4DP3(new int[]{1, 2, 3}, 4));
        p.combinationSumList(new int[]{1, 2, 3}, 4).stream().forEach(System.out::println);
    }
}
