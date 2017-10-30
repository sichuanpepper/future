package com.future.round1;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.

 * Created by someone on 4/25/17.
 */
public class FindDuplicateNumber287 {
    /**
     * Analyse: This question can be translate into a new question like this:
     * There are n containers and n + 1 balls, we're going to put these n + 1 balls into n containers, so there must be
     * at least one container which contain more than one balls, try to find that container.
     *
     * So the question has been translate into a binary search.
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1; //-1 or -2? or doesn't matter?
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int cnt = 0;
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] <= mid) {
                    cnt++;
                }
            }

            if(cnt >= mid) {
                start = mid;
            } else {
                end = mid;
            }
        }

        int cnt = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == start) {
                cnt++;
            }
            if(cnt > 1) {
                return start;
            }
        }
        return end;
    }
}
