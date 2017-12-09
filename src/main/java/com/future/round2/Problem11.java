package com.future.round2;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.
 * Created by someone on 12/08/17.
 */
public class Problem11 {
    /**
     * Analyze:
     *  (ai)
     *   |
     *   |
     *   |     |
     *   |   | | |
     *  0|_|_|_|_|_________(i)
     *
     *   As the graph above, the most water for any two lines, which equals min(Am, An) * abs(m - n)
     *   We can use two pointers, one start from left and one from right.
     *   compute the size of container which form by left and right "wall", then
     *   if left is higher than right, move right side
     *   else move left.
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if(height == null || height.length < 2) return 0;
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
            if(height[left] > height[right]) right--;
            else left++;
        }
        return max;
    }
}
