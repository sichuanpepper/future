package com.future.round2;

/**
 * https://leetcode.com/problems/sort-colors/description/
 *
 Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.

 click to show follow up.

 Follow up:
 A rather straight forward solution is a two-pass algorithm using counting sort.
 First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

 Could you come up with an one-pass algorithm using only constant space?

 * Created by xingfeiy on 12/29/17.
 */
public class Problem75 {
    /**
     * Analyze:
     * We can use two pointers, one pointer start from left side which initial as -1 and always points last 0, another pointer
     * start from right side which initial as length of array and always points last 2 from right side.
     * @param nums
     */
    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 1) return;
        int left = -1;
        int right = nums.length;
        int cur = 0;
        while (cur < right) {
            if(nums[cur] == 2) {
                nums[cur] = nums[--right];
                nums[right] = 2;
            } else if(nums[cur] == 0) {
                nums[cur] = nums[++left];
                nums[left] = 0;
                cur++;
            } else {
                cur++;
            }
        }
    }


}
