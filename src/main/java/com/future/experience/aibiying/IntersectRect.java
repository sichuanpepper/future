package com.future.experience.aibiying;

import java.util.List;

/**
 * Created by xingfeiy on 7/8/18.
 */
public class IntersectRect {
    public int countIntersectedRect(List<int[][]> rectangles) {
        if(rectangles == null || rectangles.size() < 1) return 0;
        int num = rectangles.size();
        //from 0 to n-1
        int[] ids = new int[num];
        for(int i = 0; i < ids.length; i++) ids[i] = i;

        for(int i = 0; i < rectangles.size() - 1; i++) {
            for(int j = i + 1; j < rectangles.size(); j++) {
                if(intersected(rectangles.get(i), rectangles.get(j))) {}
            }
        }
    }

    private boolean intersected(int[][] rect1, int[][] rect2) {
        int x1 = rect1[0][0], y1 = rect1[0][1], x2 = rect1[1][0], y2 = rect1[1][1];
        int x3 = rect2[0][0], y3 = rect2[0][1], x4 = rect2[1][0], y4 = rect2[1][1];
        return (x1 > x3 && x1 < x4 && y1 < y3 && y1 > y4) || (x2 > x3 && x2 < x4 && y2 < y3 && y2 > y4) ||
                (x1 > x3 && x1 < x4 && y2 < y3 && y2 > y4) || (x2 > x3 && x2 < x4 && y1 < y3 && y3 > y4);
    }
}
