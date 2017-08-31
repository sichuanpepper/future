package com.future.foundation.search;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

 Given n, find the total number of full staircase rows that can be formed.

 n is a non-negative integer and fits within the range of a 32-bit signed integer.

 * Created by xingfeiy on 4/12/17.
 */
public class ArrangingCoins441 {
    public int arrangeCoins(int n) {
        int f = 0;
        int ct = 0;
        int pre = 0;
        while (ct + pre + 1 <= n) {
            f++;
            ct += (pre++ + 1);
        }

        return f;
    }


    public int solution(int n) {
        int start = 0;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(mid * (mid + 1) / 2 <= n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start - 1;
    }

    public static int solutionT(int n) {
        int start = 0;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if((long)mid * (mid + 1) / 2 <= n) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if((long)start * (start + 1) / 2 > n) {
            return start - 1;
        } else if((long)end * (end + 1) / 2 > n) {
            return start;
        } else {
            return end;
        }
    }

    public static int arrangeCoins2(int n) {
        int start = 0;
        int end = n;
        int mid = 0;
        while (start <= end){
            mid = (start + end) >>> 1;
            if ((0.5 * mid * mid + 0.5 * mid ) <= n){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start - 1;
    }

    public static void main(String[] args) {
        int a = 1804289383;
        long b = 1804289383;
        System.out.println(a * (a + 1) / 2);
        System.out.println(b * (b + 1) / 2);
        System.out.println(solutionT(1804289383));
        System.out.println(arrangeCoins2(1804289383));
    }
}
