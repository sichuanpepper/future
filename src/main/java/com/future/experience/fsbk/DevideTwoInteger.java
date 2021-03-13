package com.future.experience.fsbk;

public class DevideTwoInteger {
    public int divide(int dividend, int divisor) {
        if(dividend == 0) {
            return 0;
        }
        long res = 0;
        long sum = 0;
        int sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? 1 : -1;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while(sum < dividend) {
            sum += divisor;
            res++;
        }
        res = (sum == dividend) ? res : res - 1;
        res = res * sign;
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(new DevideTwoInteger().divide(-2147483648 ,-1));
    }
}
