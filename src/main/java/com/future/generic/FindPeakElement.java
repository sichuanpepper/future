package com.future.generic;

/**
 * https://leetcode.com/problems/find-peak-element/
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 *
 * Thoughts:
 * - The straightforward way is just go through the numbers one by one, the run time is O(n).
 * Since it request the algorithm runs in O(log n), it's actually a hint, try binary search.
 * It's unsorted array, so the any way to do the binary search is position-based.
 * ex: [1,2,1,3,5,6,4]
 * first, we will find the mid position 3, there are 3 scenarios:
 * - mid is greater than both left and right, mid is a peak
 * - mid is smaller than left, there must be a peak in the left since nums[-1] = nums[n] = -∞
 * - mid is smaller than right, same thing
 *
 * Be careful, there's an assumption, the adjacent elements are same, otherwise, this solution doesn't work, ex: 3, 3, 3, 3, 4
 */
public class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        if(nums == null || nums.length < 1) return Integer.MIN_VALUE;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if(nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return nums[start] > nums[end] ? start : end;
    }


    /**
     * https://leetcode.com/problems/find-a-peak-element-ii/
     *
     * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
     *
     * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
     *
     * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
     *
     * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
     * @param mat
     * @return
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 500
     * 1 <= mat[i][j] <= 105
     * No two adjacent cells are equal.
     *
     * Thoughts:
     * - The simple way is finding the maximum number in the array, that must be peak, the time complexity is O(m*n)
     *
     *  If we calculate the maximum number for each column, we will get an array with the maximum number of each column, let's say maxNumOfCol
     *  One thing we are sure is these numbers are peak in vertical direction since it's the largest one.
     *  And then, we use the above solution to find the peak.
     *
     *  To find the maximum number of each column, we have to traversal all numbers, but based on the above solution, we will
     *  get the peak either in the left or in the right, we don't have to calculate maximum number for all columns.
     *
     *  Do we need to calculate the maximum number for columns (mid - 1) and (mid + 1), the answer is no.
     *  Let's say we found the max number of column mid, we just need to find if left or right has bigger number.
     */
    public int[] findPeakGrid(int[][] mat) {
        int start = 0, end = mat[0].length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            //find the max of column mid
            int maxRow = 0;
            for(int i = 0; i < mat.length; i++) {
                maxRow = mat[i][mid] > mat[maxRow][mid] ? i : maxRow;
            }

            boolean isBigLeft = mid > 0 && (mat[maxRow][mid - 1] > mat[maxRow][mid]);
            boolean isBigRight = (mid < mat[0].length - 1) && (mat[maxRow][mid] < mat[maxRow][mid + 1]);
            if(!isBigLeft && !isBigRight) {
                return new int[]{maxRow, mid};
            } else if(isBigLeft) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{3, 3, 3, 3, 4}));
    }
}
