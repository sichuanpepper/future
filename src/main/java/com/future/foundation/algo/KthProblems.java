package com.future.foundation.algo;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * https://leetcode.com/problems/top-k-frequent-elements/
 * https://leetcode.com/problems/k-th-symbol-in-grammar/
 * https://leetcode.com/problems/the-kth-factor-of-n/
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 *
 * There are three common solutions for kth problems.
 *  - Sorted input and get kth elements, easy but unable to deal with streaming data, TC: Nlog(N), SC: (1)
 *  - Max-heap, able to deal with streaming data, TC: Nlog(K), SC: O(K)
 *  - Quick select, Avg TC: O(N), worst case O(N^2), SC: O(1)
 */
public class KthProblems {
    /**
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * - Unsorted.
     * - May has duplicated elements.
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return 0;
    }

//    private int findKthLargestHelper(int[] nums, int start, int end) {
//        if(start > end) return 0;
//        int pivot = partition(nums, start, end);
//    }

    private int partition(int[] nums, int start, int end) {
        if(start == end) return start;
        int pivot = nums[end], p1 = start - 1, p2 = start;
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
        nums[p1]= pivot;
        return p1;
    }
}
