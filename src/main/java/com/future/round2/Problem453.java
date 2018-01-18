package com.future.round2;

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements/description/
 *
 Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal,
 where a move is incrementing n - 1 elements by 1.

 Example:

 Input:
 [1,2,3]

 Output:
 3

 Explanation:
 Only three moves are needed (remember each move increments two elements):

 [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

 * Created by xingfeiy on 1/11/18.
 */
public class Problem453 {
    public int minMoves(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        for(int num : nums) min = Math.min(min, num);
        int res = 0;
        for(int num : nums) res += num - min;
        return res;
    }
}
