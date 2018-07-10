package com.future.experience.aibiying;

import java.util.ArrayList;
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
                int idi = findId(ids, i);
                int idj = findId(ids, j);
                if(idi == idj) continue;
                if(intersected(rectangles.get(i), rectangles.get(j))) {
                    union(ids, i, j);
                    num--;
                    for(int n = 0; n < ids.length; n++) {
                        if(ids[n] == idj) {
                            ids[n] = idi;
                        }
                    }
                }
            }
        }
        return num;
    }

    private int findId(int[] ids, int i) {
        while (ids[i] != i) i = ids[i];
        return i;
    }

    private void union(int[] ids, int i, int j) {
        int idi = findId(ids, i);
        int idj = findId(ids, j);
        if(idi == idj) return;
        ids[idj] = idi;
    }

    private boolean intersected(int[][] rect1, int[][] rect2) {
        int x1 = rect1[0][0], y1 = rect1[0][1], x2 = rect1[1][0], y2 = rect1[1][1];
        int x3 = rect2[0][0], y3 = rect2[0][1], x4 = rect2[1][0], y4 = rect2[1][1];
        if(x1 > x4 || x3 > x2) return false;
        if(y2 > y3 || y4 > y1) return false;
        return true;
    }

    public static void main(String[] args) {
        IntersectRect i = new IntersectRect();
        List<int[][]> rects = new ArrayList<>();
        rects.add(new int[][]{{0, 2},{2, 0}});
        rects.add(new int[][]{{1, 4},{4, 1}});
        rects.add(new int[][]{{5, 2},{7, 0}});
        rects.add(new int[][]{{6, 4},{8, 1}});
//        rects.add(new int[][]{{-1, 10},{10, -1}});
        System.out.println(i.countIntersectedRect(rects));
    }
}
