package com.future.round2;

/**
 * https://leetcode.com/problems/powx-n/description/
 *
 * Created by xingfeiy on 6/4/18.
 */
public class Problem50 {
    public double myPow(double x, int n) {
        if(n == 0) return 1.0;
        if(n < 0) {
            x = 1.0 / x;
            if(Integer.MIN_VALUE == n) {  //catch overflow issue.
                return x * myPow(x, Integer.MAX_VALUE);
            } else {
                n = -n;
            }
        }

        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
