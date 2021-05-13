package com.future.experience.linying.eley;

import java.util.List;

/**
 * Question Description:
 *
 * Given two sorted lists of positive integers, find out the first k pairs with the smallest product. Each pair contains one number from each lists.
 *
 *
 *
 * Given nums1 = [1,2, 5, 9], nums2 = [1, 3, 4, 6],  k = 5
 *
 * Return: [1,1],[2,1],[1,3], [1,4], [5,1]
 *
 * The first 5 pairs are returned from the following sequence:
 * [1,1],[2,1],[1,3], [1,4], [5,1], [1,6],[2,3],[2,4],[9,1],[2,6],
 * [5,3],[5,4],[9,3],[5,6],[9,4],[9,6]
 *
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 */
public class KSmallestPairs {
    /**
     * Usually, we can use max heap to solve top K issue, just put all possible pairs into the heap and maintain the length to K.
     *
     * But here we are given sorted arrays, we don't have to try all pairs, just need to find a way to move pointers from start.
     *
     * [a1, a2, a3], [b1, b2, b3]
     * [a1, b1] must be the smallest one, what's the next? the next one could be [a2, b1] or [a1, b2], we don't know, but
     * one thing we know is the next one must be one of them, so we can put both of them into max heap.
     *
     * To avoid duplicates, we can put [a1, b1], [a2, b1]...[ak, b1] into the heap as candidates, for each poll, we put the next possible into heap.
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return null;
    }
}
