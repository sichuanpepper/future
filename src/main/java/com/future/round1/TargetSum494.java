package com.future.round1;

import com.future.utils.DisplayUtils;

/**
 * Created by someone on 5/31/17.
 */
public class TargetSum494 {
    public static int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length < 1) {
            return 0;
        }

        int[][] results = new int[nums.length + 1][ S + 1];
        results[0][0] = 1;
        for(int i = 1; i < nums.length + 1; i++) {
            for(int j = 0; j < S + 1; j++) {
                if(j - nums[i - 1] >= 0) {
                    results[i][j] += results[i - 1][j - nums[i - 1]];
                }

                if(j + nums[i - 1] <= S) {
                    results[i][j] += results[i - 1][j + nums[i - 1]];
                }
            }
        }

        DisplayUtils.printTwoDimensionsArray(results);
        return results[nums.length][S];
    }

    public static int dfs(int[] nums, int S) {
        return dfs(nums, S, 0, 0);
    }

    private static int dfs(int[] nums, int S, int index, int curSum) {
        if(index >= nums.length) {
            return (curSum == S) ? 1 : 0;
        }

        int count = 0;
        int tmp = curSum + nums[index];
        count += dfs(nums, S, index + 1, tmp);

        curSum = curSum - nums[index];
        count += dfs(nums, S, index + 1, curSum);
        return count;
    }


    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(dfs(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
