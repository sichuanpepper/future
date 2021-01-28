package com.future.experience.gugou;

import com.future.utils.DisplayUtils;

import java.util.PriorityQueue;

/**
 * 第三轮Coding: 给定一个2D矩NxN，每一行每一列都分别升序排列，返回前k个最小数
 * E.g:
 * 1 3 5 6
 * 2 5 6 7
 * 7 9 10 11
 * 9 10 15 17
 *
 * K = 5 -> [1,2,3,5,5]
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * todo: binary serach?
 */
public class GetSmallestKNumber {
    public int[] findFirstKNumbers(int[][] matrix, int k) {
        //assume the inputs are always valid.
        //int[0]: number, int[1]: row, int[2]: col
        // priority queue sorted by number
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)-> Integer.compare(a[0], b[0]));
        for(int i = 0; i < matrix.length && i < k; i++) {
            queue.offer(new int[]{matrix[i][0], i, 0});
        }

        int[] res = new int[k];
        int index = 0;
        while (!queue.isEmpty() && k-- > 0) {
            int[] pair = queue.poll();
            res[index++] = pair[0];
            if(pair[2] < matrix[0].length - 1) {
                queue.offer(new int[]{matrix[pair[1]][pair[2] + 1], pair[1], pair[2] + 1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                new int[]{1, 3,  5,  6},
                new int[]{2, 5,  6,  7},
                new int[]{7, 9,  10, 11},
                new int[]{9, 10, 15, 17}
        };
        GetSmallestKNumber p = new GetSmallestKNumber();
        DisplayUtils.printArray(p.findFirstKNumbers(matrix, 2));
        DisplayUtils.printArray(p.findFirstKNumbers(matrix, 5));
        DisplayUtils.printArray(p.findFirstKNumbers(matrix, 25));
    }
}
