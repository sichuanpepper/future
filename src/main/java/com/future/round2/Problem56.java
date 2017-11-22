package com.future.round2;

import com.future.utils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 *
 * Created by someone on 11/16/17.
 *
 */
public class Problem56 {
    /**
     * Analyze:
     * 1. Sort the interval by start number.
     * 2. Go through interval one by one, we use a tmp interval for merged intervals,
     *  - an interval comes, if the start is less than the end of tmp interval, do merge, otherwise, put the tmp interval
     *      into results and generate new tmp interval.
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if(intervals == null || intervals.size() < 1) return res;
        //sort
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        Interval tmp = intervals.get(0);
        for(int i = 1; i < intervals.size(); i++) {
            if(intervals.get(i).start <= tmp.end) {
                //the end of intervals.get(i) maybe less than end of tmp.
                tmp.end = Math.max(tmp.end, intervals.get(i).end);
            } else {
                res.add(tmp);
                tmp = intervals.get(i);
            }
        }
        res.add(tmp);
        return res;
    }
}
