package com.future.round2;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class Problem84 {
//    public int largestRectangleArea(int[] heights) {
//        int length = heights.length, index = 0, max = 0;
//        //the stack of index
//        Stack<Integer> stack = new Stack<>();
//        while(index < length) {
//            while(!stack.isEmpty() && heights[index] <= heights[stack.peek()]) {
//                int popIndex = stack.pop();
//                max = Math.max(max, heights[index] * (index - popIndex + 1));
//            }
//            stack.push(index++);
//        }
//
//        while(!stack.isEmpty()) {
//            int popIndex = stack.pop();
//            max = Math.max(max, heights[popIndex] * (length - popIndex));
//        }
//        return max;
//    }

    /**
     * For any element, let's say heights[i], how to find the maximum rectangle with height heights[i]?
     * Consider element i as pivot, and extends it to left and right until we find the first element that is smaller than heights[i].
     * Let's use left represents the first element smaller than heights[i] in the left, and use right represents same thing.
     * Then, the area of rectangle = heights[i] * (right - left  - 1)
     *
     * The straightforward way is do same thing to elements one by one, then the time complexity could be O(n^2)
     *
     * How to find the first smaller elements for each one? For element i:
     * - if heights[i] > heights[i - 1], then the i - 1 is the first smaller.
     * - else, the first smaller of i - 1 might be first smaller of i, or might not, so
     *      - if the first smaller of i - 1 is also smaller than i, then it's the first smaller for i too.
     *      - otherwise, try to find the real first smaller further.
     * @param heights
     * @return
     */
    public static int largestRectangleArea2(int[] heights) {
        if(heights == null || heights.length < 1) return 0;
        int[] lessFromLeft = new int[heights.length];
        int[] lessFromRight = new int[heights.length];

        lessFromLeft[0] =  -1;
        for(int i = 1; i < lessFromLeft.length; i++) {
            int firstLess = i - 1;
            while(firstLess >= 0 && heights[firstLess] >= heights[i]) {
                firstLess = lessFromLeft[firstLess];
            }
            lessFromLeft[i] = firstLess;
        }

        lessFromRight[heights.length - 1] = heights.length;
        for(int i = heights.length - 2; i >= 0; i--) {
            int firstLess = i + 1;
            while(firstLess < heights.length &&  heights[firstLess] >= heights[i]) {
                firstLess = lessFromRight[firstLess];
            }
            lessFromRight[i] = firstLess;
        }

        int max = 0;
        for(int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return max;
    }

    public static void main(String[] args) {
        Arrays.stream(new int[]{1, 2, 3, 4}).filter(obj -> obj % 2 == 0).forEach(System.out::println);
        System.out.println(largestRectangleArea2(new int[]{2, 1, 2}));
    }
}
