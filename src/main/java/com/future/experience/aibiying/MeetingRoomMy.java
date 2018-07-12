package com.future.experience.aibiying;

import com.future.utils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        
    }

    /**
     * Find the intervals that there are at least K people available
     * @param intervals
     * @param k
     * @return
     */
    public List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {}
}
