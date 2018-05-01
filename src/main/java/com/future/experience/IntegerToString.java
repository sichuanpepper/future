package com.future.experience;

/**
 * https://leetcode.com/problems/integer-to-english-words/description/
 *
 Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

 For example,
 123 -> "One Hundred Twenty Three"
 12345 -> "Twelve Thousand Three Hundred Forty Five"
 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 * Created by xingfeiy on 4/30/18.
 */
public class IntegerToString {
    private static final String ZERO = "Zero";

    private static final String HUNDRED = " Hundred ";

    private static final String[] KEY_WORDS_1 = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
    "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};

    private static final String[] KEY_WORDS_2 = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private static final String[] KEY_WORDS_3 = new String[]{"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        if(num == 0) return ZERO;
        String res = "";
        int index = 0;
        while (num > 0) {
            String tmp  = threeDigitsToString(num % 1000);
            if(tmp.length() > 1) {
                res = (tmp + " " + KEY_WORDS_3[index] + " " + res).trim();
            }
            index++;
            num = num / 1000;
        }
        return res;
    }

    private String twoDigitsToString(int num) {
        if(num == 0) return "";
        if(num < 21) return KEY_WORDS_1[num];
        return (KEY_WORDS_2[num / 10] + " " + KEY_WORDS_1[num % 10]).trim();
    }

    private String threeDigitsToString(int num) {
        if(num < 100) return twoDigitsToString(num);
        return (KEY_WORDS_1[num / 100] + HUNDRED + twoDigitsToString(num % 100)).trim();
    }

    public static void main(String[] args) {
        IntegerToString p = new IntegerToString();
        System.out.println(p.numberToWords(0));
        System.out.println(p.numberToWords(1));
        System.out.println(p.numberToWords(10));
        System.out.println(p.numberToWords(14));
        System.out.println(p.numberToWords(20));
        System.out.println(p.numberToWords(25));
        System.out.println(p.numberToWords(30));
        System.out.println(p.numberToWords(35));
        System.out.println(p.numberToWords(100));
        System.out.println(p.numberToWords(108));
        System.out.println(p.numberToWords(818));
        System.out.println(p.numberToWords(1000));
        System.out.println(p.numberToWords(10080));
        System.out.println(p.numberToWords(1000000));
        System.out.println(p.numberToWords(1020000));
        System.out.println(p.numberToWords(10000000));
        System.out.println(p.numberToWords(100000000));
        System.out.println(p.numberToWords(1000000001));
    }
}
