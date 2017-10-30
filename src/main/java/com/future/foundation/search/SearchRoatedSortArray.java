package com.future.foundation.search;

/**
 * Created by someone on 3/28/17.
 */
public class SearchRoatedSortArray {
    public static final int[] rotatedArray = new int[]{4, 5, 7, 0, 1, 2};

    public static final int[] rotatedArray2 = new int[]{4, 5, 7, 8, 10, 11};

    public static int findMiniElement(int[] rotatedArray) {
        if(rotatedArray == null || rotatedArray.length < 1) {
            return -1;
        }

        int start = 0;
        int end = rotatedArray.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //Why we compare mid to end? start may not exist.
            // 4, 5, 6, 7(mid), 1, 2, 3 mid is greater than start, the min is on the right.
            // 1, 2, 3, 4(mid), 5, 6, 7 mid is greater than start, the min is on the left.
            //4, 5, 6, 7(mid), 1, 2, 3 mid is less than end,
            //1, 2, 3, 4(mid), 5, 6, 7 mid is less than end, the min must be the left.
            if(rotatedArray[mid] < rotatedArray[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return rotatedArray[start] < rotatedArray[end] ? start : end;
    }

    public static int findElement(int[] rotatedArray, int value) {
        if(rotatedArray == null || rotatedArray.length < 1) {
            return -1;
        }

        int start = 0;
        int end = rotatedArray.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if(rotatedArray[mid] < rotatedArray[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        int miniPos = rotatedArray[start] < rotatedArray[end] ? start : end;

        start = 0;
        end = rotatedArray.length - 1;
        if(value == rotatedArray[miniPos]) {
            return miniPos;
        } else if(value < rotatedArray[miniPos]) {
            return -1;
        } else if(value <= rotatedArray[end]) {
            start = miniPos;
        } else {
            end = miniPos - 1;
        }

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if(rotatedArray[mid] == value) {
                return mid;
            } else if(rotatedArray[mid] < value) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(rotatedArray[start] ==  value) {
            return start;
        }
        if(rotatedArray[end] == value) {
            return end;
        }
        return -1;
    }

    public static int findElementS2(int[] rotatedArray, int value) {
        if(rotatedArray == null || rotatedArray.length < 1) {
            return -1;
        }

        int start = 0;
        int end = rotatedArray.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if(rotatedArray[mid] ==  value) {
                return mid;
            } else if(rotatedArray[mid] < value) {
                start = mid;
            } else {
                if(rotatedArray[start] < value) {
                    end = mid;
                } else if(rotatedArray[start] == value) {
                    return start;
                } else {
                    start = mid;
                }
            }
        }

        if(rotatedArray[start] == value) {
            return start;
        }

        if(rotatedArray[end] == value) {
            return end;
        }

        return -1;
    }

    public static void main(String[] args) {
//        System.out.println("Minimum element is in " + findMiniElement(rotatedArray));
//        System.out.println("Minimum element is in " + findMiniElement(rotatedArray2));
//        System.out.println("========================");
//        System.out.println("The giving element is in " + findElement(rotatedArray, 1));
//        System.out.println("The giving element is in " + findElement(rotatedArray, 4));
//        System.out.println("The giving element is in " + findElement(rotatedArray2, 4));
//        System.out.println("The giving element is in " + findElement(rotatedArray2, 11));
//        System.out.println("The giving element is in " + findElement(rotatedArray2, 1));
//        System.out.println("========================");
//        System.out.println("The giving element is in " + findElementS2(rotatedArray, 1));
//        System.out.println("The giving element is in " + findElementS2(rotatedArray, 4));
//        System.out.println("The giving element is in " + findElementS2(rotatedArray2, 4));
//        System.out.println("The giving element is in " + findElementS2(rotatedArray2, 11));
//        System.out.println("The giving element is in " + findElement(new int[]{6, 7, 1, 2, 3, 4, 5}, 7));
        System.out.println(findMiniElement(new int[]{1, 2, 3, 4}));
        System.out.println(findMiniElement(new int[]{1, 2}));
        System.out.println(findMiniElement(new int[]{3, 4, 1, 2}));
        System.out.println(findMiniElement(new int[]{4, 1, 2, 3}));
    }
}
