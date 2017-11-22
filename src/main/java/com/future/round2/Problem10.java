package com.future.round2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true

 --add by myself
 "aab", ".*b"

 * Created by someone on 10/11/17.
 */
public class Problem10 {
    public boolean isMatch(String s, String p) {
        if(p == ".*" || s.equals(p)) return true;
        if(s == null || s.length() < 1) return false;
        Queue<Character> sQueue = new LinkedList<>();
        for(char ch : s.toCharArray()) {
            sQueue.offer(ch);
        }
        Stack<String> pStack = new Stack<>();
        for(int i = p.length() - 1; i >= 0; i--) {
            if(p.charAt(i) == '*' && i > 0) {
                pStack.push(p.substring(i - 1, i-- + 1));
            } else {
                pStack.push(Character.toString(p.charAt(i)));
            }
        }

        while (!sQueue.isEmpty() && !pStack.isEmpty()) {
            String pTmp = pStack.pop();
            if(pTmp.equals(".*")) {
                String ch = pStack.pop();
                while (!ch.equals(".*") && !ch.equals(".") && !pStack.isEmpty()) {
                    ch = pStack.pop();
                }
                char sCh = sQueue.poll();
                while (Character.toString(sCh) != ch && !sQueue.isEmpty()) {
                    sCh = sQueue.poll();
                }
            } else if(pTmp.equals(".")) {
                sQueue.poll();
            } else if(pTmp.endsWith("*") && isValidChar(pTmp.charAt(0))) {
                if(sQueue.peek() != pTmp.charAt(0)) continue;
                while (!sQueue.isEmpty() && sQueue.peek() == pTmp.charAt(0)) {
                    sQueue.poll();
                }
            }else {
                if(!pTmp.equals(Character.toString(sQueue.poll()))) return false;
            }
        }
        return sQueue.isEmpty() && pStack.isEmpty();
    }

    private boolean isValidChar(char ch) {
        return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch < 'z') || (ch >= 'A' && ch < 'Z');
    }

    public static void main(String[] args) {
        Problem10 p = new Problem10();
        System.out.println(p.isMatch("", ""));
        System.out.println(p.isMatch("aa", "a"));
        System.out.println(p.isMatch("aa", "aa"));
        System.out.println(p.isMatch("aa", ".*"));
        System.out.println(p.isMatch("aa", "a*"));
        System.out.println(p.isMatch("aa", "a."));
        System.out.println(p.isMatch("aab", "c*a*b"));
        System.out.println(p.isMatch("aab", ".*b"));

        System.out.println("abc" == "abc");
        System.out.println(new String("abc") == "abc");
        System.out.println(new String("abc").equals("abc"));
        System.out.println(new String("a") == "a");
        System.out.println(Character.toString('a') == "a");
        System.out.println('a' == 'a');
        System.out.println("a".charAt(0) == 'a');
    }
}
