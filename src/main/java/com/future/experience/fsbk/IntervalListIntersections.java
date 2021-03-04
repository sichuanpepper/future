package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyze:
 * It also can be solved by convert an interval into two points and then sort all points.
 * But here just has two interval list, we can simply use two pointer.
 * Always move the earlier end interval.
 * For each movement, we check if there's any intersection. larger start <= small end
 *
 * followup: solved by binary search
 * https://leetcode.com/problems/interval-list-intersections/discuss/254665/Java-O
 */
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList.length < 1 || secondList.length < 1) {
            return new int[][]{};
        }

        int first = 0, second = 0;
        List<int[]> res = new ArrayList<>();
        while (first < firstList.length && second < secondList.length) {
            int largeStart = Math.max(firstList[first][0], secondList[second][0]);
            int smallEnd = Math.min(firstList[first][1], secondList[second][1]);
            if(largeStart <= smallEnd) {
                res.add(new int[]{largeStart, smallEnd});
            }
            if(firstList[first][1] == smallEnd) {
                first++;
            }
            if(secondList[second][1] == smallEnd) {
                second++;
            }
        }

        int[][] array = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++) {
            array[i] = res.get(i);
        }
        return array;
    }
}
