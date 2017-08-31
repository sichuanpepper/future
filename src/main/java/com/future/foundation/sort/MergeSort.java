package com.future.foundation.sort;

import java.util.Arrays;

/**
 * Created by xingfeiy on 5/6/17.
 */
public class MergeSort {
    //first of all, the merge sort is a divide and conquer algorithm, so let's see how to implement the 3 methods of D & C.
    //1. Divide -> likes the binary search, an array A[l..r] can be divide into two array left[l...m], right[m+1...r], m is the middle point.
    //2. Conquer -> if there's only one element in array, which means this array is sorted.
    //3. Merge -> merge is always the core implementation, here we have to merge two unsorted array into a sorted array,
    // how? we need to renew two arrays to copy data from left and right sub array then compare the values and insert back to original array.


    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    public static int[] sort(int[] array, int start, int end) {
        if(start >= end) {
            return array;
        }
        int mid = start + (end - start) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);

        return merge(array, start, mid, end);
    }

    public static int[] merge(int[] array, int start, int mid, int end) {
        //first, we have to allocate two new temporary arrays for left && right sub array copies.
        //don't forget the function copyOfRange, The value at original[from] is placed into the initial
        // element of the copy (unless from == original.length or from == to).
        int[] left = Arrays.copyOfRange(array, start, mid + 1);
        int[] right = Arrays.copyOfRange(array, mid + 1, end + 1);

        //now,let's refill the range from start to end orderly.
        int pl = 0;
        int pr = 0;
        //let's take this for loop carefully, the pl or pr could exceed the length of array anytime during the refilling.
        // if pl exceed the length of left array, which means all elements in left array have been refilled into original array,
        // then just refill the rest elements in right array one by one. same thing for pr.
        // otherwise, pick up the minimum element between left and right and refill into original array.
        for(int i = start; i <= end; i++) {
            if(pl >= left.length) {
                array[i] = right[pr++];
            } else if(pr >= right.length) {
                array[i] = left[pl++];
            } else {
                if(left[pl] < right[pr]) {
                    array[i] = left[pl++];
                } else {
                    array[i] = right[pr++];
                }
            }
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 7, 8, 6, 4};
        sort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
