package com.future.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

 Note:
 Each element in the result must be unique.
 The result can be in any order.

 * Created by xingfeiy on 4/17/18.
 */
public class Problem349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0;
        List<Integer> res = new ArrayList<>();
        while(p1 < nums1.length && p2 < nums2.length) {
            if(p1 > 0 && nums1[p1] == nums1[p1 - 1]) {
                p1++;
            } else if(p2 > 0 && nums2[p2] == nums2[p2 - 1]) {
                p2++;
            } else if(nums1[p1] == nums2[p2]) {
                res.add(nums1[p1++]);
                p2++;
            } else if(nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }

        int[] array = new int[res.size()];
        for(int i = 0; i < res.size(); i++) array[i] = res.get(i);
        return array;
    }
}
