package com.future.round2;

import com.future.utils.DisplayUtils;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.



 * Created by someone on 11/30/17.
 *
 */
public class Problem215 {
    /**
     * Analyze:
     * Key points: Unsorted array, array is not empty and k is valid.
     * How about if there are duplicated elements in array? It doesn't affect the result.
     *
     * We can use quick sort algorithm, but we don't have to sort all element.
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length - 1, nums.length - k);
    }

    private int helper(int[] nums, int start, int end, int k) {
        int pivot = partition(nums, start, end);
        if(pivot == k) {
            return nums[pivot];
        } else if(pivot > k) {
            return helper(nums, start, pivot - 1, k);
        } else {
            return helper(nums, pivot + 1, end, k);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int p1 = start - 1;
        int p2 = start;
        while (p2 < end) {
            if(nums[p2] < pivot) {
                int tmp = nums[++p1];
                nums[p1] = nums[p2];
                nums[p2++] = tmp;
            } else {
                p2++;
            }
        }
        nums[end] = nums[++p1];
        nums[p1] = pivot;
        return p1;
    }

    public static void main(String[] args) {
        Problem215 p = new Problem215();
        System.out.println(p.findKthLargest(new int[]{1}, 1));
        System.out.println(p.findKthLargest(new int[]{1, 2, 3, 4}, 2));
        System.out.println(p.findKthLargest(new int[]{1, 2, 3, 4}, 4));
        System.out.println(p.findKthLargest(new int[]{1, 4, 2, 5, 3}, 4));
    }
}
