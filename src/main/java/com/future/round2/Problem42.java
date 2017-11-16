package com.future.round2;

/**
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * Created by someone on 11/6/17.
 */
public class Problem42 {
    public static int trap(int[] height) {
        int res = 0;
        while(true) {
            int tmp = helper(height);
            if(tmp == -1) break;
            res += tmp;
            for(int i = 0; i < height.length; i++) {
                height[i]  = height[i] > 0 ? height[i] - 1 : 0;
            }
        }
        return res;
    }

    //
    private static int helper(int[] height) {
        int start = -1, end = 0, blocks = 0;
        for(int i = 0; i < height.length; i++) {
            if(height[i] > 0) {
                start = (start == -1) ? i : start;
                end = i;
                blocks++;
            }
        }
        return (blocks < 2) ? -1 : (end - start + 1) - blocks;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{2, 0, 2}));
    }
}
