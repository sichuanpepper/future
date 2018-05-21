package com.future.experience.facebook;

import com.future.utils.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 5/19/18.
 */
public class MergeIntervals {
    public List<Interval> merge(Interval[] int1, Interval[] int2) {
        //check null
        int1 = (int1 == null) ? new Interval[]{} : int1;
        int2 = (int2 == null) ? new Interval[]{} : int2;
        List<Interval> res = new ArrayList<>();
        int p1 = 0, p2 = 0;
        Interval pre = new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE);
        while(p1 < int1.length || p2 < int2.length) {
            int p1Start = p1 < int1.length ? int1[p1].start : Integer.MAX_VALUE;
            int p2Start = p2 < int2.length ? int2[p2].start : Integer.MAX_VALUE;
            //cur interval always points smaller one, and always move smaller one pointer.
            Interval cur = p1Start < p2Start ? int1[p1++] : int2[p2++];
            //merge intervals
            if(cur.start <= pre.end) {
                pre.end = Math.max(pre.end, cur.end);
            } else {
                res.add(pre);
                pre = cur;
            }
        }
        //don't forget the last one...
        res.add(pre);
        return res.subList(1, res.size());
    }

    public static void main(String[] args) {
        Interval[] int1 = new Interval[]{};
        Interval[] int2 = new Interval[]{new Interval(1, 3), new Interval(2, 5), new Interval(6, 8)};
        MergeIntervals p = new MergeIntervals();
        for(Interval i : p.merge(int1, int2)) {
            System.out.println("Interval " + i.start + " - " + i.end);
        }

        System.out.println("======================");

        int1 = new Interval[]{new Interval(1, 2), new Interval(2, 5), new Interval(18, 19)};
        int2 = new Interval[]{new Interval(4, 7), new Interval(4, 9), new Interval(10, 12)};
        for(Interval i : p.merge(int1, int2)) {
            System.out.println("Interval " + i.start + " - " + i.end);
        }
    }
}
