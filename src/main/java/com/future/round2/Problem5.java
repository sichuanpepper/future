package com.future.round2;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:

 Input: "babad"

 Output: "bab"

 Note: "aba" is also a valid answer.
 Example:

 Input: "cbbd"

 Output: "bb"
 * Created by xingfeiy on 10/11/17.
 */
public class Problem5 {
    public static String solution1(String s) {
        //brute solution
        if(s == null || s.length() < 1) return "";
        String res = "";
        for(int i = 0; i < s.length(); i++) {
            String oddStr = helper(s, i, 0);
            String evenStr = helper(s, i, 1);
            oddStr = (oddStr.length() > evenStr.length() ? oddStr : evenStr);
            res = oddStr.length() > res.length() ? oddStr : res;
        }
        return res;
    }

    private static String helper(String s, int pos, int offset) {
        int left = pos;
        int right = pos + offset;
        while (left >= 0 && right < s.length()) {
            if(s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }


    /**
     * DP solution
     * f(m, n) to represent if substring(m, n + 1) is palindromic or not, so we can get:
     * f(m, n) = f(m, n - 1) && charAt(m - 1) == charAt(n)
     * @param
     * @return
     */
//    public static String solution2(String s) {
//        if(s == null || s.length() < 1) return "";
//        boolean[][] dp = new boolean[s.length()][s.length()];
//        String res = "";
//        for(int i = 0; i < s.length(); i++) {}
//    }

    public static void main(String[] args) {
        System.out.println(solution1(""));
        System.out.println(solution1("a"));
        System.out.println(solution1("aaaaaaa"));
        System.out.println(solution1("aabcecbad"));
    }
}
