package com.future.foundation.geo;

import com.future.utils.Point;

/**
 * http://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
 *
 * Problem:
 * We are given two segments, and need to check if these two segments intersected with each other.
 *
 * Let's use four points to represent two segments here, segment1 s1(x1, y1), e1(x2, y2) and segment2 s2(x3, y3) and e2(x4, y4).
 *
 * Two segments intersected if:
 * 1. (s1, e1, s2) and (s1, e1, e2) have different orientation AND
 *    (s2, e2, s1) and (s2, e2, e1) have different orientation.
 * 2. Special case, both (s1, e1, s2), (s1, e1, e2), (s2, e2, s1) and (s2, e2, e1) are colinear orientation.
 *      - the x-projections of (s1, e1) and (s2, e2) intersected.
 *      - the y-projections of (s1, e1) and (s2, e2) intersected.
 *
 * Created by someone on 12/4/17.
 */
public class TwoSegmentsIntersection {
    public static boolean intersect(Point s1, Point e1, Point s2, Point e2) {
        int o1 = Orientation.calculate(new Point[]{s1, e1, s2});
        int o2 = Orientation.calculate(new Point[]{s1, e1, e2});
        int o3 = Orientation.calculate(new Point[]{s2, e2, s1});
        int o4 = Orientation.calculate(new Point[]{s2, e2, e1});
        if(o1 != 0 && o1 == o2) return false;
        if(o3 != 0 && o3 == o4) return false;

        if(o1 == o2 && o2 == o3 && o3 == o4 && o1 == 0) {
            return onSegment(s1, e1, s2) || onSegment(s1, e1, e2) || onSegment(s2, e2, s1) || onSegment(s2, e2, e1);
        }
        return true;
    }

    /**
     * Check if point p is lying on segment(s, e).
     * @param s
     * @param e
     * @param p
     * @return
     */
    public static boolean onSegment(Point s, Point e, Point p) {
        return p.x < Math.max(s.x, e.x) && p.x > Math.min(s.x, e.x) && p.y < Math.max(s.y, e.y) && p.y > Math.min(s.y, e.y);
    }
}
