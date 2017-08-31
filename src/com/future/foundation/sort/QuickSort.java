package com.future.foundation.sort;

import java.util.Arrays;

/**
 * Created by xingfeiy on 4/11/17.
 */
public class QuickSort {
    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    public static int[] sort(int[] array, int start, int end) {
        if(start >= end) {
            return array;
        }
        int pivot = partition(array, start, end);

        sort(array, start, pivot - 1);
        sort(array, pivot + 1, end);
        return array;
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = end;
        int p1 = start - 1;
        int p2 = start;
        while (p2 < pivot) {
            if(array[p2] < array[pivot]) {
                int tmp = array[++p1];
                array[p1] = array[p2];
                array[p2++] = tmp;
            } else {
                p2++;
            }
        }

        int tmp = array[++p1];
        array[p1] = array[pivot];
        array[pivot] = tmp;
        return p1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 7, 8, 6, 4};
        sort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
