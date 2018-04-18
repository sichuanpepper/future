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
        return true;
    }
}
