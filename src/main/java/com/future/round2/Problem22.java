package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]

 * Created by someone on 10/25/17.
 */
public class Problem22 {
    /**
     * Analyze:
     * 1. For each result, each position has two choices, '(' and ')'
     * 2. Once the number of ')' greater than '(', this combination is invalid.
     * 3. The length of string equals 2 * n and number of '(' equals number of ')', a valid result is generated.
     * 4. Backtracking algorithm is applied.
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if(n < 1) {
            return res;
        }

        helper(n, 0, 0, "", res);
        return res;
    }

    public static void helper(int n, int numsOfLeft, int numsOfRight, String curStr, List<String> res) {
        if(numsOfRight > numsOfLeft || numsOfLeft > n || numsOfRight > n) {
            return;
        }
        if(numsOfLeft == n && numsOfLeft == numsOfRight) {
            res.add(curStr);
            return;
        }

        //for each position, there are two options
        helper(n, numsOfLeft + 1, numsOfRight, curStr + "(", res);
        helper(n, numsOfLeft, numsOfRight + 1, curStr + ")", res);
    }

    public static void main(String[] args) {
        DisplayUtils.printList(generateParenthesis(0));
        DisplayUtils.printList(generateParenthesis(1));
        DisplayUtils.printList(generateParenthesis(2));
        DisplayUtils.printList(generateParenthesis(5));
    }
}
