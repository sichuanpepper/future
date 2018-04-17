package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 *
 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]
 * Created by xingfeiy on 3/22/18.
 */
public class Problem301 {
    /**
     * BFS solution
     * @param s
     * @return
     */
    public List<String> removeInvalidParenthesesBFS(String s) {
        if(s == null || s.length() < 1) return Arrays.asList(new String[]{s});;

        Queue<String> queue = new LinkedList<>();
        queue.offer(s);

        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String tmp = queue.poll();
                if(visited.contains(tmp)) continue;
                visited.add(tmp);

                if(isValid(tmp)) {
                    res.add(tmp);
                } else {
                    for(int j = 0; j < tmp.length(); j++) {
                        if(tmp.charAt(j) != '(' && tmp.charAt(j) != ')') continue;
                        if(j > 0 && tmp.charAt(j) == tmp.charAt(j - 1)) continue;
                        queue.offer(tmp.substring(0, j) + tmp.substring(j + 1, tmp.length()));
                    }
                }
            }
            if(res.size() > 0) break;
        }
        return res;
    }

    private boolean isValid(String str) {
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') count++;
            if(str.charAt(i) == ')' && count-- == 0) return false;
        }
        return count == 0;
    }

}
