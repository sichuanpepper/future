package com.future.experience;

/**
 * Created by xingfeiy on 4/5/18.
 */
public class PrintMatrix {
    public static void printMatrix(int n) {
        if(n < 0) return;
        int[][] matrix = new int[n][n];
        int curRow = 0, curCol = 0, value = 1;
        int steps = n;
        while (steps > 0) {
            for(int i = 0; i < steps && curCol < n; i++) matrix[curRow][curCol++] = value++;
            curRow++;
            curCol--;

            for(int i = 0; i < steps - 2 && curRow < n; i++) matrix[curRow++][curCol] = value++;

            for(int i = 0; i < steps && curCol >= 0; i++) matrix[curRow][curCol--] = value++;
            curCol++;
            curRow--;

            for(int i = 0; i < steps - 2 && curRow >= 0; i++) matrix[curRow--][curCol] = value++;
            curRow++;

            steps = steps - 2;
            curCol++;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] +  ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        printMatrix(3);
        printMatrix(1);
    }

}
