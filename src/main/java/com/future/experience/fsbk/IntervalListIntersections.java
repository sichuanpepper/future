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

    /**
     * First list is much bigger than second
     *
     * Go through intervals in second one by one
     * - Do binary search and find position p of first end greater than given start.
     * - Check if there's intersection.
     *      - p.start < s.end, there's intersection {max(p.start, s.start), min(p.end, s.end)}
     *
     * - s.end > p.end, may have more intervals in first can generate intersection with s, search space = p + 1 to end
     * - s.end < p.end, move to next interval in b and search space = p to end.
     *
     *
     *  Not tested!
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersectionFollowup(int[][] firstList, int[][] secondList) {
        if(firstList.length < 1 || secondList.length < 1) {
            return new int[][]{};
        }

        if(firstList.length < secondList.length) {
            return intervalIntersectionFollowup(secondList, firstList);
        }

        int index = 0, start = 0;
        List<int[]> res = new ArrayList<>();
        while (index < secondList.length) {
            int[] s = secondList[index];
            int p = binarySearch(firstList, start, firstList.length, s[1]);
            //check intersection, p.end > s.start
            if(s[1] > firstList[p][0]) {
                res.add(new int[]{Math.max(s[0], firstList[p][0]), Math.min(s[1], firstList[p][1])});
            }
            if(s[1] > firstList[p][1]) {
                start = p + 1;
            } else {
                start = p;
                index++;
            }
        }

        int[][] array = new int[res.size()][2];

        for(int i = 0; i < res.size(); i++) {
            array[i] = res.get(i);
        }
        return array;

    }

    private int binarySearch(int[][] firstList, int start, int end, int target) {
        while(start < end) {
            int mid = start + (end - start) / 2;
            int[] interval = firstList[mid];
            if(interval[1] > target) {
                end = mid;  //since we want to find the first one greater than target, the mid one might be result, so we don't move it to mid - 1.
            } else {
                start = mid + 1; // the mid is smaller than target, the mid is not the result, so we move it to mid + 1.
            }
        }
        return start;
    }
}
