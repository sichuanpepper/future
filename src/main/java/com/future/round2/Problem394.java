package com.future.round2;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/description/

 Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 * Created by xingfeiy on 8/1/18.
 */
public class Problem394 {
    /**
     * Analyze:
     * basically, the '[]' will embrace a sub problem, so once we meet '[', it's time to push stack.
     * There are two different elements we need to push to stack, repeat times and current string.
     * And for pop stack, we also need to pop two different elements out
     *  - repeat times
     *  - previous string
     *
     * There are 4 kind of characters:
     * - digit -> find the number
     * - letter
     * - '['
     * - ']'
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if(s == null || s.length() < 1) return s;
        int p = 0, repeatTime = 0;
        String curStr = "";
        Stack<String> strStack = new Stack<>();
        Stack<Integer> times = new Stack<>();
        while (p < s.length()) {
            if(Character.isDigit(s.charAt(p))) {
                int tmp = p;
                while (p < s.length() && Character.isDigit(s.charAt(p))) p++;
                repeatTime = Integer.parseInt(s.substring(tmp, p));
            } else if(Character.isLetter(s.charAt(p))) {
                int tmp = p;
                while (p < s.length() && Character.isLetter(s.charAt(p))) p++;
                curStr += s.substring(tmp, p);
            } else if(s.charAt(p) == ']') {
                StringBuilder sb = new StringBuilder();
                sb.append(strStack.pop());
                int count = times.pop();
                for(int i = 0; i < count; i++) {
                    sb.append(curStr);
                }
                curStr = sb.toString();
                p++;
            } else if(s.charAt(p) == '[') {
                strStack.push(curStr);
                times.push(repeatTime);
                curStr = "";
                p++;
            }
        }

        return curStr;
    }

    public static void main(String[] args) {
        Problem394 p = new Problem394();
        System.out.println(p.decodeString("3[a]2[bc]"));
//        System.out.println(p.decodeString("3[a2[c]]"));
        System.out.println(p.decodeString("2[abc]3[cd]ef"));
        System.out.println(p.decodeString("3[a2[c]]"));
    }
}
