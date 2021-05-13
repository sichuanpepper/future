package com.future.experience.fsbk.eley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 *
 * Thoughts:
 * - Since we are given a sorted array, one common solution is binary search.
 * - We do binary search, search x in the array, we can find a position:
 *  - position >= 0, extends it to both left and right based on the diff
 *  - position < 0, -(insert_pos) - 1 -> insert_pos = -(position) - 1
 *      - 0, [0, k)
 *      - len of array, [len - k, len)
 *      - extends it to both left and right based on the diff
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if(arr == null || arr.length < 1 || k < 1) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int lower = 0, upper = arr.length;

        //todo
        return res;
    }
}
