package com.future.round2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/largest-number/description/
 *
 Given a list of non negative integers, arrange them such that they form the largest number.

 For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

 Note: The result may be very large, so you need to return a string instead of an integer.


 * Created by xingfeiy on 12/18/17.
 */
public class Problem179 {
    /**
     * Analyze:
     * Let's use a smaller example, 3, 30, 34
     * for 3, 30, the largest number is 330, 3 comes to first
     * then 3 and 34, the largest number is 343, 34 comes to first
     * So for 3, 30, 34, the largest number is 34330.
     *
     * We can defined a comparator and sort it.
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length < 1) return "";
        String[] strNums = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            strNums[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strNums, new Comparator<String>() {
            public int compare(String o1, String o2) {
                String opt1 = o1 + o2;
                String opt2 = o2 + o1;
                return opt2.compareTo(opt1);
//                return String.compare(opt2, opt1);
            }
        });
        //use equals rather than "=="
        if(strNums[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for(String str : strNums) {
            sb.append(str);
        }
        return sb.toString();
    }
}
