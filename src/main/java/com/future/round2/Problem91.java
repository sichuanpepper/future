package com.future.round2;

/**
 * https://leetcode.com/problems/decode-ways/description/
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

  'A' -> 1
  'B' -> 2
  ...
  'Z' -> 26
  Given an encoded message containing digits, determine the total number of ways to decode it.

 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

 The number of ways decoding "12" is 2.

 * Created by someone on 11/14/17.
 */
public class Problem91 {
    /**
     * Analyze:
     * 1. It's actually a DFS problem, we can build a tree for given digits string.
     * for example we are given string "123", we can build the string likes this:
     *             ""
     *         1         12
     *     2      23         3
     *   3           null      null
     *
     *   Find all paths from root to leaf.
     *
     *   The recursive solution got TLE.
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if(s == null || s.length() < 1 || s.startsWith("0")) {
            return 0;
        }
        return helper(s, 0);
    }


    private int helper(String s, int start) {
        if(start >= s.length()) {
            return 1;
        }
        if(s.charAt(start) == '0') return 0;
        int count = 0;
        count += helper(s, start + 1);
        if((start + 2) <= s.length() && Integer.parseInt(s.substring(start, start + 2)) < 27) {
            count += helper(s, start + 2);
        }
        return count;
    }

    /**
     * We can use DP to solve this issue.
     *
     * It's easy to find the optimal function:
     * f(n) = f(n - 1) + f(n - 2) if substring(n-1, n) is less than 26
     *
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        if(s == null || s.length() < 1 || s.startsWith("0")) return 0;
        int first = 1;
        int second = 1;
        for(int i = 1; i < s.length(); i++) {
            int tmp = 0;
            if(s.charAt(i) != '0') tmp += second;
            if(s.charAt(i - 1) != '0' && Integer.parseInt(s.substring(i - 1, i + 1)) < 27) tmp += first;
            first = second;
            second = tmp;
        }
        return second;
    }

    public static void main(String[] args) {
        Problem91 p = new Problem91();
        System.out.println(p.numDecodings(""));
        System.out.println(p.numDecodings("0090"));
        System.out.println(p.numDecodings("9001"));
        System.out.println(p.numDecodings("12"));
        System.out.println(p.numDecodings("123"));
        System.out.println(p.numDecodings("1023"));
        System.out.println(p.numDecodings("1020"));

        System.out.println("----------------------");
        System.out.println(p.numDecodings2(""));
        System.out.println(p.numDecodings2("0090"));
        System.out.println(p.numDecodings2("9001"));
        System.out.println(p.numDecodings2("12"));
        System.out.println(p.numDecodings2("123"));
        System.out.println(p.numDecodings2("1023"));
        System.out.println(p.numDecodings2("1020"));
    }
}
