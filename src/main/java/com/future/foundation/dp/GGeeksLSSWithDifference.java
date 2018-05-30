package com.future.foundation.dp;


import java.util.Arrays;

/**
 * Given an array of n integers. The problem is to find maximum length of the subsequence with difference
 * between adjacent elements as either 0 or 1.
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = {2, 5, 6, 3, 7, 6, 5, 8}
 * Output : 5
 * The subsequence is {5, 6, 7, 6, 5}.
 * <p>
 * Input : arr[] = {-2, -1, 5, -1, 4, 0, 3}
 * Output : 4
 * The subsequence is {-2, -1, -1, 0}.
 * <p>
 * Created by someone on 9/6/17.
 */
public class GGeeksLSSWithDifference {

    public int solution(int[] nums) {
        if(nums == null || nums.length < 1) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(Math.abs(nums[i] - nums[j]) == 1 || nums[i] == nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }

    /**
     * Find the longest increasing subsequence.
     * For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
     * @param nums
     * @return
     */
    public int recallLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        //Analysis:
        // f(1) = 1, LIS = {10}
        // f(2) = 2, LIS = {10, 22} <-
        // f(3) = 2, LIS = {10, 22}
        //..., f(n) = max{f(m) | nums[m] < nums[n] } + 1; 0 < m < n;
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new GGeeksLSSWithDifference().recallLIS(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
        System.out.println(new GGeeksLSSWithDifference().recallLIS(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80, 1}));
        System.out.println(new GGeeksLSSWithDifference().recallLIS(new int[]{3, 2, 1}));
        System.out.println(new GGeeksLSSWithDifference().recallLIS(new int[]{1,1,1,1,2}));
        System.out.println(new GGeeksLSSWithDifference().solution(new int[]{2, 5, 6, 3, 7, 6, 5, 8}));
        System.out.println(new GGeeksLSSWithDifference().solution(new int[]{-2, -1, 5, -1, 4, 0, 3}));
    }
}
