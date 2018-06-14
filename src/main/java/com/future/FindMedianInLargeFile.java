package com.future;

/**
 * Created by xingfeiy on 6/13/18.
 */
public class FindMedianInLargeFile {
    /**
     * Imagine the given array is the integers in a large file, we can't load it into memory to sort it, find the median
     * number in this large file.
     * <p>
     * Analyze:
     * Think about how to find median number in unsorted array?
     *
     * @param nums
     * @return
     */
    public double findMedian(int[] nums) {
        return findHelper(nums, Integer.MIN_VALUE, Integer.MAX_VALUE, nums.length / 2);
    }

    private double findHelper(int[] nums, long startBound, long endBound, int k) {
        if(startBound >= endBound) return startBound;
        long guess = startBound + (endBound - startBound) / 2;
        int leftCount = 0, leftMax = Integer.MIN_VALUE, rightMin = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= guess) {
                leftCount++;
                leftMax = Math.max(leftMax, n);
            } else {
                rightMin = Math.min(rightMin, n);
            }
        }

        if (leftCount == k) return leftMax;
        if (leftCount < k) {
            return findHelper(nums, Math.max(leftMax + 1, guess), endBound, k);
        } else {
            return findHelper(nums, startBound, leftMax, k);
        }

    }

    /**
     * Analyze:
     * Quick select, we are going to find the element in array which smaller than (k - 1) elements.
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargerNum(int[] nums, int k) {
        return helper(nums, 0, nums.length - 1, k);
    }

    private int helper(int[] nums, int start, int end, int k) {
        if (start > end) return Integer.MIN_VALUE;
        int p = partition(nums, start, end);
        if (end - p + 1 == k) {
            return nums[p];
        } else if (end - p + 1 < k) {
            return helper(nums, start, p - 1, k - (end - p + 1));
        }
        return helper(nums, p + 1, end, k);
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int p1 = start - 1, p2 = start;
        while (p2 < end) {
            if (nums[p2] < pivot) {
                int tmp = nums[++p1];
                nums[p1] = nums[p2];
                nums[p2++] = tmp;
            } else {
                p2++;
            }
        }

        nums[end] = nums[++p1];
        nums[p1] = pivot;
        return p1;
    }

    private long search(int[] nums, int k, long left, long right) {
        if (left >= right) {
            return left;
        }
        long res = left;
        long guess = left + (right - left) / 2;
        int count = 0;
        for (int num : nums) {
            if (num <= guess) {
                count++;
                res = Math.max(res, num);
            }
        }
        if (count == k) {
            return res;
        } else if (count < k) {
            return search(nums, k, Math.max(res + 1, guess), right);
        } else {
            return search(nums, k, left, res);
        }
    }

    public double findMedian2(int[] nums) {
        int len = 0;
        for (int num : nums) {
            len++;
        }
        if (len % 2 == 1) {
            return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE,
                    Integer.MAX_VALUE);
        } else {
            return (double) (search(nums, len / 2, Integer.MIN_VALUE,
                    Integer.MAX_VALUE) +
                    search(nums, len / 2 + 1, Integer.MIN_VALUE,
                            Integer.MAX_VALUE)) / 2;
        }
    }


    public static void main(String[] args) {
        FindMedianInLargeFile p = new FindMedianInLargeFile();
        System.out.println(p.findKthLargerNum(new int[]{4, 2, 3, 5}, 1));
        System.out.println(p.findKthLargerNum(new int[]{4, 2, 3, 5}, 2));
        System.out.println(p.findKthLargerNum(new int[]{4, 2, 3, 5}, 3));
        System.out.println(p.findKthLargerNum(new int[]{4, 2, 3, 5}, 4));

        System.out.println("Find Median in large file....");
        System.out.println(p.findMedian2(new int[]{4, 2, 3, 4, 5, 6})); //4.0
        System.out.println(p.findMedian2(new int[]{4, 2, 3, 5, 6}));  //4.0
        System.out.println(p.findMedian2(new int[]{4, 2, 3, 5, 6, 3, 2, 4}));  //3.5
        System.out.println(p.findMedian(new int[]{4, 2, 3, 4, 5, 6})); //4.0
        System.out.println(p.findMedian(new int[]{4, 2, 3, 5, 6}));  //4.0
        System.out.println(p.findMedian(new int[]{4, 2, 3, 5, 6, 3, 2, 4}));  //3.5
    }
}
