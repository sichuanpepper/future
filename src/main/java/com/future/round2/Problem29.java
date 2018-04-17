package com.future.round2;

import com.future.utils.Interval;

/**
 *
 * https://leetcode.com/problems/divide-two-integers/description/
 *
 * Divide two integers without using multiplication, division and mod operator.

 If it is overflow, return MAX_INT.
 *
 * Created by someone on 11/26/17.
 */
public class Problem29 {
    /**
     * Analyze:
     * dividend and divisor could be either positive or negative.
     * to simplify the problem, we can consider both of them are positive, and add the sign at last.
     *
     * The overflow issue? when it occurs?
     * corner case, divisor is 0.
     * we can use another way to compute division, like 10/3 = (3+3+3 util value > 10), the count of divisor is the
     * result of division.
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if(divisor == 0) return Integer.MAX_VALUE;
        int sign = ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) ? 1 : -1;
        //use long instead of integer to avoid overflow issue.
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long) divisor);
        if(ldividend < ldivisor) return 0;

        long res = 0;
        if(ldivisor == 1) {
            res = ldividend;
        } else {
            res = helper(ldividend, ldivisor);
        }
        res = sign * res;
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int)res;
    }

    private long helper(long ldividend, long ldivisor) {
        if(ldividend < ldivisor) return 0;
        long res = 1;
        long sum = ldivisor;
        while (sum + sum <= ldividend) {
            sum += sum;
            res = res << 1;
        }
        return res + helper(ldividend - sum, ldivisor);
    }


    public static void main(String[] args) {
        Problem29 p = new Problem29();
        System.out.println(p.divide(-2147483648, 1));
    }

}
