package com.future.experience;

/**
 * Created by xingfeiy on 4/5/18.
 */
public class PrintMatrix {
    public static void printMatrix(int n) {
        if(n < 0) return;
        int[][] matrix = new int[n][n];
        int startRow = 0, startCol = 0, value = 1;
        while (n > 0) {
            int i = 0;
            for(; i < n; i++) matrix[startRow][startCol + i] = value++;
            startCol += (i - 1);
            startRow++;
            for(i = 0; i < n - 1; i++) matrix[startRow + i][startCol] = value++;
            startRow += (i - 1);
            startCol--;
            for(i = 0; i < n - 1; i++) matrix[startRow][startCol - i] = value++;
            startCol -= (n - 1);
            startRow--;
            for(i = 0; i < n - 2; i++) matrix[startRow - i][startCol] = value++;
            startCol++;
            n -= 2;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] +  " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printMatrix(3);
    }

}
