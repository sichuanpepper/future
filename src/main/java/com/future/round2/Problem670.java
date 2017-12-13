package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-swap/description/

 Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
 Return the maximum valued number you could get.

 Example 1:
 Input: 2736
 Output: 7236
 Explanation: Swap the number 2 and the number 7.
 Example 2:
 Input: 9973
 Output: 9973
 Explanation: No swap.
 Note:
 The given number is in the range [0, 10^8]

 *
 * -- facebook
 *
 * Created by someone on 11/21/17.
 */
public class Problem670 {
    public int maximumSwap(int num) {
        if(num < 10) return num;
        int[] array = new int[10];
        Arrays.fill(array, -1);
        int index = 9;
        while (num > 0) {
            array[index--] = num % 10;
            num = num / 10;
        }
        for(int i = 0; i < array.length; i++) {
            if(array[i] == -1) continue;
            int larger = array.length - 1;
            for(int j = array.length - 1; j > i; j--) {
                if(array[j] > array[larger]) larger = j;
            }
            if(array[i] < array[larger]) {
                int tmp = array[i];
                array[i] = array[larger];
                array[larger] = tmp;
                break;
            }
        }
        int res = 0;
        index = 0;
        while (index < array.length) {
            if(array[index] != -1) {
                res = res * 10 + array[index];
            }
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        Problem670 p = new Problem670();
        System.out.println(p.maximumSwap(1234));
        System.out.println(p.maximumSwap(4321));
        System.out.println(p.maximumSwap(2736));
    }

//    public int maximumSwap(int num) {
//        char[] digits = Integer.toString(num).toCharArray();
//
//        int[] buckets = new int[10];
//        for (int i = 0; i < digits.length; i++) {
//            buckets[digits[i] - '0'] = i;
//        }
//
//        for (int i = 0; i < digits.length; i++) {
//            for (int k = 9; k > digits[i] - '0'; k--) {
//                if (buckets[k] > i) {
//                    char tmp = digits[i];
//                    digits[i] = digits[buckets[k]];
//                    digits[buckets[k]] = tmp;
//                    return Integer.valueOf(new String(digits));
//                }
//            }
//        }
//
//        return num;
//    }
}
