package com.future.experience.aibiying;

import java.util.Stack;

/**
 * Created by xingfeiy on 6/14/18.
 */
public class WaterDrop {
    public void pourWater(int[] heights, int water, int location) {}

    private void findSlots(int[] heights, int location, Stack<int[]> left, Stack<int[]> right) {
        int x = location, y = heights[location] + 1;
        left.push(new int[]{x, y});
        while (x >= 0 && y <= heights[location] + 1) {

        }
    }

    public static void main(String[] args) {

    }
}
