package com.future.round2;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * https://www.cnblogs.com/Dylan-Java-NYC/p/12200703.html
 *
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with
 * the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the
 * same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair
 * with the smallest bike index). We repeat this process until there are no available workers.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation:
 * Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation:
 * Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2,
 * and Worker 2 will take Bike 1. So the output is [0,2,1].
 * Note:
 *
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 1000
 */
public class Problem1057 {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //int[0]: index of worker, int[1]: index of bike, int[2]: distance.
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
           if(a[2] != b[2]) {
               return Integer.compare(a[2], b[2]);
           } else if(a[0] != b[0]) {
               return Integer.compare(a[0], b[0]);
           } else {
               return Integer.compare(a[1], b[1]);
           }
        });
        for(int i = 0; i < workers.length; i++) {
            for(int j = 0; j < bikes.length; j++) {
                queue.offer(new int[]{i, j, Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1])});
            }
        }
        boolean[] visitedWorker = new boolean[workers.length];
        boolean[] visitedBike = new boolean[bikes.length];
        int[] res = new int[bikes.length];
        while (!queue.isEmpty()) {
            int[] token = queue.poll();
            if(visitedBike[token[1]] || visitedWorker[token[0]]) {
                continue;
            }
            res[token[1]] = token[0];
            visitedBike[token[1]] = true;
            visitedWorker[token[0]] = true;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] workers = new int[][]{new int[]{0,0}, new int[]{2,1}};
        int[][] bikes = new int[][]{new int[]{1,2}, new int[]{3, 3}};
        Arrays.stream(new Problem1057().assignBikes(workers, bikes)).forEach(System.out::println);
    }
}
