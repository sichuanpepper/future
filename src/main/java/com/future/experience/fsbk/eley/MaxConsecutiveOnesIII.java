package com.future.experience.fsbk.eley;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {
//    public static int longestOnes(int[] nums, int k) {
//        int p1 = 0, p2 = 0, res = 0, curCnt = 0;
//        while(p2 < nums.length) {
//            if(nums[p2] == 0) {
//                curCnt++;
//            }
//            if(curCnt > k) {
//                curCnt -= (nums[p1++] == 0 ? 1 : 0);
//            } else {
//                res = Math.max(res, p2 - p1 + 1);
//                p2++;
//            }
//        }
//        return res;
//    }

    /**
     * For each move, two situations here:
     *  - if the current ele is 1, it never makes current window to be invalid.
     *  - if the current ele is 0, check if the current window still valid, if not, make it to be valid.
     *  Then, we calculate the length of current window and update the max
     *
     * @param nums
     * @param k
     * @return
     */
    public static int longestOnes(int[] nums, int k) {
        int p1 = 0, p2 = 0, res = 0, curCnt = 0;
        while(p2 < nums.length) {
            if(nums[p2] == 0) {
                curCnt++;
                if(curCnt > k) { //current window is invalid, make it valid
                    while(curCnt > k) {
                        curCnt -= (nums[p1++] == 0 ? 1 : 0);
                    }
                }
            }
            res = Math.max(res, p2 - p1 + 1);
            p2++;

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
    }
}
