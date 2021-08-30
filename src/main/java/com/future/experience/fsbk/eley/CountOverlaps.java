package com.future.experience.fsbk.eley;

import com.future.utils.Interval;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of interval, find the number of overlaps.
 */
public class CountOverlaps {
    /**
     * [1,        5]
     *      [2,          8]
     *         [3 5]
     *         [3           9]
     *
     *  Thoughts:
     *  - Sort intervals by start time
     *  - For each interval, we care about the end time of previous intervals,
     *      if there are n previous intervals where end time is larger than current start time,
     *      then, current interval generates n overlaps.
     * @param intervals
     * @return
     */
    public static int findOverlaps(List<Interval> intervals) {
        if(intervals == null || intervals.size() < 2) {
            return 0;
        }

        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Integer> queue = new PriorityQueue<>();//store end time
        int res = 0;
        for(Interval interval : intervals) {
            while (!queue.isEmpty() && queue.peek() <= interval.start) {
                queue.poll();
            }
            res += queue.size();
            queue.offer(interval.end);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(new Interval(1, 5), new Interval(2, 8), new Interval(3, 5), new Interval(3, 9));
        System.out.println(findOverlaps(intervals));
    }
}
