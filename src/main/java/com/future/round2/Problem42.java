package com.future.round2;

/**
 * https://leetcode.com/problems/trapping-rain-water/description/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 * Created by someone on 11/6/17.
 */
public class Problem42 {

    /**
     * Analyze:
     * Think about how can we trap the water, higher left side and right side, lower in the middle.
     * Case 1, [1, 0, 3] 1 cell water can be trapped, move the lower side (here is left) and find the much lower place, the differ is
     * the waters we can trapped.
     * Case 2, [1, 0, 3, 5, 1, 5] we move the low side (here is left), once we found there's higher wall, assign it to left.
     *
     * So, the solution is, we use two pointers, one from left and one from right, and we also use two variables to track the highest left
     * and the highest right.
     * If cur_left is lower than cur_right move cur_left, otherwise move cur_right.
     *
     *  - If the cur_left is lower than highest_left, highest_left - cur_left is how much water we can trapped.
     *      - otherwise, assign cur_left to highest_left.
     *  - Else do same thing for right side
     *
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height == null || height.length < 2) return 0;
        int left = 0, right = height.length - 1;
        int highestLeft = height[left], highestRight = height[right];
        int res = 0;
        while (left < right) {
            if(height[left] < height[right]) {
                if(height[left] < highestLeft) {
                    res += highestLeft - height[left];
                } else {
                    highestLeft = height[left];
                }
                left++;
            } else {
                if (height[right] < highestRight) {
                    res += highestRight - height[right];
                } else {
                    highestRight = height[right];
                }
                right--;
            }
        }
        return res;
    }

    public static int solution2(int[] height) {
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
        System.out.println(solution2(new int[]{2, 0, 2}));
    }
}
