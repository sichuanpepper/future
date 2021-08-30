package com.future.generic;

/**
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Thoughts:
 * - Any two elements can represent a container, the total water can contain depends on the smaller element.
 * - We are looking for two elements a, b and maximize min(a, b) * (idx of b - idx of a)
 * - We can initial the container from leftmost and rightmost, and narrow down the window, we always move the smaller one
 *
 *     [0,1,2,3,4,5,6,7,8]
 * ex: [1,8,6,2,5,4,8,3,7]
 *
 *
 * Takeaways:
 * Basically it's a sub-window issue, we want to maximize the sub-window, we start it from full window and narrow down it.
 * The key point here is how to narrow down, smaller side always makes the sub-window smaller during narrow down, so we discard the smaller one.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if(height == null || height.length < 2) {
            return 0;
        }
        int left = 0, right = height.length - 1, max = 0;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
