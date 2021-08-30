package com.future.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array, find all local peaks.
 *
 * Thoughts:
 * - It could be super easy if there's no continuous same numbers, just go through all number one by one and check its left and right.
 */
public class LocalPeak {
    /**
     * The simple case
     * @param array
     * @return
     */
    public static List<Integer> getLocalPeaksWithoutDup(int[] array) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < array.length; i++) {
            boolean isSmallLeft =  i == 0 ? true : array[i - 1] < array[i];
            boolean isSmallRight = i == array.length - 1 ? true : array[i] > array[i + 1];
            if(isSmallLeft && isSmallRight) {
                res.add(array[i]);
            }
        }
        return res;
    }

    /**
     * If there are multiple same numbers on the peak?
     * We care the previous small number and next small number.
     * @param array
     * @return
     */
    public static List<Integer> getLocalPeaks(int[] array) {
        List<Integer> res = new ArrayList<>();
        int p = 0;
        while (p < array.length) {
            boolean isSmallLeft = p == 0 ? true : array[p - 1] < array[p];

            int tmp = p;
            while (tmp < array.length && array[tmp] == array[p]) {
                tmp++;
            }
            boolean isSmallRight = tmp == array.length || array[tmp] < array[p];
            if(isSmallLeft && isSmallRight) {
                res.add(array[p]);
            }
            p = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getLocalPeaksWithoutDup(new int[]{3, 2, 5, 6, 2, 1, 7}));

        System.out.println(getLocalPeaks(new int[]{2, 2, 1, 3, 3, 4, 5, 5, 2}));
        System.out.println(getLocalPeaks(new int[]{2, 2, 1, 3, 3, 4, 5, 5, 2, 6}));
    }
}
