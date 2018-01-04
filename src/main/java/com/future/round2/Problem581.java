package com.future.round2;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/
 *
 Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 then the whole array will be sorted in ascending order, too.

 You need to find the shortest such subarray and output its length.

 Example 1:
 Input: [2, 6, 4, 8, 10, 9, 15]
 Output: 5
 Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 Note:
 Then length of the input array is in range [1, 10,000].
 The input array may contain duplicates, so ascending order here means <=.

 * Created by xingfeiy on 1/3/18.
 */
public class Problem581 {
    /**
     * Analyze:
     * It's easy to find the first element which violated the ascending order, but it's useless.
     * Likes, 1, 3, 5, 4, 2, 6, the first violative element is 4, but the subarray should start from 3 rather than 5 since
     * there's a smaller element 2 after 4.
     *
     * So we need to find the last element in array which violated order, that's the end of the subarray.
     * And then, from right side to left side, do the same thing, that's the start of the subarray.
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        //from left to right side
        int start = -1, end = -1, max = nums[0], min = nums[nums.length - 1];
        for(int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            min = Math.min(nums[nums.length - 1 - i], min);
            if(nums[i] < max) end = i;
            if(nums[nums.length - 1 - i] > min) start = nums.length - 1 - i;
        }
        return (end == start) ? 0 : end - start + 1;
    }

    public static void main(String[] args) {
        Problem581 p = new Problem581();
        System.out.println(p.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }
}
