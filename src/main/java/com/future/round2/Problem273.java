package com.future.round2;

/**
 * https://leetcode.com/problems/integer-to-english-words/description/
 *
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

 For example,
 123 -> "One Hundred Twenty Three"
 12,345 -> "Twelve Thousand Three Hundred Forty Five"
 1,234,567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 -- add by myself
 110,234,567 -> "One Hundred Ten Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 1,110,234,567 -> "One Billion One Hundred Ten Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 0 -> "Zero"
 10 -> "Ten"
 11 -> "Eleven"
 12 -> "Twelve"
 13 -> "Thirteen"
 14 -> "Fourteen"
 ...
 20 -> "Twenty"
 ...
 30 -> "Thirty"
 ...
 100 -> "One Hundred"
 200 -> "Two Hundred"
 ...
 1,000 -> "One Thousand"
 1,001 -> "One Thousand One"

 Questions:
 012 is valid???

 * Created by someone on 11/14/17.
 */
public class Problem273 {
    /**
     * Analyze:
     * 1. the given integer can be divided into sub units where each unit contains 3 digits.
     * 2. Convert sub unit to English word and add corresponding suffix likes thousand or million...
     * @param num
     * @return
     */
    private static final String[] Level_1 = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] Level_2 = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] Level_2_1 = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] Level_3 = new String[]{"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if(num < 10) return Level_1[num];
        int index = 0;
        String res = "";
        while (num > 0) {
            int tmp = num % 1000;
            if(tmp != 0) res = (threeDigitsConvert(tmp) + " " + Level_3[index] + " " + res).trim();
            index++;
            num = num / 1000;
        }
        return res.trim();
    }

    private String threeDigitsConvert(int num) {
        String res = "";
        if(num > 99) {
            res = Level_1[num / 100] + " Hundred";
            num %= 100;
        }
        if(num >= 20) {
            res = (res + " " + Level_2[num / 10] + " ").trim();
            num %= 10;
        } else if(num > 9) {
            return (res + " " + Level_2_1[num - 10]).trim();
        }
        if(num == 0) return res;
        return (res + " " +Level_1[num]).trim();
    }

    public static void main(String[] args) {
        Problem273 p = new Problem273();
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
