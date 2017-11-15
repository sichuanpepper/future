package com.future.round2;

import com.future.utils.Interval;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), find the minimum number of conference rooms required.

 For example,
 Given [[0, 30],[5, 10],[15, 20]],
 return 2.

 * Created by someone on 11/14/17.
 */
public class Problem253 {
    /**
     * Analyze:
     * 1. If two intervals have overlap, one additional room is required.
     * 2. Sort intervals by start time.
     * 3. Use a priority queue, when an interval comes, image the current time is the start time of the coming interval.
     *    Then check the queue and poll the intervals already over at this time out, then insert the coming interval,
     *    compute the max room required.
     * @param intervals
     * @return
     */
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length < 1) return 0;

        //sort the intervals by start time.
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        //init a priority queue with comparator which sorts intervals by end time.
        PriorityQueue<Interval> pq = new PriorityQueue<>(intervals.length, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.end, o2.end);
            }
        });

        int maxRoom = 0;
        for(Interval interval : intervals) {
            while (!pq.isEmpty()) {
                if(pq.peek().end <= interval.start) {
                    pq.poll();
                } else break;
            }
            pq.offer(interval);
            maxRoom = Math.max(maxRoom, pq.size());
        }
        return maxRoom;
    }

    public int solution2(Interval[] intervals) {
        if(intervals == null || intervals.length < 1) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return Integer.compare(a.start ,b.start);
            }
        });

        List<Interval> res = new ArrayList<>();
        res.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            boolean found = false;
            for(int j = 0; j < res.size(); j++) {
                if(intervals[i].start >= res.get(j).end) {
                    res.set(j, intervals[i]);
                    found = true;
                    break;
                }
            }
            if(!found) {
                res.add(intervals[i]);
            }
        }
        return res.size();
    }
}
