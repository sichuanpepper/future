package com.future.round2;

/**
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

 For example, given the range [5, 7], you should return 4.


 * Created by xingfeiy on 3/7/18.
 */
public class Problem201 {
    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0) return 0;
        int moveSteps = 1;
        //if m != n, the last bit must be different, and turns out 0 after and operation.
        while(m != n) {
            m >>= 1;
            n >>= 1;
            moveSteps <<= 1;
        }
        return m * moveSteps;
    }
}
