package com.future.round2;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * Created by xingfeiy on 5/19/18.
 */
public class Problem416 {

    /**
     * DP
     * f(m, s) = f(m - 1, s - nums[m]) || f(m - 1, s)
     * @param nums
     * @return
     */
    public boolean canPartitionDP(int[] nums) {
        if(nums == null || nums.length < 1) return false;
        int sum = 0;
        for(int n : nums) sum += n;
        if(sum % 2 != 0) return false;
        sum = sum / 2;

        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        dp[0][0] = true;
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 1; j <= sum; j++) {
                if(j - nums[i - 1] >= 0) dp[i][j] = dp[i - 1][j - nums[i - 1]];
                dp[i][j] = dp[i][j] || dp[i - 1][j];
            }
        }
        return dp[nums.length][sum];
    }

    /**
     * DFS
     * TLE...
     * Time complexity: O(2^n)
     * @param nums
     * @return
     */
    public boolean canPartitionDFS(int[] nums) {
        if(nums == null || nums.length < 1) return false;
        int sum = 0;
        for(int n : nums) sum += n;
        if(sum % 2 != 0) return false;
        sum = sum / 2;
        return helper(nums, 0, 0, sum);
    }

    private boolean helper(int[] nums, int start, int cur, int sum) {
        if(cur == sum) return true;
        if(start >= nums.length) return false;
        for(int i = start; i < nums.length; i++) {
            if(helper(nums, i + 1, cur + nums[i], sum) || helper(nums, i + 1, cur, sum)) return true;
        }
        return false;
    }
}
