package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/jump-game-ii/description/
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

 Note:
 You can assume that you can always reach the last index.
 *
 * Created by someone on 12/5/17.
 */
public class Problem45 {

    /**
     * Analyze:
     * Still, we can consider this problem as a BFS problem, but just don't use DP or Dijkstra.
     * Let's take [2, 3, 1, 1, 4] as example, start from first element 2.
     * for 2, it's the first level, the farthest element can be reached in this level is A[2]=1
     * next, let process level 2, level 2 start from A[1] and end to the farthest position A[2], and same thing, the farthest
     * position can be reached in this level is A[1 + 3] = A[4], that's the end.
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int level = 0, start = 1, preMax = nums[0];
        while (start < nums.length) {
            level++; //go to the next level
            if(preMax >= nums.length - 1) return level; //the last one will be this level.
            //find the next max
            int curMax = 0;
            for(int i = start; i <= preMax; i++) {
                curMax = Math.max(curMax, nums[i] + i);
            }
            start = preMax + 1;
            preMax = curMax;
        }
        return level;
    }

    public static void main(String[] args) {
        Problem45 p = new Problem45();
        System.out.println(p.jump(new int[]{1, 1, 1, 1}));
    }

    /**
     * Analyze:
     * Actually, we don't have to use DP to solve this issue even through this problem has DP's features, it's too heavy here.
     * We can consider this problem as a BFS problem, let's take [2, 3, 1, 1, 4] as example.
     * 2 can reach 3 and 1, and 3 can reach 1, 1, 4..., it likes a graph, we need to calculate the shortest path from 2 to 4.
     * Sounds like Dijkstra..., it's also too heavy here, but we can try to solve it by Dijkstra to recall that algorithm.
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        // I don't implement it here, since you will find it's similar with the DP solution.
        return 0;
    }

    /**
     * Analyze:
     *
     * Has optimal substructure and overlapping problem, so maybe we can use DP to solve this issue.
     *
     * Let's use f(m) to represent the minimum jumps to reach position m.
     * f(m) = min(f(m - k1), f(m - k2),...) + 1 where k1 and k2 are the position can jump to m.
     * @param nums
     * @return
     */
    public int FAILED(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        List<List<Integer>> list = new ArrayList<>();
        for(int i  = 0; i < nums.length; i++) list.add(new ArrayList<>());
        for(int i = 0; i < nums.length - 1; i++) {
            int steps = nums[i];
            while (steps > 0) {
                if(i + steps >= nums.length) list.get(list.size() - 1).add(i);
                else list.get(i + steps).add(i);
                steps--;
            }
        }

        int[] dp = new int[nums.length];
        dp[0] = 0;
        for(int i = 1; i < nums.length; i++) {
            int min = Integer.MAX_VALUE;
            for(int j : list.get(i)) {
                min = Math.min(min, dp[j]);
            }
            dp[i] = min + 1;
        }
        return dp[dp.length - 1];
    }
}
