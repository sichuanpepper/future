package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

/**
 * Created by xingfeiy on 7/4/18.
 */
public class RegularExpression {
    /**
     * Lower case only
     * The pattern may include letters, or
     *  - '.', means match any character
     *  - '*', repeat previous character zero or multiple times.
     *  - '+', reqpeat previous character at least once.
     *  f(m, n) = f(m - 1, n - 1)  if char(m) == char(n) or char(n) == '.'
     *          = f(m, n - 2)                 if char(m - 1) != char(n - 1) repeat zero time, char(n) == '*'
     *          = f(m, n - 2) || f(m - 1, n - 1)      if char(m - 1) == char(n - 1) repeat zero time or at more times
     *
     *          char(n) == '+'
     *          f(m - 1, n - 1)
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean matches(String str, String pattern) {
        if(str == null) return str == pattern;
        if(pattern == null) return pattern == str;
        //
        boolean[][] dp = new boolean[str.length() + 1][pattern.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i < dp[0].length; i++) {
            if(pattern.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 2];
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                //current characters matched
                if(str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(pattern.charAt(j - 1) == '*') { //current not match
                    if(str.charAt(i - 1) != pattern.charAt(j - 2) && pattern.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j] || dp[i - 1][j - 1];  //repeat zero time or multiple time
                    }
                } else if(pattern.charAt(j - 1) == '+') {
                    if(str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 1];  //repeat multiple times or just one time
                }
            }
        }
//        DisplayUtils.printTwoDimensionsArray(dp);
        return dp[str.length()][pattern.length()];
    }

    public static void main(String[] args) {
        RegularExpression r = new RegularExpression();
        System.out.println(r.matches(null, null)); //true
        System.out.println(r.matches(null, "a")); //false
        System.out.println(r.matches("", null)); //false
        System.out.println(r.matches("a", "a")); //true
        System.out.println(r.matches("abc", "abc")); //true
        System.out.println(r.matches("abc", ".*")); //true
        System.out.println(r.matches("abbbc", "abc")); //false
        System.out.println(r.matches("abbbc", "abb.c")); //true
        System.out.println(r.matches("abbbc", "abbb.")); //true
        System.out.println(r.matches("abbbc", ".bbbc")); //true
        System.out.println(r.matches("abbbc", "abb*c")); //true
        System.out.println(r.matches("abbbc", "abbb*c")); //true
        System.out.println(r.matches("abbbc", "ab*c")); //true
        System.out.println(r.matches("abbbc", "a.*c")); //true
        System.out.println(r.matches("abbbc", "a.+c")); //true
        System.out.println(r.matches("abbbc", "ab+c")); //true
        System.out.println(r.matches("abbbc", "a+c")); //false
        System.out.println(r.matches("abbbc", "abbb+c")); //false

    }
}
