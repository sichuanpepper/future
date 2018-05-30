package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/the-skyline-problem/description/
 *
 * Created by xingfeiy on 5/17/18.
 */
public class Problem218 {
    private class BuildingCorner implements Comparable<BuildingCorner> {
        public int x;

        public int y; //height

        public boolean left = true;

        public BuildingCorner(int x, int y, boolean left) {
            this.x = x;
            this.y = y;
            this.left = left;
        }

        //
        public int compareTo(BuildingCorner that) {
            if(this.x == that.x && this.y == that.y) return this.left ? 1 : -1;
            //sort the height in descending order.
            if(that.x == this.x) return that.y - this.y;
            //sort x in ascending order.
            return this.x - that.x;
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if(buildings == null || buildings.length < 1) return res;
        List<BuildingCorner> bcs = new ArrayList<>();
        for(int[] b : buildings) {
            bcs.add(new BuildingCorner(b[0], b[2], true));
            bcs.add(new BuildingCorner(b[1], b[2], false));
        }

        Collections.sort(bcs);

        //LinkedList doesn't offer comparator.
        Queue<Integer> heights = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        heights.offer(0);
        int preHeight = 0;
        for(BuildingCorner bc : bcs) {
            if(bc.left) {
                heights.offer(bc.y);
            } else {
                heights.remove(bc.y);
            }

            //since for same x, the heights are sorted in descending order, the first one must be the highest one.
            int curHeight = heights.peek();
            if(curHeight != preHeight) {
                res.add(new int[]{bc.x, curHeight});
                preHeight = curHeight;
            }
        }
        return res;
    }

    public List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(int[] h:height) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if(prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] building = new int[2][];
        building[0] = new int[]{0, 2, 3};
        building[1] = new int[]{2, 5, 3};
        Problem218 p = new Problem218();
        List<int[]> res = p.getSkyline2(building);
    }
}
