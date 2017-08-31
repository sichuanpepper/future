package com.future.round1;

/**
 * Created by xingfeiy on 4/12/17.
 */
public class Sqrt69 {
    public static int mySqrt(int x) {
        if(x < 1) {
            return 0;
        }

        int start = 1;
        int end = x;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            long tmp = (long)mid * mid;
            if(tmp == x) {
                return mid;
            } else if(tmp < x) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if((long)end * end <= x) {
            return end;
        } else {
            return start;
        }
    }


    public static void main(String[] args) {
        double d = 2147395599 * 2147395599;
        System.out.println(mySqrt(2147395599));
    }
}
