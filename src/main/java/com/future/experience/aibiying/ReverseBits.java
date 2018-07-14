package com.future.experience.aibiying;

/**
 *
 Reverse bits of a given 32 bits unsigned integer.

 Example:

 Input: 43261596
 Output: 964176192
 Explanation: 43261596 represented in binary as 00000010100101000001111010011100,
 return 964176192 represented in binary as 00111001011110000010100101000000.
 Follow up:
 If this function is called many times, how would you optimize it?

 * Created by xingfeiy on 7/12/18.
 */
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if(n == 0) return 0;
        int res = 0;
        for(int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n = n >>> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        ReverseBits r = new ReverseBits();
        System.out.println(r.reverseBits(43261596));
    }
}
