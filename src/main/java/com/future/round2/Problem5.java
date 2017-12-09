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
 * Created by someone on 10/11/17.
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
     * Let's use f(m,n) represent the result of substring from m to n.
     * If f(m, n) is palindromic, then f(m - 1, n + 1) is palidromic if s.charAt(m-1) == s.charAt(n+1), otherwise, it's not.
     * So that means, if f(m + 1, n - 1) is palindromic and s.charAt(m) == s.char(n), then f(m, n) is palindromic.
     * @param
     * @return
     */
    public static String solution2(String s) {
        if(s == null || s.length() < 1) return "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for(int i = s.length() - 1; i >= 0; i--) {
            for(int j = i; j < s.length(); j++) {
                if(i == j || (j - i == 1 && s.charAt(i) == s.charAt(j))) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
                }
                if(dp[i][j] && res.length() < (j - i + 1)) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution1(""));
        System.out.println(solution1("a"));
        System.out.println(solution1("aaaaaaa"));
        System.out.println(solution1("aabcecbad"));

        System.out.println("=====================================");

        System.out.println(solution2(""));
        System.out.println(solution2("a"));
        System.out.println(solution2("aaaaaaa"));
        System.out.println(solution2("aabcecbad"));
    }
}
