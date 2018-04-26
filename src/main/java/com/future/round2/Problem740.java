package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/delete-and-earn/description/
 Given an array nums of integers, you can perform operations on the array.

 In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal
 to nums[i] - 1 or nums[i] + 1.

 You start with 0 points. Return the maximum number of points you can earn by applying such operations.

 Example 1:
 Input: nums = [3, 4, 2]
 Output: 6
 Explanation:
 Delete 4 to earn 4 points, consequently 3 is also deleted.
 Then, delete 2 to earn 2 points. 6 total points are earned.
 Example 2:
 Input: nums = [2, 2, 3, 3, 3, 4]
 Output: 9
 Explanation:
 Delete 3 to earn 3 points, deleting both 2's and the 4.
 Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 9 total points are earned.
 Note:

 The length of nums is at most 20000.
 Each element nums[i] is an integer in the range [1, 10000].

 * Created by xingfeiy on 1/22/18.
 */
public class Problem740 {
    public int deleteAndEarn(int[] nums) {
        if(nums == null) return 0;
        Arrays.sort(nums);
        int[] preVal = new int[]{0, 0}; //pickup, don't pickup
        int p1 = 0;
        while (p1 < nums.length) {
            //compute the sum of same points
            int sumOfSame = nums[p1], p2 = p1 + 1;
            while (p2 < nums.length && nums[p2] == nums[p1]) sumOfSame += nums[p2++];
            if(p1 == 0 || nums[p1] - nums[p1 - 1] > 1) {
                int max = Math.max(preVal[0], preVal[1]);
                preVal[0] = sumOfSame + max;
                preVal[1] = max;
            } else {
                int max = Math.max(preVal[0], preVal[1]);
                preVal[0] = preVal[1] + sumOfSame;
                preVal[1] = max;
            }
            p1 = p2;
        }
        return Math.max(preVal[0], preVal[1]);
    }

    public static void main(String[] args) {}
}
