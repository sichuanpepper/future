package com.future.foundation.dp;

/**
 * Created by someone on 7/31/17.
 */
public class Levenshtein {
    public int distance(String str1, String str2) {
        if(str1 == null || str2 == null) {
            return 0;
        }
        if(str1.length() < 1) {
            return str2.length();
        }

        if(str2.length() < 1) {
            return str1.length();
        }

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for(int i = 1; i < str1.length() + 1; i++) {
            for(int j =  1; j < str2.length() + 1; j++) {
                if(str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
//        DisplayUtils.printTwoDimensionsArray(dp);
        return dp[str1.length()][str2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new Levenshtein().distance("abc", "abc"));
        System.out.println(new Levenshtein().distance("axc", "abc"));
        System.out.println(new Levenshtein().distance("ad", "abc"));
    }
}
