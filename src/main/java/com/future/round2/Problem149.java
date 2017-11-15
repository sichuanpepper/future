package com.future.round2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/max-points-on-a-line/description/
 * <p>
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p>
 * <p>
 * Created by someone on 11/7/17.
 */
public class Problem149 {
    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public static int maxPoints(Point[] points) {
        if(points == null) return 0;
        if(points.length <= 2) return points.length;
        int max = 0;
        Map<Double,Integer> map = new HashMap<>();
        for(int i = 0; i < points.length; i++) {
            int samePoints = 0;
            int sameX = 1;
            for(int j = i + 1; j < points.length; j++) {
                if(points[i].x == points[j].x && points[i].y == points[j].y) samePoints++;
                if(points[i].x == points[j].x) {
                    sameX++;
                    continue;
                }
                double k = (double)(points[i].y - points[j].y) / (double)(points[i].x - points[j].x);
                if(map.containsKey(k)) {map.put(k, map.get(k) + 1);}
                else map.put(k, 2);
                max = Math.max(max, map.get(k) + samePoints);
            }
            max = Math.max(max, sameX);
        }
        return max;
    }

    public static void main(String[] args) {
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(1, -1)};
        System.out.println(maxPoints(points));
    }
}
