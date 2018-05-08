package com.future.round2;

/**
 * https://leetcode.com/problems/valid-number/description/
 *
 Validate if a given string is numeric.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

 * Created by xingfeiy on 4/9/18.
 */
public class Problem65 {
    /**
     * Analyze: the key point of this issue is how to define a valid number..
     * Ask questions first:
     *  - Can we consider decimal number only?
     *      - contains char between ['0', '9']
     *  - 32 bits or 64 bits?
     *  - What are the number types we have to care about? Integer, Float, Double?
     *      - max length of string? or max value and min value
     *  - Positive && Negative
     *      - contains '+', '-'
     *  - Float
     *      - contains '.'
     *  - Scientific notion
     *      - contains 'e', 'E'
     *  - constraint
     *      - Start with:
     *          - digit, '+', '-'
     *          - '.' ?
     *      - End with:
     *          - digit
     *          - '.' ?
     *      - Appearance
     *          - '.', only 1, and behind digit
     *          - '+', '-' only 1 and must be first position
     *          - 'e', 'E' only one
     *
     * 1 => true  decimal
     * 01 => octal number
     * ox145 => hexadecimal number
     * oxFFFF => hexadecimal number
     * 010101 => true? binary
     * +1 => true
     * -1 => true
     * 0.1 => true
     * 0.1000 => true
     * 0.0 => true
     * .1 => true
     * .0 => true
     * +0.1 => true
     * -0.1 => true
     * +.0 => true
     * -.0 => true
     * 1.2345e4 => true  scientific notion
     * 1.2345E4 => true  scientific notion
     * +1.2345e4 => true  scientific notion
     * -1.2345e4 => true  scientific notion
     *
     * 1234l => true
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if(s == null || s.length() < 1) return false;
        if(!validStartAndEnd(s)) return false;
        int[] counter = new int[3]; //0 -> '.', 1 -> '+'/'-', 2 -> 'e'/'E'
        for(int i = 0; i < s.length(); i++) {

        }

        return true;
    }

    private boolean validPrefixChar(char pre, char cur) {
        if(isDigit(pre)) return true;
        return true;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean validStartAndEnd(String s) {
        return s.startsWith("+") || s.startsWith("-") || (s.charAt(0) >= '0' && s.charAt(0) <= 9);
    }

    private boolean isValid(char ch) {
        return (ch >= '0' && ch <= '9') || (ch == 'E') || (ch == 'e') || (ch == '+') || (ch == '-');
    }
}
