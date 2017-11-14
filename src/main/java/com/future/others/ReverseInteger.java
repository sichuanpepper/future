package com.future.others;

/**
 * Created by someone on 10/25/17.
 */
public class ReverseInteger {
    public static int reverse(int val) {
        long res = 0;
        int abs = Math.abs(val);
        while(abs != 0) {
            res = res * 10 + abs % 10;
            abs /= 10;
        }
        return res > Integer.MAX_VALUE? 0 : ((val < 0) ? -(int)res : (int)res);
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(1111111119));
        System.out.println(reverse(-2147483647));
    }
}
