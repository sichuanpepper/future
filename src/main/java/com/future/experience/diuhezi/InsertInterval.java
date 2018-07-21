package com.future.experience.diuhezi;

import com.future.utils.Interval;

import java.util.ArrayList;
import java.util.List;


/**
 * https://leetcode.com/problems/insert-interval/description/
 *
 *
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * Created by xingfeiy on 12/9/17.
 */
public class InsertInterval {
    /**
     * Analyze:
     * Let's write down more test cases here first.
     * - if intervals is empty, insert it.
     * - [3, 5], [6, 9]  insert [1, 2] or [10, 11]
     * - [3, 8], [9, 10] insert [4, 6], nothing change
     * - [3, 6], [7, 9] insert [5, 8], merge
     * So, basically there are 3 situations
     * - Insert it as new interval, both start and end are not in any interval.
     *      - Includes smaller intervals, remove smaller intervals.
     *      - Otherwise, just insert.
     * - Merge multiple intervals
     *      - Both start and end in different intervals
     * - Extends an interval.
     *      - Either start or end in an interval
     * - Do nothing.
     *
     * [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9]
     *
     * [1, 2], no overlap with [4, 9] and end(2) is smaller than start(4) of newInterval, put it to result
     * [3, 5], has overlap with [4, 9], merge it to newInterval,then newInterval is [3, 9].
     * [6, 7], it's included in[3, 9], do nothing.
     * [8, 10], has overlap with[3, 9], merge it to newInterval, then newInterval is [3, 10]
     * [12, 16], the start is greater than end of newInterval, put newInterval to result, then put this interval to result.
     *
     *
     *  [Follow up] The intervals come up one by one
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(newInterval == null) return intervals;
        List<Interval> res = new ArrayList<>();
        if(intervals == null || intervals.size() < 1) {
            res.add(newInterval);
            return res;
        }

        for(Interval interval : intervals) {
            if(interval.end < newInterval.start) {
                res.add(interval); //no overlap before newInterval
            } else if(interval.start > newInterval.end) {
                res.add(new Interval(newInterval.start, newInterval.end)); //no overlap after newInterval
                res.add(interval);
                newInterval = new Interval(Integer.MAX_VALUE, Integer.MAX_VALUE);
            } else if(interval.start < newInterval.start) {
                if(interval.end <= newInterval.end) {
                    newInterval.start = interval.start;
                } else {
                    res.add(interval); //newInterval is included in current interval.
                    newInterval = new Interval(Integer.MAX_VALUE, Integer.MAX_VALUE);
                }
            } else if(interval.start >= newInterval.start) {
                if(interval.end <= newInterval.end) {
                    //do nothing
                } else {
                    newInterval.end = interval.end;
                }
            }
        }
        if(newInterval.start != Integer.MAX_VALUE) res.add(newInterval);
        return res;
    }
}
