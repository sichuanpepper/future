package com.future.experience.facebook;

/**
 * https://leetcode.com/problems/decode-ways/description/

 * Created by xingfeiy on 4/19/18.
 */
public class DecodeWay {
    public int numDecodings(String s) {
        //the given string could be empty, there's one way to decode emtpy string, do nothing.
        if(s == null || s.length() < 1) return 1;
        //the given string start with 0, there's no way to decode it.
        if(s.charAt(0) == '0') return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 1; i < s.length(); i++) {
            //if current character is '0', can not exist individually.
            if(s.charAt(i) != '0') dp[i + 1] = dp[i];
            //the two numbers could be equals 0 or it start with 0
            // Integer.parseInt("01") = 1
            int twoNums = Integer.parseInt(s.substring(i - 1, i + 1));
            if(twoNums < 27 && twoNums > 9) dp[i + 1] += dp[i - 1];
            if(dp[i + 1] == 0) return 0;
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("01"));
    }
}
