package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给两个数组，两个数组中的元素都是pair<int, int>，数组表示压缩后的一串数字
 * A: [(1, 2), (3,1), (2,3), (3, 1)] 表示 {1, 1, 3, 2, 2, 2, 3}
 * B: [(5, 1), (1,1), (3,4), (2, 1)] 表示 {5, 1, 3, 3, 3, 3, 2}
 * 求A点乘B的结果，以压缩形式的返回。以上面的A，B为例，结果是[(5,1), (1,1), (9,1), (6,4)]
 * 要求不能展开A和B以后做乘法再压缩。
 */
public class CompressionStringMultiplication {
    /**
     * Can we assume the length of A and B are same after decompressed it?
     * @param arrayA
     * @param arrayB
     * @return
     */
    public List<int[]> mult(int[][] arrayA, int[][] arrayB) {
        int pA1 = 0, pA2 = 1, pB1 = 0, pB2 = 1;
        List<int[]> res = new ArrayList<>();
        while (pA1 < arrayA.length && pB1 < arrayB.length) {
            int curRes = arrayA[pA1][0] * arrayB[pB1][0];
            if(res.size() > 0 && res.get(res.size() - 1)[0] == curRes) {
                res.get(res.size() - 1)[1]++;
            } else {
                res.add(new int[]{curRes, 1});
            }
            if(pA2 == arrayA[pA1][1]) {
                pA1++;
                pA2 = 1;
            } else {
                pA2++;
            }

            if(pB2 == arrayB[pB1][1]) {
                pB1++;
                pB2 = 1;
            } else {
                pB2++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arrayA = new int[][]{new int[]{1, 2}, new int[]{3, 1}, new int[]{2, 3}, new int[]{3, 1}};
        int[][] arrayB = new int[][]{new int[]{5, 1}, new int[]{1, 1}, new int[]{3, 4}, new int[]{2, 1}};
        List<int[]> res = new CompressionStringMultiplication().mult(arrayA, arrayB);
        for(int[] pair : res) {
            System.out.println(pair[0] + " , " + pair[1]);
        }
    }
}
