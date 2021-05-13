package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.List;

/**
 * Find local minimum from an array. Local minimum is a number who's neighbor/neighbors are greater than itself.
 * For example, in the following array
 *
 * arr = [2, 5, 9, 3, 4] local minimums are 2 and 3. Return any one local minimum.
 */
public class LocalMinimum {
    public List<Integer> find(int[] arr) {
        List<Integer> res = new ArrayList<>();
        if(arr == null || arr.length < 1) {
            return res;
        }
        for(int i = 0; i < arr.length; i++) {
            int left = i > 0 ? arr[i - 1] : Integer.MAX_VALUE;
            int right = i < arr.length - 1 ? arr[i + 1] : Integer.MAX_VALUE;
            if(arr[i] < right && arr[i] < left) {
                res.add(arr[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new LocalMinimum().find(new int[]{1, 2, 3}).forEach(System.out::println);
        new LocalMinimum().find(new int[]{3, 2, 3}).forEach(System.out::println);
        new LocalMinimum().find(new int[]{1, 3, 2, 3}).forEach(System.out::println);
    }
}
