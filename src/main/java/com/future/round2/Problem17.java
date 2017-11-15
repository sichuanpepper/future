package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * Given a digit string, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below.

 * Created by someone on 11/14/17.
 */
public class Problem17 {
    /**
     * Analyze:
     * It's a DFS problem, it's just like a tree, and return all paths from root to leaf.
     * @param digits
     * @return
     */
    private static final String[] STRINGS = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() < 1) return res;
        helper(digits, 0, res, "");
        return res;
    }

    private void helper(String digits, int start, List<String> res, String curStr) {
        if(start >= digits.length()) {
            res.add(curStr);
            return;
        }
        for(char ch : STRINGS[digits.charAt(start) - '0'].toCharArray()) {
            helper(digits, start + 1, res, curStr + ch);
        }
    }

    public static void main(String[] args) {
        Problem17 p = new Problem17();
        //1. null string
        DisplayUtils.printList(p.letterCombinations(""));

        //2. others
        DisplayUtils.printList(p.letterCombinations("0"));
        DisplayUtils.printList(p.letterCombinations("01"));
        DisplayUtils.printList(p.letterCombinations("0101"));
        DisplayUtils.printList(p.letterCombinations("23"));
    }
}
