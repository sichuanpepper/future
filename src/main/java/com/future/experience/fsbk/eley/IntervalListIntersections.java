package com.future.experience.fsbk.eley;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 *
 * Thoughts:
 * - Use two pointers, here are the situations:
 *  - min(end) >= max(start), has intersection, move pointer with min(end)
 *  - p1's end < p2's start, p1++
 *  - p2's end < p1's start, p2++
 */
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList == null || secondList == null || firstList.length < 1 || secondList.length < 1) {
            return new int[][]{};
        }

        int p1 = 0, p2 = 0;
        List<int[]> resList = new ArrayList<>();
        while(p1 < firstList.length && p2 < secondList.length) {
            int[] first = firstList[p1];
            int[] second = secondList[p2];
            int maxStart = Math.max(first[0], second[0]);
            int minEnd = Math.min(first[1], second[1]);
            if(minEnd >= maxStart) {
                resList.add(new int[]{maxStart, minEnd});
                if(minEnd == first[1]) {
                    p1++;
                } else {
                    p2++;
                }
            } else if(first[1] < second[0]) {
                p1++;
            } else {
                p2++;
            }
        }

        int[][] res = new int[resList.size()][2];
        for(int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }

        return res;
    }
}
