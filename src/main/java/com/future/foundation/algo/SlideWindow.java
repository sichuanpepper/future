package com.future.foundation.algo;

/**
 * https://leetcode.com/tag/sliding-window/
 *
 * Sliding window is an algorithm to solve substring/subarray issues, likes longest/smallest substring/subarray with/without [conditions].
 *
 * Solution:
 * Use two pointers(left, right) to represent a window, keep move right pointer, for each move:
 *  - Check if current window meets the conditions or not, likes contains valid ele or the length smaller than a specific length.
 *      - if not meet, move left pointer util the window meet the conditions.
 *  - Check if current window is the result or not.
 *  - Move right pointer and looking for next possible result.
 *
 */
public class SlideWindow {
}
