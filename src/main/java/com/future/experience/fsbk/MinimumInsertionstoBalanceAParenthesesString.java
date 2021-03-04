package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 *
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
 *
 * Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
 * Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
 * In other words, we treat '(' as openning parenthesis and '))' as closing parenthesis.
 *
 * For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
 *
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 *
 * Return the minimum number of insertions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to to add one more ')
 * ' at the end of the string to be "(())))" which is balanced.
 * Example 2:
 *
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 * Example 3:
 *
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 * Example 4:
 *
 * Input: s = "(((((("
 * Output: 12
 * Explanation: Add 12 ')' to balance the string.
 * Example 5:
 *
 * Input: s = ")))))))"
 * Output: 5
 * Explanation: Add 4 '(' at the beginning of the string and one ')' at the end. The string becomes "(((())))))))".
 */
public class MinimumInsertionstoBalanceAParenthesesString {
    /**
     * Analyze:
     * We go through all characters one by one, if
     * - '(', just count it.
     * - ')', we check two situations here:
     *      - the following character, if it's not ')', which means we have to insert a ')'
     *      - the count of '(', if the count < 1, which means we have to insert a '('
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int cntOfLeft = 0, pointer = 0, res = 0;
        while(pointer < s.length()) {
            if(s.charAt(pointer) == '(') {
                cntOfLeft++;
                pointer++;
            } else {
                //try to build two right ')'
                if(pointer + 1 == s.length() || s.charAt(pointer + 1) != ')') {
                    res++;
                    pointer++;
                } else {
                    pointer += 2;
                }

                if(cntOfLeft < 1) {
                    res++;
                } else {
                    cntOfLeft--;
                }
            }
        }
        return res + cntOfLeft * 2;
    }
}
