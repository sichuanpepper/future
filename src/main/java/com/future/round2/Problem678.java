package com.future.round2;

/**
 * https://leetcode.com/problems/valid-parenthesis-string/description/
 Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string
 is valid. We define the validity of a string by these rules:

 Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 Any right parenthesis ')' must have a corresponding left parenthesis '('.
 Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 An empty string is also valid.
 Example 1:
 Input: "()"
 Output: True
 Example 2:
 Input: "(*)"
 Output: True
 Example 3:
 Input: "(*))"
 Output: True
 Note:
 The string size will be in the range [1, 100].
 * Created by xingfeiy on 8/7/18.
 */
public class Problem678 {
    /**
     * Analyze:
     * The problem can be imagined as a tree, for example (*)
     *              (
     *           /  |  \
     *          )   ''  )
     *         /    |    \
     *        )     )    )
     *  Still remember that how to check valid string without character '*'? Just use a count, so the straightforward way
     *  is do same thing for DFS search.
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        return checkValidStringHelper(s, 0, 0);
    }

    private boolean checkValidStringHelper(String s, int cur, int count) {
        if(cur >= s.length()) return count == 0;
        if(count < 0) return false;
        if(s.charAt(cur) == '(') {
            return checkValidStringHelper(s, cur + 1, count + 1);
        } else if(s.charAt(cur) == ')') {
            return checkValidStringHelper(s, cur + 1, count - 1);
        }
        return checkValidStringHelper(s, cur + 1, count) || checkValidStringHelper(s, cur + 1, count + 1) ||
                checkValidStringHelper(s, cur + 1, count - 1);
    }


}
