package com.future.foundation.search;

/**
 * Created by xingfeiy on 3/27/17.
 */
public class BinarySearch {
    public static final int[] array = new int[]{1, 3, 4, 6, 8, 10, 13};

    public static final int[] array_with_dup = new int[]{1, 3, 4, 4, 4, 6, 8, 8, 10, 13};

    public static final int[] null_array = null;

    public static final int[] empty_array = new int[1];

    public static final int[] length_one_array = new int[]{1};


    //start 7:52 03/27/2017
    //end 7:58
    //First test: Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 7
    //error 1: mid >= start, wrong, modified the condition as start <= end, passed the basic testing.
    //But is this the best solution?
    public static int search(int[] array, int value) {
        if(array == null) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(array[mid] == value) {
                return mid;
            } else if(array[mid] < value) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static int preferSolution(int[] array, int value) {
        if(array == null || array.length < 1) {
            return -1;
        }

        int start = 0;
        int end = array.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(array[mid] == value) {
                return mid;
            } else if(array[mid] < value) {
                //can we add 1 like what we did in above solution?
                //If there's no duplicated element and just want to check if the value exists in array or not, add 1 is fine.
                //But, sometime, add 1 doesn't work, for example, if we have given array with duplicated elements and we want to find the
                //first position, like array 1, 2, 5, 5, 6, we want to find the position of first 5.
//                start = mid + 1;
                start = mid;
            } else {
                end = mid;
            }
        }
        if(array[start] == value) {
            return start;
        }

        if(array[end] == value) {
            return end;
        }
        return -1;
    }

    public static int findFirst(int[] array, int value) {
        if(array == null || array.length < 1) {
            return -1;
        }

        int start = 0;
        int end = array.length;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if(array[mid] == value) {
                end = mid;
            } else if(array[mid] < value) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if(array[start] == value) {
            return start;
        }

        if(array[end] == value) {
            return end;
        }
        return  -1;
    }

    public static void main(String[] args) {
        System.out.println(preferSolution(array, 3));
        System.out.println(preferSolution(array, 13));
        System.out.println(preferSolution(array, 1));
        System.out.println(preferSolution(array, 19));
        System.out.println(preferSolution(array, -1));
        System.out.println(preferSolution(null_array, -1));

        System.out.println("================================");

        //1, 3, 4, 4, 4, 6, 8, 8, 10, 13
        System.out.println(findFirst(array_with_dup, 3));
        System.out.println(findFirst(array_with_dup, 4));
        System.out.println(findFirst(array_with_dup, 8));

    }
}
