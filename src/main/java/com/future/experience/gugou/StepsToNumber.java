package com.future.experience.gugou;

import java.util.Arrays;

/**
 * 给一个数，可以选择除以3（如果能整除），除以2（如果能整除），或者减一。问最少需要多少步才能到1。
 */
public class StepsToNumber {
    /**
     * Is it a positive integer?
     *
     * @param n
     * @return
     */
    public int getSteps(int n) {
       if(n == 1) return 0;
       if(n < 1) return Integer.MAX_VALUE;
       return Math.min(Math.min((n % 3) == 0 ? getSteps(n / 3) : Integer.MAX_VALUE, (n % 2) == 0 ? getSteps(n / 2) : Integer.MAX_VALUE), getSteps(n - 1)) + 1;
    }

    public int getStepsDP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for(int i = 2; i <= n; i++) {
            int min = dp[i - 1] + 1;
            if(i % 2 == 0) {
                min = Math.min(min, dp[i / 2] + 1);
            }
            if(i % 3 == 0) {
                min = Math.min(min, dp[i / 3] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        StepsToNumber p = new StepsToNumber();
        System.out.println(p.getSteps(2));
        System.out.println(p.getSteps(4));
        System.out.println(p.getSteps(9));
        System.out.println(p.getSteps(10));
        System.out.println("DP>>>>>>>>>>>>>>");
        System.out.println(p.getStepsDP(2));
        System.out.println(p.getStepsDP(4));
        System.out.println(p.getStepsDP(9));
        System.out.println(p.getStepsDP(10));
    }
}
