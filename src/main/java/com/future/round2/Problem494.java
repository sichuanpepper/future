package com.future.round2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/target-sum/description/
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 Note:
 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.

 * Created by xingfeiy on 5/4/18.
 */
public class Problem494 {

    /**
     * dfs, beats 30%
     * @param nums
     * @param S
     * @return
     */
    public int dfs(int[] nums, int S) {
        if(nums == null || nums.length < 1) return 0;
        helper(nums, 0, S);
        return ways;
    }

    private int ways = 0;
    private void helper(int[] nums, int start, int S) {
        if(start >= nums.length) {
            if(S == 0) ways++;
            return;
        }

        helper(nums, start + 1, S + nums[start]);
        helper(nums, start + 1, S - nums[start]);
    }

    /**
     * f(n, s) = f(n - 1, s + nums[n]) + f(n - 1, s - nums[n]) where n is nth element in nums, and s is the sum.
     *
     * beats 56%
     *
     * @param nums
     * @param S
     * @return
     */
    public int dpSolution(int[] nums, int S) {
        if(nums == null || nums.length < 1) return 0;
        int sum = 0;
        for(int num : nums) sum += num;
        if(S > sum || S < -sum) return 0;
        //the total sum range could be -sum to sum
        int[][] dp = new int[nums.length + 1][2 * sum + 1];
        dp[0][sum] = 1;
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(j + nums[i - 1] < dp[0].length) dp[i][j] += dp[i - 1][j + nums[i - 1]];
                if(j - nums[i - 1] >= 0) dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][sum + S];
    }


    /**
     * BFS, TLE...
     * @param nums
     * @param S
     * @return
     */
    public int bfs(int[] nums, int S) {
        if(nums == null || nums.length < 1) return 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S);
        for(int i = 0; i < nums.length; i++) {
            int size = queue.size();
            for(int j = 0; j < size; j++) {
                int tmp = queue.poll();
                queue.offer(tmp + nums[i]);
                queue.offer(tmp - nums[i]);
            }
        }
        int res = 0;
        while(!queue.isEmpty()) res += queue.poll() == 0 ? 1 : 0;
        return res;
    }

    public static void main(String[] args) {
        Problem494 p = new Problem494();
        System.out.println(p.dpSolution(new int[]{2,20,24,38,44,21,45,48,30,48,14,9,21,10,46,46,12,48,12,38}, 48));

        System.out.println(p.bfs(new int[]{2,20,24,38,44,21,45,48,30,48,14,9,21,10,46,46,12,48,12,38}, 48));
        System.out.println(p.dfs(new int[]{2,20,24,38,44,21,45,48,30,48,14,9,21,10,46,46,12,48,12,38}, 48));
    }
}
