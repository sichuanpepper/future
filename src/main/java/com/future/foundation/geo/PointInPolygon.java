package com.future.foundation.geo;

import com.future.utils.Point;

/**
 * http://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/
 *
 * Problem:
 * Check if a point lies inside the given polygon or not.
 * The point lies on edge considers inside.
 *
 * Created by someone on 12/4/17.
 */
public class PointInPolygon {
    /**
     * Check if given point lies inside given polygon.
     *
     * Analyze:
     * 1. Draw a horizontal line to the right for given point and extend it to infinity(let's call it inf_p).
     * 2. Calculate the count of edges of polygon which intersect with inf_p.
     *      - if the count if odd, point lies inside polygon.
     *      - if the point lies on an edge (in this case, point intersects with two edges).
     *          - Three points has orientation colinear and point lies on edge.
     *
     *
     * @param polygon
     * @param point
     * @return
     */
    public static boolean inside(Point[] polygon, Point point) {
        if(polygon == null || polygon.length < 3) return false;
        Point infPoint = new Point(10000, point.y);

        int count = 0;
        //there are polygon.length - 1 edges
        for(int i = 0; i < polygon.length; i++) {
            Point next = (i == polygon.length - 1) ? polygon[0] : polygon[i + 1];
            //calculate intersect
            if(TwoSegmentsIntersection.intersect(point, infPoint, polygon[i], next)) {
                count++;
                //check if point lies on edge
                if(Orientation.calculate(new Point[]{polygon[i], next, point}) == 0 &&
                        TwoSegmentsIntersection.onSegment(polygon[i], next, point)) {
                    return true;
                }
            }
        }
        return count % 2 == 1;
    }
}
