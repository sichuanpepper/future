package com.future.experience.linying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by xingfeiy on 6/18/18.
 */
public class ClosestBinarySearchTreeValue {
    /**
     * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
     * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
     * Analyze:
     *  - Binary search to find the insert position of x.
     *  - If k is odd, select the elements from both left and right, here, left = right = k / 2
     *  - If k is Even, left = k / 2, right = k / 2 - 1
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        // if x existed in arr, return the index of x in arr.
        // if x doesn't exist in arr, return (-index) - 1, where index is the position of first element greater than x, or length of arr.
        int insertPos = Arrays.binarySearch(arr, x);
        if(insertPos < 0) insertPos = -(insertPos + 1);
        int left = insertPos - 1, right = insertPos;
        while (k-- > 0) {
            if(left < 0 || (right < arr.length && Math.abs(arr[left] - x) > Math.abs(arr[right] - x))) {
                right++;
            } else {
                left--;
            }
        }
        for(int i = left + 1; i <= right; i++) res.add(arr[i]);
        return res;
    }



    public static void main(String[] args) {
        ClosestBinarySearchTreeValue p = new ClosestBinarySearchTreeValue();
    }
}
