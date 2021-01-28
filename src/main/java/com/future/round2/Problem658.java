package com.future.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if(arr == null || arr.length < 1) return res;
        int lower = 0, upper = arr.length;
        if(k >= arr.length) {
            upper = arr.length;
        } else if(x < arr[0]) {
            upper = k;
        } else if(x > arr[arr.length - 1]) {
            lower = arr.length - k;
        } else {
            int insertPos = Arrays.binarySearch(arr, x);
            insertPos = insertPos < 0 ? (-insertPos - 1) : insertPos;
            lower = Math.max(0, insertPos - k);
            upper = Math.min(arr.length - 1, insertPos + k);
            while (upper - lower >= k - 1) {
                if((x - arr[lower]) > (arr[upper] - x)) {
                    upper--;
                } else {
                    lower++;
                }
            }
        }

        for(int i = lower; i < upper; i++) res.add(arr[i]);
        return res;
    }


    public static void main(String[] args) {
        Problem658 p = new Problem658();
        //[0,1,1,1,2,3,6,7,8,9]
        //9
        //4
//        System.out.println(p.findClosestElements(new int[]{0,1,1,1,2,3,6,7,8,9}, 9, 4));
        //[1,1,2,3,3,3,4,6,8,8]
        //6
        //1
//        System.out.println(p.findClosestElements(new int[]{1,1,2,3,3,3,4,6,8,8}, 6, 1));
        //[0,0,1,2,3,3,4,7,7,8]
        //3
        //5
//        System.out.println(Arrays.binarySearch(new int[]{2, 2, 2, 2, 2}, 2));
        System.out.println(p.findClosestElements(new int[]{0,0,1,2,3,3,4,7,7,8}, 3, 5));
    }
}
