package com.future.foundation.sort;

import java.util.Arrays;

/**
 * Created by xingfeiy on 5/3/17.
 */
public class HeapSort {
    //Three major steps to implement a heap sort algorithm.
    //1. build heap
    //2. heapify
    //3. heap sort.

    //What is heap data structure?
    // Heap structure is a tree which is full filled on all levels except possibly the lowest(this tree is also known as complete tree)
    // and filled from the left up to the point.

    //The features of heap:
    //1. Heaps satisfy the heap property (for every nodes other than root, the value of node is less than or equals the value of parent,
    // that is, the value of node is at most the value of its parent).
    //2. We use an array A presents a heap, for each node i in array, we can know following things:
    //  index of parent = index of i / 2
    //  index of left child = index of i * 2
    //  index of right child = index of i * 2 + 1
    //3. It's the most important key for building heap, all elements in subarray A[n/2+1...n] are leaves of the tree.

    //Since we need to use heapify function to build a heap, so let's implement heapify first.
    // What's the parameters should be in function heapify? an array for sure, the index of node we are going to heapfy, and another one...
    public static void heapify(int[] array, int n, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        if(left < n && array[left] > array[index]) {
            largest = left;
        }

        if(right < n && array[right] > array[largest]) {
            largest = right;
        }

        if(largest != index) {
            int tmp = array[index];
            array[index] = array[largest];
            array[largest] = tmp;
            heapify(array, n, largest);
        }
    }

    public static void sort(int[] array) {
        // build heap, why end the loop at length / 2 -1, refer to 3rd feature of heap above.
        // and also notice the array is zero-based, that's why length / 2 - 1 here rather than length / 2
        for(int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length ,i);
        }

        //heap sort
        for(int i = array.length - 1; i >=0; i--) {
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;
            heapify(array, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 7, 8, 6, 4};
        sort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }


}
