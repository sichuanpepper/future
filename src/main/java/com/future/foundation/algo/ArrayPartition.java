package com.future.foundation.algo;

import java.util.Arrays;

public class ArrayPartition {
    /**
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0) {
            return false;
        }

        return helper(nums, k, 0, 0,sum / k, new boolean[nums.length]);
    }

    private boolean helper(int[] nums, int k, int curIndex, int curSum, int target,boolean[] visited) {
        if(k < 1) {
            return true;
        }
        if(curSum == target) {
            return helper(nums, k - 1, 0, 0, target, visited);
        }

        for(int i = curIndex; i < nums.length; i++) {
            if(visited[i]) {
                continue;
            }
            if(curSum + nums[i] <= target) {
                visited[i] = true;
                if(helper(nums, k, curIndex + 1, curSum + nums[i], target, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/largest-sum-of-averages/
     * @param A
     * @param K
     * @return
     */
    public double largestSumOfAverages(int[] A, int K) {
        return 0.0;
    }

    /**
     * https://leetcode.com/problems/split-array-with-same-average/
     * @param A
     * @return
     */
    public boolean splitArraySameAverage(int[] A) {
        return true;
    }
}
