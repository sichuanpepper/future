package com.future.experience.gugou;


public class MaxPathInMatrix {
    /**
     * 给一个matrix，每个row只能选一个数，如果选第i行第m个元素，第i+1行第n个元素，那这两个元素之间有penalty：abs(m - n); 让求最后最大和是多少。
     *
     * 1,  2, 5
     * 2,  2, 4
     * 20, 1, 2
     *
     * 1 row, the maximum sum is itself.
     * 2 row, for each element, we compute the result with the elements in the above row one by one.
     *      (1 + 2) - (0 - 0) = 3
     *      (2 + 2) - (1 - 0) = 3
     *      (2 + 5) - (2 - 0) = 5
     *      ...
     * The time complexity is O(m * n * n)
     * 1, 2, 5 -> 1, 2, 5 -> 3, 4, 5
     *
     * @param matrix
     * @return
     */
    public int maxSum(int[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int res = Integer.MIN_VALUE;
        int[] preRow = maxArray(matrix[0]);

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                preRow[j] += matrix[i][j];
            }
            if(i < matrix.length - 1) {
                preRow = maxArray(preRow);
            }
        }
        for(int i = 0; i < preRow.length; i++) res = Math.max(res, preRow[i]);

        return res;
    }

    public int[] maxArray(int[] array) {
        int[] maxArray = new int[array.length];
        maxArray[0] = array[0];
        for(int i = 1; i < maxArray.length; i++) {
            maxArray[i] = Math.max(maxArray[i - 1] - 1, array[i]);
        }
        for(int i = maxArray.length - 2; i >= 0; i--) {
            maxArray[i] = Math.max(maxArray[i + 1] - 1, maxArray[i]);
        }
        return maxArray;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1, 2, 5},
                new int[]{2, 2, 4},
                new int[]{20, 1, 2}
        };

        int[][] matrix1 = new int[][]{
                new int[]{1, 2, 3}
        };

        int[][] matrix2 = new int[][]{
                new int[]{-1, -2, -3},
                new int[]{-5, -2, -4}
        };

        MaxPathInMatrix p = new MaxPathInMatrix();
        System.out.println(p.maxSum(matrix));
        System.out.println(p.maxSum(matrix1));
        System.out.println(p.maxSum(matrix2));

//        Arrays.stream(p.maxArray(new int[]{3, 1, 4})).forEach(System.out::println);

    }

}
