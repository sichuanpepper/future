package com.future.round2;

/**
 * <url>https://leetcode.com/problems/first-missing-positive/description/</url>
 *
 *
 * Given an unsorted integer array, find the first missing positive integer.

  For example,
  Given [1,2,0] return 3,
 and [3,4,-1,1] return 2.

  Your algorithm should run in O(n) time and uses constant space.
 * Created by someone on 12/3/17.
 */
public class Problem41 {

    /**
     * Analyze:
     * For any k elements, the missing positive element must be range [1, k + 1], examples:
     * [1, 2, 3] => here k = 3, the missing element is 4 which is in range [1, 4]
     * [1, 5, 8] => here k = 3, the missing element is 2 which is in range [1, 4]
     * [100, 1000, 10000] => here k = 3, the missing element is 1 which is in range [1, 4]
     *
     * So once we found the k, we can have a k + 1 array for next search, find the missing one.
     * Let's take [2, 0, 9, -1, 8] as example, as the above logic, we can find k = 3, non-positives got ignore.
     * Since we are asked constant space, we can't new an array here, so:
     * Partition (the way in quick sort), [2, 9, 8, 0, -1], Runtime O(n) and constant space.
     * We go through subarray from 0 to k - 1, and use subarray from 0 to k - 1 to mark the corresponding element already appeared.
     * 2 => [2, -9, 8, 0, -1], since the range [0, k - 1] are positives, we need a tag to represent the corresponding value is existed,
     * but we also need the original value, so we just make it to negative and use abs() to retrieve the original value.
     * abs(-9) => 9 is out of range, do nothing.
     * abs(8) => 8 is out of range, do nothing.
     *
     * Then we go through the subrange from 0 to k - 1, and if we found a positive, that's what we are looking for.
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length < 1) return 1;
        //partition
        int p1 = -1, p2 = 0;
        while (p2 < nums.length) {
            if(nums[p2] > 0) {
                int tmp = nums[++p1];
                nums[p1] = nums[p2];
                nums[p2++]= tmp;
            } else {
                p2++;
            }
        }
        if(p1 < 0) return 1; //there's no positive.

        for(int i = 0; i <= p1; i++) {
            int tmp = Math.abs(nums[i]) - 1;
            if(tmp <= p1) {
                nums[tmp] = nums[tmp] > 0 ? -nums[tmp] : nums[tmp];
            }
        }
        int miss = 0;
        while (miss <= p1) {
            if(nums[miss] > 0) break;
            miss++;
        }
        return miss + 1;
    }

    /**
     * Analyze:
     * 1. Unsorted Integer Array.
     * 2. First missing POSITIVE.
     *
     * Find the minimum positive, and find the count of positive integers.
     * [2, 3, 4] return 1
     * [1, 2, 3] return 4
     * [1, 100] return 2
     *
     *
     * First try failed, there could be duplicates.
     * @param nums
     * @return
     */
    public int FAILD(int[] nums) {
        //GOT ERROR, invalid given array should return 1.
        if(nums == null || nums.length < 1) return 1;
        int min = Integer.MAX_VALUE;
        int max = 0;
        long sum = 0;
        for(int num : nums) {
            if(num > 0) {
                min = Math.min(min, num);
                max = Math.max(max, num);
                sum += num;
            }
        }
        if(min == Integer.MAX_VALUE) return 1; //no positive integer is included.
        long expectedSum = max * (max + 1) / 2 - min * (min - 1) / 2; //the expected sum from min to max.
        if(expectedSum == sum) return min > 1 ? 1 : max + 1;  //is an arithmetic progression.
        if(expectedSum < sum) return (min > 1) ? 1 :  max + 1; // there are duplicates.
        return (int)(expectedSum - sum);
    }


}
