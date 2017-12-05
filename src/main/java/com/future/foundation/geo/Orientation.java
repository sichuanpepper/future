package com.future.foundation.geo;

import com.future.utils.Point;

/**
 * http://www.geeksforgeeks.org/orientation-3-ordered-points/
 *
 * Orientation of an ordered triplet of points in the plane can be:
 *  - Counterclockwise
 *  - Clockwise
 *  - Colinear
 *
 * Created by xingfeiy on 12/4/17.
 */
public class Orientation {
    /**
     * Here, we are given 3 valid points, and implement a function to return its orientation with value
     * -1 represents Counterclockwise
     * 1 represents Clockwise
     * 0 represents Colinear.
     *
     * Analyze:
     * The solution is pretty straightforward, assume we are given 3 points p1, p2, p3, we just need calculate the
     * slope1 of p1 -> p2 and slope2 of p2 -> p3,
     *  - if slope1 > slope2, it's Clockwise
     *  - if slope1 < slope2, it's Counterclockwise
     *  - if slope1 == slope2, it's Colinear
     *
     *  slope = (y2 - y1)/(x2 - x1)
     *
     * @param points
     * @return
     */
    public static int calculate(Point[] points) {
        float slope1 = (float)(points[1].y - points[0].y) / (points[1].x - points[0].x);
        float slope2 = (float)(points[2].y - points[1].y) / (points[2].x - points[1].x);
        if(Float.compare(slope1, slope2) == 0) return 0;
        return Float.compare(slope1, slope2) > 0 ? 1 : -1;
    }

    public static void main(String[] args)
    {
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(4, 4);
        points[2] = new Point(1, 2);

        int o = calculate(points);

        if (o==0) System.out.println("Linear");
        else if (o == 1) System.out.println("Clockwise");
        else System.out.println("CounterClockwise");

        points[0] = new Point(0, 0);
        points[1] = new Point(4, 4);
        points[2] = new Point(4, 5);

        o = calculate(points);

        if (o==0) System.out.println("Linear");
        else if (o == 1) System.out.println("Clockwise");
        else System.out.println("CounterClockwise");

        points[0] = new Point(0, 0);
        points[1] = new Point(4, 4);
        points[2] = new Point(5, 5);

        o = calculate(points);

        if (o==0) System.out.println("Linear");
        else if (o == 1) System.out.println("Clockwise");
        else System.out.println("CounterClockwise");

    }
}
