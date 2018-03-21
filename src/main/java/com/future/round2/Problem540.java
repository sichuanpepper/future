package com.future.round2;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/description/
 *
 * Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

 Example 1:
 Input: [1,1,2,3,3,4,4,8,8]
 Output: 2
 Example 2:
 Input: [3,3,7,7,10,11,11]
 Output: 10
 Note: Your solution should run in O(log n) time and O(1) space.


 * Created by xingfeiy on 3/15/18.
 */
public class Problem540 {
    public int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(start + 1< end) {
            int mid = start + (end - start) / 2;
            if(mid % 2 == 0) {
                if(nums[mid] == nums[mid - 1]) {
                    end = mid;
                } else if (nums[mid] == nums[mid + 1]) {
                    start = mid;
                } else {
                    return nums[mid];
                }
            } else {
                if(nums[mid] == nums[mid - 1]) {
                    start = mid;
                } else if(nums[mid] == nums[mid + 1]) {
                    end = mid;
                } else {
                    return nums[mid];
                }
            }
        }
        if(start > 0 && nums[start] == nums[start - 1]) {
            return nums[end];
        }
        return nums[start];
    }
}
