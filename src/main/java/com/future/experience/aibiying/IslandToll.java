package com.future.experience.aibiying;

import java.util.Arrays;
import java.util.PriorityQueue;

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
    public int minToll2(int[][] map, int dest) {
        //int[] -> [island_id, toll]
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
        for(int adj : map[0]) queue.offer(new int[]{adj, adj * adj});
        boolean[] visited = new boolean[map.length];
        visited[0] = true;
        while (!queue.isEmpty()) {
            int[] islandToll = queue.poll();
            if(islandToll[0] == 9) return islandToll[1];
            if(map[islandToll[0]].length < 1) continue; //no further reachable islands
            //update
            for(int adj : map[islandToll[0]]) {
                if(visited[adj]) continue;
                queue.offer(new int[]{adj, Math.abs(adj - islandToll[0]) * Math.abs(adj - islandToll[0]) + islandToll[1]});
                visited[adj] = true;
            }
        }
        return Integer.MAX_VALUE; //unreachable
    }

    public int minToll(int[][] map, int dest) {
        int[] tolls = new int[map.length];
        Arrays.fill(tolls, Integer.MAX_VALUE);
        tolls[0] = 0;
        boolean[] visited = new boolean[map.length];
        int curDest = 0;
        while (true) {
            if(curDest == dest) break;
            visited[curDest] = true;
            //update tolls
            if(map[curDest].length > 0) {
                for(int adj : map[curDest]) {
                    tolls[adj] = Math.min(tolls[adj], Math.abs(adj - curDest) * Math.abs(adj - curDest) + tolls[curDest]);
                }
            }

            //find minimal one
            int min = Integer.MAX_VALUE, minIsland = -1;
            for(int i = 0; i< tolls.length; i++) {
                if(visited[i]) continue;
                if(tolls[i] < min) {
                    min = tolls[i];
                    minIsland = i;
                }
            }
            if(min == Integer.MAX_VALUE) break;
            curDest = minIsland;
        }
        return tolls[dest];
    }

    public static void main(String[] args) {
        int[][] map = new int[10][];
        map[0] = new int[]{1, 2, 3};
        map[1] = new int[]{9};
        map[2] = new int[]{5, 6};
        map[3] = new int[]{};
        map[4] = new int[]{};
        map[5] = new int[]{0, 1};
        map[6] = new int[]{0, 1, 2, 3, 4, 5, 9};
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
        System.out.println(p.minToll(map, 9));
    }
}
