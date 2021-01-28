package com.future.experience.gugou;

/**
 * https://leetcode.com/problems/rectangle-area-ii/
 */
public class RectangleAreaII {
    /**
     Let's add one more rect (4, 1, 5, 2)
     Each rect (int[4]) can generate two verticle lines:
     x     => (min_y  , max_y,  1/-1), 1: start, -1: end
     rect[0] => (rect[1], rect[3], 1)
     rect[2] => (rect[1], rect[3], -1)

     x     => (min_y  , max_y,  1/-1)
     0     => ( 0,       2,     1)
     1     => ( 0,       1,     1)
     1     => ( 0,       3,     1)
     2     => ( 0,       2,     -1)
     2     => ( 0,       3,     -1)
     3     => ( 0,       1,     -1)
     4     => ( 1,       2,     1)
     5     => ( 1,       2,     -1)

     Merge, min(min_y), max(max_y)
     0     => ( 0,       2,     1) => (1-0)*2=2, start=1
     1     => ( 0,       3,     2) => (2-1)*3=3, start=3
     2     => ( 0,       3,     -2) => (3-2)*1=1, start=1
     3     => ( 0,       1,     -1)  start=0, skip
     4     => ( 1,       2,     1)
     5     => ( 1,       2,     -1)
     **/
    public int rectangleArea(int[][] rectangles) {
        return 0;
    }
}
