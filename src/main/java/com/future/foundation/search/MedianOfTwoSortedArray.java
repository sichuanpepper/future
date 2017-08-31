package com.future.foundation.search;

/**
 * Created by xingfeiy on 3/30/17.
 *
 * There are two sorted array A and B of size m and n respectively, find the median of the two sorted array.
 *
 * Time complexity should be O(lg(m+n))
 *
 * A=[1, 2, 3, 4, 5, 6], B=[2, 3, 4, 5], the median is 3.5
 * A=[1, 2, 3], B=[4, 5], the median is 3
 */
public class MedianOfTwoSortedArray {

    public static float findMedian(int[] a, int[] b) {
        //what does median means? similar to the question which find the Kth elements in two ordered array, here k = (a.length + b.length) / 2
        //But the situation is different for even or odd.
        int totalLength = a.length + b.length;
        if(totalLength % 2 == 0) {
            return (float)(findKth(a, b, 0, 0, totalLength / 2) + findKth(a, b, 0, 0, totalLength / 2 - 1)) / 2;
        } else {
            return findKth(a, b, 0, 0, totalLength / 2);
        }
    }

    public static int findKth(int[] a, int[] b, int aStart, int bStart, int k) {
        //when to end the recursion?
        //array a and array b are final, and will not be changed, so we don't care about that.
        //think about variable aStart and bStart...
        //and k...
        if(aStart >= a.length) { //The Kth element must be array b
            return b[bStart + k];
        }

        //Same thing for array b
        if(bStart >= b.length) {
            return a[aStart + k];
        }

        //what value of k could be? k/2, k/4..., k should be equal or bigger than 1, otherwise doesn't make sense.
        if(k == 1) {
            return Math.min(a[aStart], b[bStart]);
        }

        //pay attention, k/2 may not exist in either a or b.
        int hkfa = aStart + k/2 >= a.length ? Integer.MAX_VALUE : a[aStart + k/2];
        int hkfb = bStart + k/2 >= b.length ? Integer.MAX_VALUE : b[bStart + k/2];
        if(hkfa <= hkfb) {
            return findKth(a, b, aStart + k/2, bStart, k/2);
        } else {
            return findKth(a, b, aStart, bStart + k/2, k/2);
        }
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6};
        int[] b = new int[]{2, 3, 4, 5};
        System.out.println("Find 10th: " + findKth(a, b, 0, 0, 10));
        System.out.println("Find 5th: " + findKth(a, b, 0, 0, 5));
        System.out.println("Find 3th: " + findKth(a, b, 0, 0, 3));
        System.out.println("Find 1th: " + findKth(a, b, 0, 0, 1));
    }
}
