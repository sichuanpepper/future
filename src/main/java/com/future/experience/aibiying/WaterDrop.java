package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by xingfeiy on 6/14/18.
 */
public class WaterDrop {
    /**
     * Let's make some assumptions first
     * - There are an infinity high walls in left most and right most.
     * - Each drop go down first, then left, and right.
     * @param heights
     * @param water
     * @param location
     */
    public void pourWater(int[] heights, int water, int location) {
        int max = 0;
        for(int h : heights) max = Math.max(max, h);
        char[][] matrix = new char[max + 5][heights.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = ' ';
            }
        }
        for(int j = 0; j < matrix[0].length; j++) {
            int drops = heights[j];
            for(int i = matrix.length - 1; i >= 0 && drops > 0; i--, drops--) {
                matrix[i][j] = '+';
            }
        }
        DisplayUtils.printTwoDimensionsArray(matrix);

        //now let's define the rules
        // - first rule, if water is able to go down, go down
        // - for the start point, if water is able to go left, go left, otherwise, go right.
        // - if the water goes left, and arrived a position where can not go left anymore, just stay there.
        // - if the water goes right, and arrived a position where can not go right anymore, stay there.
        int locationHeight = matrix.length - heights[location] - 1;
        while (water-- > 0) {
            if(location > 0 && matrix[locationHeight][location - 1] == ' ') {
                goLeft(matrix, locationHeight, location - 1);
            } else if(location < matrix[0].length - 1 && matrix[locationHeight][location + 1] == ' ') {
                goRight(matrix, locationHeight, location + 1);
            } else {
                matrix[locationHeight--][location] = 'W';
            }
        }
        System.out.println("==========================");
        DisplayUtils.printTwoDimensionsArray(matrix);
    }

    private void goLeft(char[][] matrix, int x, int y) {
        if(x < matrix.length - 1 && matrix[x + 1][y] == ' ') {
            goLeft(matrix, x + 1, y);
        } else if(y > 0 && matrix[x][y - 1] == ' ') {
            goLeft(matrix, x, y - 1);
        } else {
            matrix[x][y] = 'W';
        }
    }

    private void goRight(char[][] matrix, int x, int y) {
        if(x < matrix.length - 1 && matrix[x + 1][y] == ' ') {
            goRight(matrix, x + 1, y);
        } else if(y < matrix[0].length - 1 && matrix[x][y + 1] == ' ') {
            goRight(matrix, x, y + 1);
        } else {
            matrix[x][y] = 'W';
        }
    }

    public static void main(String[] args) {
        WaterDrop w = new WaterDrop();
        int[] heights = new int[]{4, 2, 0, 3, 3, 1, 3, 1, 4};
        w.pourWater(heights, 19, 3);
    }
}
