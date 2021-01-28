package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/multiply-strings/
 *
 *
 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

 Example 1:

 Input: num1 = "2", num2 = "3"
 Output: "6"
 Example 2:

 Input: num1 = "123", num2 = "456"
 Output: "56088"
 Note:

 The length of both num1 and num2 is < 110.
 Both num1 and num2 contain only digits 0-9.
 Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.

 */
public class StringMulitply {
    public String multiply(String num1, String num2) {
        if(num1 == null || num1.length() < 1 || num2 == null || num2.length() < 1) return "";
        if(num1.equals("0") || num2.equals("0")) return "0";

        int[] arr = new int[num1.length() + num2.length()];
        for(int i = num1.length() - 1; i >= 0; i--) {
            for(int j = num2.length() - 1; j >= 0; j--) {
                int pos1 = i + j, pos2 = i + j + 1;
                int sum = (num2.charAt(j) - '0') * (num1.charAt(i) - '0') + arr[i + j + 1];
                arr[i + j + 1] = sum % 10;
                arr[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            if(sb.length() == 0 && arr[i] == 0) continue;
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringMulitply p = new StringMulitply();
        System.out.println(p.multiply("0", "0"));
    }
}
