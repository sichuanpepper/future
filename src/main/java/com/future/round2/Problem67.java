package com.future.round2;

/**
 * https://leetcode.com/problems/add-binary/description/
 *
 Given two binary strings, return their sum (also a binary string).

 For example,
 a = "11"
 b = "1"
 Return "100".

 * Created by xingfeiy on 12/9/17.
 */
public class Problem67 {
    public String addBinary(String a, String b) {
        if(a == null || a.length() < 1) return b;
        if(b == null || b.length() < 1) return a;
        int pa = a.length() - 1;
        int pb = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (pa >= 0 || pb >= 0 || carry > 0) {
            int val = (pa >= 0 ? a.charAt(pa--) - '0' : 0) + (pb >= 0 ? b.charAt(pb--) - '0' : 0) + carry;
            sb.append(val % 2);
            carry = val / 2;
        }
        return sb.reverse().toString();
    }
}
