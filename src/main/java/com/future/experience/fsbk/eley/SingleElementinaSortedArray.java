package com.future.experience.fsbk.eley;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 *
 *
 You are given a sorted array consisting of only integers where every element appears exactly twice,
 except for one element which appears exactly once. Find this single element that appears only once.

 Follow up: Your solution should run in O(log n) time and O(1) space.



 Example 1:

 Input: nums = [1,1,2,3,3,4,4,8,8]
 Output: 2
 Example 2:

 Input: nums = [3,3,7,7,10,11,11]
 Output: 10
 *
 * Thoughts:
 * - Binary search, since the length of array is always odd, the mid one is exactly mid one.
 * - The left sub-array and right sub-array have same length, could be even or odd.
 * - Compare the mid value with the previous one and next one.
 *  - mid == mid - 1
 *      - left is even: single number in the left
 *      - left is odd: single number in the right
 *  - mid == mid + 1
 *      - right is even: single number in the right
 *      - right is odd: single number in the left
 *  - otherwise, return it.
 *
 *
 */
public class SingleElementinaSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            boolean isEven = ((mid - start) % 2 == 0);
            if(nums[mid] == nums[mid - 1]) {
                if(isEven) {
                    end = mid - 2;
                } else {
                    start = mid + 1;
                }
            } else if(nums[mid] == nums[mid + 1]){
                if(isEven) {
                    start = mid + 2;
                } else {
                    end = mid - 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[start];

    }
}
