package com.future.experience.aibiying;

import com.future.utils.Interval;

import java.sql.Time;
import java.util.*;

/**
 * Created by xingfeiy on 7/11/18.
 */
public class MeetingRoomMy {
    private class TimePoint {
        public int time;

        public boolean isStart;

        public TimePoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public String toString() {
            return "TimePoint{" +
                    "time=" + time +
                    ", isStart=" + isStart +
                    '}';
        }
    }
    /**
     * Find the intervals without meeting
     * @param intervals
     * @return
     */
    public List<Interval> getAvailableIntervals(List<List<Interval>> intervals) {
        List<Interval> res = new ArrayList<>();
        List<TimePoint> tps = new ArrayList<>();
        for(List<Interval> is : intervals) {
            for(Interval i : is) {
                tps.add(new TimePoint(i.start, true));
                tps.add(new TimePoint(i.end, false));
            }
        }

        Collections.sort(tps, (o1, o2) -> (o1.time - o2.time));

        TimePoint start = null;
        int countOfStart = 0;
        for(TimePoint tp : tps) {
            countOfStart += tp.isStart ? 1 : -1;
            if(countOfStart == 0) {
                start = tp;
            } else if(countOfStart == 1 && start != null) {
                if(tp.time > start.time) res.add(new Interval(start.time, tp.time));
                start = null;
            }
        }
        return res;
    }

    /**
     * Find the intervals that there are at least K people available.
     * Question:
     * - Can we assume that there's no overlap meeting for each person.
     * - Does order matter in results?
     * - If there are consecutive intervals in results, do we need merge it or either way is fine?
     *  - If we want to separate intervals, start left first and then end (the sort)
     *  - If we want to merge it, end left first and then start.
     * @param intervals
     * @param k
     * @return
     */
    public List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
        List<Interval> res = new ArrayList<>();
        List<TimePoint> tps = new ArrayList<>();
        for(List<Interval> is : intervals) {
            for(Interval i : is) {
                tps.add(new TimePoint(i.start, true));
                tps.add(new TimePoint(i.end, false));
            }
        }

        Collections.sort(tps, (o1, o2) -> {
            if(o1.time == o2.time) {
                //return o1.isStart ? 1 : -1;  //merge consecutive.
                return o1.isStart ? -1 : 1;  //keep separate
            }
            return o1.time - o2.time;
        });
        TimePoint start = null;
        int countOfStart = 0;
        for(TimePoint tp : tps) {
            countOfStart += tp.isStart ? 1 : -1;
            if(countOfStart <= (intervals.size() - k) && start == null) {
                start = tp;
            } else if(countOfStart > (intervals.size() - k) && start != null) {
                if(tp.time > start.time) res.add(new Interval(start.time, tp.time));
                start = null;
            }
        }
        //don't forget!!!!!
        if(start != null) res.add(new Interval(start.time, tps.get(tps.size() - 1).time));
        return res;
    }

    public static void main(String[] args) {
        MeetingRoomMy m = new MeetingRoomMy();
        List<List<Interval>> intervals = new ArrayList<>();
        intervals.add(Arrays.asList(new Interval[]{new Interval(1, 3), new Interval(3, 5)}));
        intervals.add(Arrays.asList(new Interval[]{new Interval(2, 4), new Interval(6, 8)}));
        intervals.add(Arrays.asList(new Interval[]{new Interval(8, 10), new Interval(11, 12)}));
        intervals.add(Arrays.asList(new Interval[]{new Interval(1, 12)}));
//        intervals.add(Arrays.asList(new Interval[]{new Interval(11, 12)}));
        for(Interval in : m.getAvailableIntervals(intervals, 2)) {
            System.out.println(in.start + " to " + in.end);
        }

    }
}
