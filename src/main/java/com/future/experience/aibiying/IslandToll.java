package com.future.experience.aibiying;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by xingfeiy on 6/14/18.
 */
public class IslandToll {
    /**
     * There are 10 islands represent by number 0 to 9, and each island connects with several islands,
     * which represents by a 2d array with row length 10.
     * The toll between any two islands is the square of the absolute difference of island's number.
     * ex, the toll between island 1 and island 4 is (4 - 1) ^ 2
     *
     * Find the minimal toll from island 0 to island 9.
     *
     * Analyze:
     * The map actually is a graph, and it's a dijkstra problem.
     * - step1, init toll array from island 0.
     * - step2, find the island that can be reached with minimal toll, then update toll array by this island.
     * @param map
     * @return
     */
//    public int minToll2(int[][] map) {
//        //int[] -> [island_id, toll]
//        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
//        for(int adj : map[0]) queue.offer(new int[]{adj, adj * adj});
//        boolean[] visited = new boolean[map.length];
//        visited[0] = true;
//        while (!queue.isEmpty()) {
//            int[] islandToll = queue.poll();
//            if(islandToll[0] == 9) return islandToll[1];
//            if(map[islandToll[0]].length < 1) continue; //no further reachable islands
//            //update
//            for(int adj : map[islandToll[0]]) {
//                if(visited[adj]) continue;
//                queue.offer(new int[]{adj, Math.abs(adj - islandToll[0]) * Math.abs(adj - islandToll[0]) + islandToll[1]});
//                visited[adj] = true;
//            }
//        }
//        return Integer.MAX_VALUE; //unreachable
//    }

    /**
     * dijkstra solution
     * - Init toll array with max integer except first element
     * - Add first one into queue
     * - Update reachable elements
     * @param map
     * @return
     */
    public int minToll3(int[][] map) {
        int[] tolls = new int[10];
        for(int i = 1; i < 10; i++) tolls[i] = Integer.MAX_VALUE;

        //array includes 2 elements, array[0] the number of island, array[1], the min cost so far.
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            for(int neighbor : map[tmp[0]]) {
                int cost = Math.abs(neighbor - tmp[0]) * Math.abs(neighbor - tmp[0]) + tmp[1];
                if(tolls[neighbor] > cost) {
                    tolls[neighbor] = cost;
                    queue.offer(new int[]{neighbor, cost});
                }
            }
        }
        return tolls[9];
    }

    public int minTollDFS(int[][] map) {
        helper(map, 0, 0, new boolean[10]);
        return min;
    }
    private int min = Integer.MAX_VALUE;
    private void helper(int[][] map, int cur, int curToll, boolean[] visited) {
        if(cur == 9) {
            min = Math.min(min, curToll);
            System.out.println("Found a way, cost: " + curToll);
            return;
        }
        if(visited[cur]) return;
        visited[cur] = true;
        for(int neighbor : map[cur]) {
            int cost = Math.abs(neighbor - cur) * Math.abs(neighbor - cur);
            helper(map, neighbor, curToll + cost, visited);
        }
    }

    public static void main(String[] args) {
        int[][] map = new int[10][];
        map[0] = new int[]{1, 2, 3};
        map[1] = new int[]{0};
        map[2] = new int[]{0, 5, 6};
        map[3] = new int[]{};
        map[4] = new int[]{};
        map[5] = new int[]{0, 1};
        map[6] = new int[]{0, 1, 2, 3, 4, 5, 8};
        map[7] = new int[]{};
        map[8] = new int[]{};
        map[9] = new int[]{};
//        map[2] = new int[]{3, 5, 9};
//        map[3] = new int[]{};
//        map[4] = new int[]{1, 2, 3};
//        map[5] = new int[]{1, 2, 3};
//        map[6] = new int[]{1, 2, 3};
//        map[7] = new int[]{1, 2, 3};
//        map[8] = new int[]{1, 2, 3};
//        map[9] = new int[]{1, 2, 3};

        IslandToll p = new IslandToll();
        System.out.println(p.minToll3(map));
        System.out.println(p.minTollDFS(map));
    }
}
