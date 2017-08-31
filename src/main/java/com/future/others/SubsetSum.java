package com.future.others;

import java.util.Arrays;

/**
 * Given a set of non-negative integers, and a value sum,
 * determine if there is a subset of the given set with sum equal to given sum.
 * Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 *
 * Please use 3 different solutions to solve it.
 *
 * Created by xingfeiy on 8/1/17.
 */
public class SubsetSum {
    //solution 1, brute force way, recursive.
    // time complexity O(2^n)
    public boolean solution1(int[] nums, int sum) {
        if(nums == null || nums.length < 1 || sum < 0) {
            return false;
        }

        return helper1(nums, 0, sum);
    }

    private boolean helper1(int[] nums, int start, int sum) {
        if(sum == 0) {
            return true;
        }

        if(sum < 0 || start == nums.length) {
            return false;
        }

        return helper1(nums, start + 1, sum) || helper1(nums, start + 1, sum - nums[start]);
    }

    //solution 2, backtrack
    //time complexity O(n!)
    public boolean solution2(int[] nums, int sum) {
        if(nums == null || nums.length < 1 || sum < 0) {
            return false;
        }

        return helper2(nums, 0, sum);
    }

    private boolean helper2(int[] nums, int start, int sum) {
        if(sum == 0) {
            return true;
        }

        for(int i = start; i < nums.length; i++) {
            if(nums[i] <= sum) {
                sum -= nums[i];
                if(helper2(nums, start + 1, sum)) {
                    return true;
                }
                sum += nums[i];
            } else {
                if(helper2(nums, start + 1, sum)) {
                    return true;
                }
            }
        }
        return false;
    }

    //solution 3, improved backtrack
    public boolean solution3(int[] nums, int sum) {
        if(nums == null || nums.length < 1 || sum < 0) {
            return false;
        }

        Arrays.sort(nums);
        return helper3(nums, 0, sum);
    }

    private boolean helper3(int[] nums, int start, int sum) {
        if(sum < 0) {
            return false;
        }

        if(sum == 0) {
            return true;
        }

        for(int i = start; i < nums.length; i++) {
            if(nums[i] <= sum) {
                sum -= nums[i];
                if(helper3(nums, start + 1, sum)) {
                    return true;
                }
                sum += nums[i];
            }
        }
        return false;
    }

    //solution 4, dp
    //time complexity O(n^2)
    public boolean solution4(int[] nums, int sum) {
        if(nums == null || nums.length < 1 || sum < 0) {
            return false;
        }

        //every element can be chose or just ignore, we use f(n, s) represent if there's subset with sum equal to given sum.
        //so f(n, s) = f(n - 1, s) || f(n - 1, s - nums[n]);

        //all values are default by false.
        boolean[][] res = new boolean[nums.length + 1][ sum + 1];

        //init, if the sum is 0
        for(int i = 0; i < nums.length + 1; i++) {
            res[i][0] = true;
        }

        for(int i = 1; i < nums.length + 1; i++) {
            for(int j = 1; j < sum + 1; j++) {
                res[i][j] = res[i - 1][j];

                if(nums[i - 1] > j) {
                    continue;
                }

                res[i][j] = res[i - 1][j] || res[i - 1][j - nums[i - 1]];

            }
        }
        return res[nums.length][sum];

    }

    public static void main(String[] args) {
        int[] set1 = new int[]{3, 34, 4, 12, 5, 2};
        System.out.println(new SubsetSum().solution1(set1, 9));
//        System.out.println(new SubsetSum().solution2(set1, 9));
        System.out.println(new SubsetSum().solution3(set1, 9));
        System.out.println(new SubsetSum().solution4(set1, 9));

        System.out.println(new SubsetSum().solution1(set1, 13));
//        System.out.println(new SubsetSum().solution2(set1, 13));
        System.out.println(new SubsetSum().solution3(set1, 13));
        System.out.println(new SubsetSum().solution4(set1, 13));
    }

}
