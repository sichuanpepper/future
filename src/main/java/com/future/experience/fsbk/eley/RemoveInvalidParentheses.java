package com.future.experience.fsbk.eley;

import java.util.*;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * Example 3:
 *
 * Input: s = ")("
 * Output: [""]
 *
 * Thoughts:
 * - The string contains parentheses and letters
 * - A straightforward way is generate all sub sets and return all valid sub-sets with minimum removals.
 * - Since we want to find the minimum results, BFS is applied.
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String str = queue.poll();
                if(visited.contains(str)) {
                    continue;
                }
                visited.add(str);
                if(isValid(str)) {
                    res.add(str);
                }

                if(res.size() == 0) {
                    for(String child : generateChildren(str)) {
                        queue.offer(child);
                    }
                }
            }
            if(res.size() > 0) {
                break;
            }
        }
        return res;
    }

    private List<String> generateChildren(String s) {
        if(s == null || s.length() < 1) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            if(i > 0 && s.charAt(i) == s.charAt(i - 1) || Character.isLetter(s.charAt(i))) {
                continue;
            }

            res.add(s.substring(0, i) + s.substring(i + 1));
        }
        return res;
    }

    private boolean isValid(String str) {
        int cntOfRight = 0;
        for(char ch : str.toCharArray()) {
            if(ch == '(') {
                cntOfRight++;
            } else if(ch == ')') {
                cntOfRight--;
            }

            if(cntOfRight < 0) {
                return false;
            }
        }
        return cntOfRight == 0;
    }
}
