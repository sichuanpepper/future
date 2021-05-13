package com.future.lintcode;

import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/3sum-closest/
 *
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers.
 *
 * Example
 * Example 1:
 *
 * Input:[2,7,11,15],3
 * Output:20
 * Explanation:
 * 2+7+11=20
 * Example 2:
 *
 * Input:[-1,2,1,-4],1
 * Output:2
 * Explanation:
 * -1+2+1=2
 * Challenge
 * O(n^2) time, O(1) extra space
 *
 * Notice
 * You may assume that each input would have exactly one solution.
 */
public class P59 {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target: An integer
     * @return: return the sum of the three integers, the sum closest target.

    Sort array in acending order
    Go throught ele one by one, for each ele at postion p1:
    - find the two elements in left sub array(p1 + 1, end), one start from p1 + 1, and one start from end
    - if sum(p1, p2, p3) == target, return it.
    - if sum(p1, p2, p3) > target, get diff and update diff, p3--
    - if sum(p1, p2, p3) < target, get diff and update diff, p2++

    TC: O(n^2)
    SC: O(1)
     */
    public int threeSumClosest(int[] numbers, int target) {
        Arrays.sort(numbers);
        int diff = Integer.MAX_VALUE, res = 0;
        for(int i = 0; i < numbers.length - 2; i++) {
            if(i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }

            int left = i + 1, right = numbers.length - 1;
            while(left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if(Math.abs(sum - target) < diff) {
                    res = sum;
                    diff = Math.abs(sum - target);
                }

                if(sum == target) {
                    return target;
                } else if(sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
