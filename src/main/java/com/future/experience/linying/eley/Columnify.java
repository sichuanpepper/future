package com.future.experience.linying.eley;

import com.future.utils.DisplayUtils;

/**
 * Question Description: Given a sequence and a number of columns, print it in columns of (nearly) equal length.
 * Thus, given (1, 2, 3, 4, 5, 6, 7) and two columns, your program should output
 *
 * 1 5
 * 2 6
 * 3 7
 * 4
 *
 * Followup questions: Add spacing: Given (1, 2, 3, 4, 5, 100, 7), output
 *
 * 1   5
 * 2 100
 * 3   7
 * 4
 */

public class Columnify {
    public int[][] print(int[] array, int col) {
        int row = array.length / col + (array.length % col > 0 ? 1 : 0);
        int[][] res = new int[row][col];
        for(int i = 0; i < array.length; i++) {
            res[i % row][i / row] = array[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Columnify p = new Columnify();
        DisplayUtils.printTwoDimensionsArray(p.print(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 2));
        DisplayUtils.printTwoDimensionsArray(p.print(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3));
        DisplayUtils.printTwoDimensionsArray(p.print(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 4));
    }
}
