package com.future.round2;

import com.future.utils.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/meeting-rooms/description/
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.

 For example,
 Given [[0, 30],[5, 10],[15, 20]],
 return false.


 * Created by xingfeiy on 11/16/17.
 */
public class Problem252 {
    /**
     * Analyze:
     * To determine that, just need to check if there's overlap in given intervals.
     *
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length < 1) return true;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return Integer.compare(a.start, b.start);
            }
        });

        int endTime = 0;
        for(Interval interval : intervals) {
            if(interval.start < endTime) return false;
            endTime = interval.end;
        }
        return true;
    }
}
