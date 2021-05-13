package com.future.experience.fsbk.eley;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int k) {
        if(points == null || points.length < 1) {
            return null;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((p2, p1) -> Double.compare(
                Math.sqrt(Math.pow(p1[0] - 0, 2) + Math.pow(p1[1] - 0, 2)),
                Math.sqrt(Math.pow(p2[0] - 0, 2) + Math.pow(p2[1] - 0, 2)
        )));

        for(int[] point : points) {
            queue.offer(point);
            if(queue.size() > k) {
                queue.poll();
            }
        }

        int[][] res = new int[2][queue.size()];
        int i = 0;
        while (!queue.isEmpty()) {
            res[i++] = queue.poll();
        }

        return res;
    }
}
