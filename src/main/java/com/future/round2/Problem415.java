package com.future.round2;

/**
 * https://leetcode.com/problems/add-strings/description/
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:

 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.

 * Created by xingfeiy on 5/20/18.
 */
public class Problem415 {
    public String addStrings(String num1, String num2) {
        if(num1 == null || num1.length() < 1 || num1.equals("0")) return num2;
        if(num2 == null || num2.length() < 1 || num2.equals("0")) return num1;

        String res = "";
        int carry = 0, p1 = num1.length() - 1, p2 = num2.length() - 1;
        while(p1 >= 0 || p2 >= 0 || carry > 0) {
            int val1 = (p1 >= 0) ? num1.charAt(p1--) - '0' : 0;
            int val2 = (p2 >= 0) ? num2.charAt(p2--) - '0' : 0;
            int val = val1 + val2 + carry;
            res = Integer.toString(val % 10) + res;
            carry = val / 10;
        }
        return res;
    }
}
