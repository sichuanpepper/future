package com.future.experience.fsbk;

public class SingleElementinaSortedArray {
    /**
     Binaray search:
     - If there are even elements in the left of mid
     - [mid] == [mid + 1], the single number is in the right side, start = mid + 2
     -  else, end = mid
     - If there are odd elements in the left of mid
     - [mid] == [mid - 1], the single number is in the right side, start = mid + 1
     - else, end = mid - 1
     **/
    public int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if((mid - start) % 2 == 0) {
                if(nums[mid] == nums[mid + 1]) {
                    start = mid + 2;
                } else {
                    end = mid;
                }
            } else {
                if(nums[mid] == nums[mid - 1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }
        return nums[start];
    }
}
