package com.future.round2;

import com.future.utils.DisplayUtils;

/**
 * https://leetcode.com/problems/plus-one/description/
 *
 Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

 You may assume the integer do not contain any leading zero, except the number 0 itself.

 The digits are stored such that the most significant digit is at the head of the list.

 * Created by xingfeiy on 12/9/17.
 */
public class Problem66 {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length < 1) return digits;
        int carry = 0;
        int index = digits.length - 1;
        while (index >= 0) {
            int num = digits[index] + (index == digits.length - 1 ? 1 : 0) + carry;
            digits[index--] = num % 10;
            carry = num / 10;
        }
        if(carry > 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            System.arraycopy(digits, 0, res, 1, digits.length);
            return res;
        }
        return digits;
    }

    public static void main(String[] args) {
        Problem66 p = new Problem66();
        DisplayUtils.printArray(p.plusOne(new int[]{1, 0}));
    }
}
