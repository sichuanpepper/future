package com.future.experience.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xingfeiy on 5/20/18.
 */
public class DotProduct {
    /**
     * The dot product of two sparse matrix, let's say we have matrix A[m, k] and B [k, n], where
     * m is the rows of A, k is the columns of A and rows of B, n is columns of B, A * B will generate
     * a matrix C[m, n].
     *
     * For each element in C, we will have:
     * C[m][n] = A[m][k1] * B[k1][n] + A[m][k2] * B[k2][n] + ... + A[m][k] * B[k][n]
     *
     * Store:
     * How to store a matrix efficiently?
     * One way is use array, example:
     * |2|0|0|
     * |0|3|0|
     * |0|0|0|
     *
     *    r:|0|1|
     * c(k):|0|1|
     *    v:|2|3|
     *
     * So, for two big sparse matrix dot product, we can pre-transform 2d matrix to smaller one.
     * @param a
     * @param b
     * @return
     */
    public int sparseMatrixProduct(int[][] a, int[][] b) {
        List<SMElement> aElements = new ArrayList<>();
        List<SMElement> bElements = new ArrayList<>();
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                if(a[i][j] != 0) aElements.add(new SMElement(i, j, a[i][j]));
            }
        }

        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[0].length; j++) {
                if(b[i][j] != 0) bElements.add(new SMElement(i, j, b[i][j]));
            }
        }

        //sort first list by column
        Collections.sort(aElements, new Comparator<SMElement>() {
            @Override
            public int compare(SMElement o1, SMElement o2) {
                return o1.col - o2.col;
            }
        });

        //sort second list by row
        Collections.sort(bElements, new Comparator<SMElement>() {
            @Override
            public int compare(SMElement o1, SMElement o2) {
                return o1.row - o2.row;
            }
        });

        int p1 = 0, p2 = 0, res = 0;
        while (p1 < aElements.size() && p2 < bElements.size()) {
            if(aElements.get(p1).col == bElements.get(p2).row) {
                res += aElements.get(p1).val * bElements.get(p2).val;
                p1++;
            } else if(aElements.get(p1).col > bElements.get(p2).row) {
                p2++;
            } else {
                p1++;
            }
        }
        return res;
    }

    private class SMElement{
        public int row;
        public int col;
        public int val;

        public SMElement(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}
