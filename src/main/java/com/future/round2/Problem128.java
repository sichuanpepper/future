package com.future.round2;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 */
public class Problem128 {
    /**
     * Clarify the question:
     * - Unsorted
     * - Has duplicated?
     * - If there are multiple results? doesn't matter for this question.
     * - Is there any time complexity or space complexity is required?
     *
     * Start from simple:
     * If the given array is sorted, we can use two pointers to track the maximum consecutive sequence, but the time complexity comes to o(nlogn)
     *
     * To guarantee run in O(n), instead of pre-sorted the array, we can build the consecutive sequence on the fly.
     * We care about the start and end of each sequence, so we maintain these information in a map, and store the current length of sequence in the value.
     *
     * For each new number in array, check if we already have number + 1 nad number - 1 in the map.
     *  - Deal with duplicated number.
     *  - Extends the result to most left and most right number.
     *
     *  Second solution:
     *  Put all numbers into a set, and then go through numbers in array one by one.
     *  - If current number is removed, which means it's duplicated.
     *  - Else, extend to smaller and larger number based on current number until there's no smaller or larger number.
     *      - For each visit, we removed the visited number from set.
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        return 0;
    }
}
