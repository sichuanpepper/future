package com.future.experience.fsbk;

/**
 * Created by xingfeiy on 5/15/18.
 */
public class KthElementInArray {
    /**
     * Given a unsorted array, find the kth largest element in array.
     * ex, nums = {5, 2, 1, 4, 2}, k = 2, return 4.
     *
     * Analyze:
     * Quick sort
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < 1 || k < 1 || k >= nums.length) return Integer.MIN_VALUE;
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        if(start >= end) return nums[start];
        int partition = partition(nums, start, end);
        if(end - partition + 1 == k) {
            return nums[partition];
        } else if(end - partition + 1 > k) {
            return quickSelect(nums, partition + 1, end, k);
        } else {
            return quickSelect(nums, start, partition - 1, k - (end - partition + 1));
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int p1 = start - 1, p2 = start;
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

    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
     *
     * Find kth element in sorted 2d array
     *
     * @param matrix
     * @param k
     * @return
     */
    public int findKthLargest(int[][] matrix, int k) {return 0;}


    public static void main(String[] args) {
        KthElementInArray p = new KthElementInArray();
        System.out.println(p.findKthLargest(new int[]{5, 2, 1, 4, 2}, 2));  //4
        System.out.println(p.findKthLargest(new int[]{1, 1, 1, 1, 1, 1}, 2));  //1
        System.out.println(p.findKthLargest(new int[]{1, 1, 1, 1, 2, 2}, 3));  //1
    }
}
