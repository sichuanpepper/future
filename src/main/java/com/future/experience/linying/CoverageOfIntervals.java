package com.future.experience.linying;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 实现一个datastructure class，里面有两个function，一个是 void addIntervals(int from, int to)，另一个是int getCoverageLength();
 * 比如:
 * addIntervals(3,7)
 * addIntervals(8,10)
 * addIntervals(1,5)
 * getCoverageLength() -> 6 + 2 = 8
 *
 * Analyze:
 * Ask the frequency of each function...
 *
 * Thoughts:
 * - Merge and sort it when we insert an interval, so the intervals is always a sorted list without overlap.
 * |___|   |_____|       |__|
 *
 * For each insert, we do the binary search for start and end (the start or end is in the middle of an interval),
 * we may find at most two intervals (at most two overlaps), the length of insert interval - length of overlaps,
 * that's the new length increased.
 *
 */
public class CoverageOfIntervals {


    private int length = 0;

    private List<int[]> intervals = new ArrayList<>();

    public void addIntervals(int from, int to) {
        int idx = findInterval(intervals, from);
        int overlap = 0;
        while (intervals.get(idx)[0] < to) {
            if(from <= intervals.get(idx)[0] && to >= intervals.get(idx)[1]) {
                overlap += intervals.get(idx)[1] - intervals.get(idx)[0];
            } else if(from > intervals.get(idx)[0] && to < intervals.get(idx)[1]) {
                overlap += to - from;
            } else {
                overlap += (Math.min(to, intervals.get(idx)[1]) - Math.max(from, intervals.get(idx)[0]));
            }
            idx++;
        }

    }

    public int getCoverageLength() {
        return 0;
    }

    private int findInterval(List<int[]> intervals, int time) {
        int start = 0, end = intervals.size() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if(time >= intervals.get(mid)[0] && time <= intervals.get(mid)[1]) {
                return mid;
            } else if(start < intervals.get(mid)[0]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
