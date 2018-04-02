package com.future.round2;

/**
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/

 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 Example 1:
 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 Note:

 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.

 * Created by xingfeiy on 4/1/18.
 */
public class Problem698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum % k != 0) return false;
        sum = sum / k;
        return helper(nums, 0, new boolean[nums.length], 0, sum, k);
    }

    private boolean helper(int[] nums, int curIndex, boolean[] visited, int curSum, int sum, int k) {
        if(k == 1) return true;
        for(int i = curIndex; i < nums.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            if(curSum + nums[i] == sum) {
                if(helper(nums, 0, visited, 0, sum, k - 1)) return true;
            } else {
                if(helper(nums, i + 1, visited, curSum + nums[i], sum, k)) return true;
            }
            visited[i] = false;
        }
        return false;
    }
}
