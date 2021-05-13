package com.future.experience.fsbk.eley;

import com.future.utils.TreeNode;

/**
 * https://www.lintcode.com/problem/construct-binary-tree-from-string/
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 *
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 * There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 * An empty tree is represented by "" instead of "()".
 * Example
 * Example 1:
 *
 * Input: "-4(2(3)(1))(6(5))"
 * Output: {-4,2,6,3,1,5}
 * Explanation:
 * The output is look like this:
 *       -4
 *      /  \
 *     2    6
 *    / \   /
 *   3   1 5
 *
 * Thoughts:
 * - It's easy to see the string is the tree which stored by pre-order
 * - For pre-order, the order structure looks like root, left sub-tree, right sub-tree
 * - The solution could be, from left to right,
 *  - build root
 *  - build left sub tree, the key is where to stop it.
 *  - then build right sub tree
 */
public class ConstructBinaryTreefromString {
    private int curPos = 0;
    /**
     * @param s: a string
     * @return: a root of this tree
     */
    public TreeNode str2tree(String s) {
        if(curPos >= s.length()) {
            return null;
        }
        //find root val
        int start = curPos;
        while (curPos < s.length() && (s.charAt(curPos) == '-' || Character.isDigit(s.charAt(curPos))) ) {
            curPos++;
        }
        //curPos points to first parenthesis or end of s
        if(start == curPos) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(start, curPos)));

        //if the following char is '(', which means it has children, otherwise no children
        if(curPos >= s.length() && s.charAt(curPos) == ')') {
            curPos++;
            return root;
        }

        //curPos is left parenthesis, build left sub-tree
        curPos++;
        root.left = str2tree(s);
        if(curPos >= s.length() || s.charAt(curPos) == ')') {
            curPos++;
            return root;
        }

        curPos++;
        root.right = str2tree(s);
        if(curPos >= s.length() || s.charAt(curPos) == ')') {
            curPos++;
        }
        return root;
    }
}
