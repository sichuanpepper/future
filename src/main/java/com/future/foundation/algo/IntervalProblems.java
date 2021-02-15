package com.future.foundation.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Make sure all interval are valid, which the start time < end time
 * 1. Sort intervals.
 * - Sort it by start time.
 * 2. Merge it.
 * - Are [a, b], [b, c] considered as overlap?
 * - Use two variables to track current start and end.
 * - If current end less than the start of current interval, then create new interval(start, end), and reassign start and end.
 * - Otherwise, maximize end.
 * - Don't forgot the last interval.
 * <p>
 * 3. Insert a interval, merge if necessary.
 * <p>
 * 3. Find the maximum or minimum overlaps.
 */
public class IntervalProblems {
    /**
     * https://leetcode.com/problems/merge-intervals/
     * Given a collection of intervals, merge all overlapping intervals.
     * <p>
     * Is it sored? No!
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        return null;
    }


    /**
     * https://leetcode.com/problems/insert-interval/
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        return null;
    }

    /**
     * https://leetcode.com/problems/non-overlapping-intervals/
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        return 0;
    }


    // Definition of Interval:
    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //Meeting room
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals == null || intervals.size() < 2) return true;
        Collections.sort(intervals, (a, b)->(Integer.compare(a.start, b.start)));
        for(int i = 1; i < intervals.size(); i++) {
            if(intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * find the minimum number of conference rooms required.
     *
     * TC: nlog(n), SC: o(n)
     * @param intervals
     * @return
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if(intervals == null || intervals.size() < 1) return 0;
        //int[0]: time, int[1]: 0->start, 1->end
        List<int[]> points = new ArrayList<>();
        for(Interval interval : intervals) {
            points.add(new int[]{interval.start, 1});
            points.add(new int[]{interval.end, -1});
        }
        Collections.sort(points, (a, b)->Integer.compare(a[0], b[0]));
        int cur = 0, res = 0;
        for(int[] point : points) {
            cur += point[1];
            res = Math.max(res, cur);
        }
        return res;
    }

    public int minMeetingRooms2(List<Interval> intervals) {
        if(intervals == null || intervals.size() < 1) return 0;
        Collections.sort(intervals, (a, b)->Integer.compare(a.start, b.start));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int res = 0;
        for(Interval interval : intervals) {
            if(!queue.isEmpty() && interval.start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(interval.end);
            res = Math.max(res, queue.size());
        }

        return res;
    }

    public void testMinMeetingRooms2() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(0,30));
        intervals.add(new Interval(5, 10));
        intervals.add(new Interval(15, 20));
        IntervalProblems problems = new IntervalProblems();
        System.out.println(problems.minMeetingRooms2(intervals));
    }

    /**
     * Given a list of intervals calendar and a number of available conference rooms. For each query,
     * return true if the meeting can be added to the calendar successfully without causing a conflict, otherwise false.
     * A conference room can only hold one meeting at a time.
     *
     * Example 1:
     *
     * Input: calendar = [[1, 2], [4, 5], [8, 10]], rooms = 1, queries = [[2, 3], [3, 4]]
     * Output: [true, true]
     * Example 2:
     *
     * Input: calendar = [[1, 2], [4, 5], [8, 10]], rooms = 1, queries = [[4, 5], [5, 6]]
     * Output: [false, true]
     * Example 3:
     *
     * Input:
     * calendar = [[1, 3], [4, 6], [6, 8], [9, 11], [6, 9], [1, 3], [4, 10]]
     * rooms = 3
     * queries = [[1, 9], [2, 6], [7, 9], [3, 5], [3, 9], [2, 4], [7, 10], [5, 9], [3, 10], [9, 10]]
     * @param intervals
     * @param numberOfRoom
     * @param queries
     * @return
     */
    public boolean[] isAbleToScheduleMeetings(List<Interval> intervals, int numberOfRoom, List<Interval> queries) {
        return null;
    }

    public static void main(String[] args) {

    }
}
