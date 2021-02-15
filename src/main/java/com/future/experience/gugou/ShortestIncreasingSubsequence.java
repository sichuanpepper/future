package com.future.experience.gugou;

import java.util.Arrays;

/**
 *    n = 4
 *    [2, 1, 4, 2, 1, 2, 3, 1, 4]
 *    return 5
 *    1,2,3,1,4 -> 最短子序列从1到4 (n=4)
 *    给一个n，和 array of int，求包含1到n的最短子序。不会有负数，肯定有valid subsequence
 *    Solution:
 *    Maintain an array of length n, number[n] represents the last index of number n;
 *    initial fill number array with -1, number[0] = 0;
 *
 *    For each element i in given array:
 *    if ele equals to 1: set or update index.
 *
 *    else if(number[array[i] - 1] >= 0) number[array[i]] = number[array[i] - 1] ;
 *
 *    [-1, -1, -1, -1, -1]
 *    2: number[1] == -1, skip
 *    1: set index, [-1, 0, -1, -1, -1]
 *    4: number[3] == -1, skip
 *    2: number[1] == 0, [-1, 0, 0, -1, -1]
 *    1: set index. [-1, 4, 0, -1, -1]
 *    2: number[1] = 4, update [-1, 4, 4, -1, -1]
 *    3: number[2] = 4, update [-1, 4, 4, 4, -1]
 *    1: set index, [-1, 7, 4, 4, -1]
 *    4: find target, check if number[3] if number[3] >=0, it's an candidate, otherwise skip it.
 */
public class ShortestIncreasingSubsequence {
    public int solution(int[] array, int n) {
        if(array == null || array.length < 1 || n < 1) return 0;
        int[] indexOfNum = new int[n + 1];
        Arrays.fill(indexOfNum, -1);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == 1) {
                indexOfNum[1] = i;
            } if(array[i] > n) {
                continue;
            }else if(indexOfNum[array[i] - 1] >= 0) {
                indexOfNum[array[i]] = indexOfNum[array[i] - 1];
                if(array[i] == n) {
                    min = Math.min(min, i - indexOfNum[n] + 1);
                    System.out.println(indexOfNum[n] + " to " + i);
                    Arrays.stream(Arrays.copyOfRange(array, indexOfNum[n], i + 1)).forEach(System.out::println);
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ShortestIncreasingSubsequence p = new ShortestIncreasingSubsequence();
        p.solution(new int[]{2, 1, 4, 2, 1, 2, 3, 1, 4}, 1);
    }
}
