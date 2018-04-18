package com.future.foundation.sort;

import com.future.utils.DisplayUtils;

import java.util.Random;

/**
 * Created by xingfeiy on 4/12/18.
 */
public class BucketSort {
    /**
     * Here we just consider non-negative array.
     * It's the simple way just want to present how bucket sort works.
     * @param nums
     * @return
     */
    public static int[] bucketSort(int[] nums) {
        if(nums == null || nums.length < 1) return nums;

        int max = Integer.MIN_VALUE;
        for(int num : nums) max = Math.max(max, num);

        int[] buckets = new int[max + 1];
        for(int num : nums) buckets[num]++;

        int index = 0;
        for(int i = 0; i < buckets.length; i++) {
            for(int j = 0; j < buckets[i]; j++) {
                nums[index++] = i;
            }
        }
        return nums;
    }

    /**
     * Let's say there are 1M(or even much more) 8bits unsigned integers, find the kth larger number in the given array.
     * @param k
     * @return
     */
    public static int findkthLargerNum(int total, int k) {
        int[] buckets = new int[1<<8];
        Random random = new Random();
        for(int i = 0; i < total; i++) {
            int val = random.nextInt(1<<8);
            System.out.println(val);
            buckets[val]++;
        }

        for(int i = buckets.length - 1; i >=0; i--) {
            for(int j = 0; j < buckets[i]; j++) if(--k == 1) return i;
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        DisplayUtils.printArray(bucketSort(new int[]{}));
        DisplayUtils.printArray(bucketSort(new int[]{1, 2, 3}));
        DisplayUtils.printArray(bucketSort(new int[]{3, 2, 8, 4, 3, 2, 3, 8, 0, 5, 0}));

        System.out.println(findkthLargerNum(10, 2));
    }
}
