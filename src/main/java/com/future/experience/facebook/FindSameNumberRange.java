package com.future.experience.facebook;


/**
 * Created by xingfeiy on 5/7/18.
 */
public class FindSameNumberRange {
    /**
     * Given a sorted array, it may include duplicated, return the number of target in array.
     * @param nums
     * @param target
     * @return
     */
    public int findSameNumber(int[] nums, int target) {
        if(nums == null || nums.length < 1) return 0;
        int start = 0, end = nums.length - 1, targetStart = -1, targetEnd = -1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                end = mid;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if(nums[start] == target) {
            targetStart = start;
        } else if(nums[end] == target) {
            targetStart = end;
        } else {
            return 0;
        }

        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                start = mid;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        targetEnd = (nums[end] == target) ? end : start;
        return targetEnd - targetStart + 1;
    }

    public static void main(String[] args) {
        FindSameNumberRange p = new FindSameNumberRange();
        System.out.println(p.findSameNumber(new int[]{}, 5)); //0
        System.out.println(p.findSameNumber(new int[]{1, 2, 3, 4, 6}, 5)); //0
        System.out.println(p.findSameNumber(new int[]{1, 2, 3, 4, 4, 5, 5, 6}, 5)); //2
        System.out.println(p.findSameNumber(new int[]{1,1 ,1, 1, 2, 3, 4, 4, 5, 5, 6}, 1)); //4
    }
}
