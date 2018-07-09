package com.future.experience.aibiying;

import java.util.LinkedList;
import java.util.Queue;

/**
 An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

 Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor,
 "flood fill" the image.

 To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel
 of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color
 as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

 At the end, return the modified image.

 Example 1:
 Input:
 image = [[1,1,1],[1,1,0],[1,0,1]]
 sr = 1, sc = 1, newColor = 2
 Output: [[2,2,2],[2,2,0],[2,0,1]]
 Explanation:
 From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 by a path of the same color as the starting pixel are colored with the new color.
 Note the bottom corner is not colored 2, because it is not 4-directionally connected
 to the starting pixel.
 Note:

 The length of image and image[0] will be in the range [1, 50].
 The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 The value of each color in image[i][j] and newColor will be an integer in [0, 65535].

 * Created by xingfeiy on 6/21/18.
 */
public class FloodFill {
//    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
//        helper(image, sr, sc, image[sr][sc], newColor, new boolean[image.length][image[0].length]);
//        return image;
//    }
//
//    private void helper(int[][] image, int sr, int sc, int oldColor, int newColor, boolean[][] visited) {
//        if(sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length
//                || image[sr][sc] != oldColor || visited[sr][sc]) return;
//        image[sr][sc] = newColor;
//        visited[sr][sc] = true;
//        helper(image, sr - 1, sc, oldColor, newColor, visited);
//        helper(image, sr + 1, sc, oldColor, newColor, visited);
//        helper(image, sr, sc - 1, oldColor, newColor, visited);
//        helper(image, sr, sc + 1, oldColor, newColor, visited);
//    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image == null || image.length < 1) return image;
        helper(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void helper(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if(sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || oldColor == newColor) return;
        if(image[sr][sc] != oldColor) return;
        image[sr][sc] = newColor;
        helper(image, sr - 1, sc, oldColor, newColor);
        helper(image, sr + 1, sc, oldColor, newColor);
        helper(image, sr, sc - 1, oldColor, newColor);
        helper(image, sr, sc + 1, oldColor, newColor);
    }

    public int[][] floodFillBFS(int[][] image, int sr, int sc, int newColor) {
        if(image == null || image.length < 1) return image;
        if(sr < 0 || sc >= image.length || sc < 0 || sc >= image[0].length) return image;
        if(image[sr][sc] == newColor) return image;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        int oldColor = image[sr][sc];
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            image[curPos[0]][curPos[1]] = newColor;
            if(curPos[0] > 0 && image[curPos[0] - 1][curPos[1]] == oldColor)
                queue.offer(new int[]{curPos[0] - 1, curPos[1]});
            if(curPos[0] < image.length - 1 && image[curPos[0] + 1][curPos[1]] == oldColor)
                queue.offer(new int[]{curPos[0] + 1, curPos[1]});
            if(curPos[1] > 0 && image[curPos[0]][curPos[1] - 1] == oldColor)
                queue.offer(new int[]{curPos[0], curPos[1] - 1});
            if(curPos[1] < image[0].length - 1 && image[curPos[0]][curPos[1] + 1] == oldColor)
                queue.offer(new int[]{curPos[0], curPos[1] + 1});
        }
        return image;
    }
}
