package com.future.experience.aibiying;

import com.future.foundation.java.multiplethreads.SyncExample;

/**
 *
 Implement a simple regex parser which, given a string and a pattern, returns a boolean indicating
 whether the input matches the pattern. By simple, we mean that the regex can only contain special
 character: * (star), . (dot), + (plus). The star means what you'd expect, that there will be zero or more of
 previous character in that place in the pattern. The dot means any character for that position. The plus

 means one or more of previous character in that place in the pattern.

 * Created by xingfeiy on 6/14/18.
 */
public class RegularExperssionMatching {

    /**
     * f(s, r) =
     *          - if(str.char(s) == reg.char(r)) f(s - 1, r - 1)
     *          - if(reg.char(r) == '.') f(s - 1, r - 1)
     *          - if(reg.char(r) == '*')
     *              - if(str.char(s) != reg.char(r - 1) && reg.char(r - 1) != '.') f(s, r - 2)  //repeat 0
     *              - if(str.char(s) == reg.char(r - 1))
     *                  - f(s, r - 2) //repeat 0
     *                  - f(s - 1, r)
     *          - if(reg.char(r) == '+')
     *              - if(str.char(s) == reg.char(r - 1))
     *                  - f(s - 1, r - 1) repeat once
     *                  - f(s - 1, r) repeat more than once
     *              - else false
     * @param str
     * @param reg
     * @return
     */
    public boolean regMatch(String str, String reg) {
        boolean[][] dp = new boolean[str.length() + 1][reg.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i < dp[0].length; i++) {
            if(reg.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 1] || dp[0][i - 2];
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(reg.charAt(j - 1) == str.charAt(i - 1) || reg.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(reg.charAt(j - 1) == '*') {
                    if(reg.charAt(j - 2) != str.charAt(i - 1) && reg.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    }
                } else if(reg.charAt(j - 1) == '+') {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[str.length()][reg.length()];
    }

    public static void main(String[] args) {
        RegularExperssionMatching p = new RegularExperssionMatching();
        System.out.println(p.regMatch("", ""));  //true
        System.out.println(p.regMatch("aa", "aa"));  //true
        System.out.println(p.regMatch("aa", "a*"));  //true
        System.out.println(p.regMatch("aa", "aa*"));  //true
        System.out.println(p.regMatch("aa", "a+"));  //true
        System.out.println(p.regMatch("aa", "aa+"));  //false
        System.out.println(p.regMatch("aa", ".*"));  //true
        System.out.println(p.regMatch("aa", ".+"));  //true
        System.out.println(p.regMatch("aa", "a.*"));  //true
        System.out.println(p.regMatch("aa", "aa.*"));  //true
        System.out.println(p.regMatch("aa", "aa.+"));  //false
        System.out.println(p.regMatch("aaa", "a+"));  //true
    }
}
