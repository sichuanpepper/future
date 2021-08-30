package com.future.generic;

/**
 * https://leetcode.com/problems/jump-game-ii/
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */
public class JumpGame {
    /**
     * Thoughts:
     * It's easy to see it's a graph structure, and we need to find the shortest path in the graph, so BFS is applied.
     *          2
     *       3  -> 1
     *     4 <- 1
     *  If we take a closer look, we can see for each level, we care about the max reachable position.
     *  To identify level, we need to know the start index and end index in the array.
     *  - start = end + 1
     *  - end = reachable
     *
     *  init: start = 0, end = 0, reachable = 2, step = 1
     *        start = 1, end = 2, reachable = 4, step = 2
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int start = 0, end = 0, reachable = nums[0], step = 0;
        while(end < nums.length - 1) {
            for(int i = start; i <= end; i++) {
                reachable = Math.max(reachable, i + nums[i]);
            }
            start = end + 1;
            end = reachable;
            step++;
        }
        return step;
    }
}
