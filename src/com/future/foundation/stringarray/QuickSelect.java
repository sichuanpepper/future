package com.future.foundation.stringarray;

/**
 * Created by xingfeiy on 4/27/17.
 */
public class QuickSelect {
    //first, let's recall quick sort here again
    public static int[] quickSort(int[] array) {
        if(array == null || array.length < 2) {
            return array;
        }
        //
        return quickSort(array, 0, array.length - 1);
    }

    //recursive sort
    public static int[] quickSort(int[] array, int start, int end) {
        //what's the condition to end the recursion
        if(start >= end) {
            return array;
        }

        int partition = partition(array, start, end);

        quickSort(array, start, partition - 1);
        quickSort(array, partition + 1, end);
        return array;
    }

    public static int partition(int[] array, int start, int end) {
        int pivot = array[end]; // remember, choosing pivot is sensitive.
        // the first pointer which means the last elements which less than or equals pivot, the position p1 + 1 is where the pivot goes.
        // the default value is start - 1, just think about what's gonna happen if the pivot is the smallest one?
        int p1 = start - 1;
        // the second pointer traverses the array to find the element which less than or equals pivot, and then swap with p1 + 1
        // p1 moves to p1 + 1, now p1 has new position where still be last one which less than or equals pivot.
        // and the meantime, p2 keep moving to find next element and do same thing, until p2 < end. (remember the end one is pivot)
        int p2 = start;
        // is it possible that the p1 is moving faster than p2?
        // the answer is impossible since p2 moves 1 step in each loop, but p1 not.
        while (p2 < end) {
            if(array[p2] <=  pivot) {
                int tmp = array[++p1];
                array[p1] = array[p2];
                array[p2] = tmp;
            }
            p2++;
        }
        array[end] = array[++p1];
        array[p1] = pivot;
        return p1;
    }

    // now, let's talk about quick select.
    // the simple way is sort the array by quick sort and then return the k - 1 element, the time complexity is O(nlgn)
    // but we can optimize this algorithm little bit, doesn't like quick sort, we just need take care one side of the partition.
    // all the elements in the left side of partition must smaller than or equals partition
    // all the elements in the right side of partition must larger than partition.
    // which means, if the index of partition is bigger than or equal to k, the selected number must be left side,
    // we can ignore right now totally, otherwise we can ignore left side.

    public static int select(int[] array, int k) {
        return select(array, 0, array.length - 1, k);
    }

    public static int select(int[] array, int start, int end, int k) {
        // is it possible the start is larger than end?
        // first the corner case, if there's only one element in the array => impossible.
        // look into the code, only the partition equals end, the start can be bigger than end, but if that happen,
        // which means there's no kth element in array, the k is larger than array length.
        if(start >= end) {
            return array[start];
        }

        int partition = partition(array, start, end);
        if(partition + 1 == k) {
            return array[partition];
        } else if(partition + 1 < k) {
            return select(array, partition + 1, end, k);
        } else {
            return select(array, start, partition - 1, k);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 7, 8, 6, 4};
        quickSort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        array = new int[]{5, 2, 7, 8, 6, 4};
        System.out.println(select(array, 3));
        System.out.println(select(array, 2));
        System.out.println(select(array, 1));
        System.out.println(select(array, 5));
    }

}
