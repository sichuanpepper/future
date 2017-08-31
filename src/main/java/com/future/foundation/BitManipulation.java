package com.future.foundation;

/**
 * Created by xingfeiy on 5/17/17.
 */
public class BitManipulation {
    public static void main(String[] args) {
//        binary();
//        shiftOperations();
//        bitwiseOperators();
//        hammingDistance(1, 4);
//        toHex(26);
//        System.out.println(revertBinary(4));
//        System.out.println(revertBinary(43261596));
//        tricks();

        Math.pow(10, 2);
    }

    public static void tricks() {
        // num = num1 & num2, num is not greater than Math.min(num1, num2)
        System.out.println(11 & 2); //output 2
        System.out.println(11 & 5); //output 1

        // get the last set bit of num, the last set bit means the right most bit '1'
        System.out.println(8 & -8); // if there's only one set bit, always return the positive value.
        // 11(1011) => -11 = 0 - 11 = 0000 - 1011 = 0101
        //11 & -11 = 1011 & 0101 = 0001
        System.out.println(11 & -11);
    }

    public static void shiftOperations() {
        //In Java, there are two shift operations, '>>' shift right, '<<' shift left.
        int i = 1; //00000001
        System.out.println(i << 1); //output 2, equivalent to i * 2

        i = 3; //00000011
        System.out.println(i << 1); //output 6 (00000110), equivalent to i * 2

        System.out.println(i << 3); //output 24, equivalent to i * 2 * 2 * 2

        //shift right, notes that the shift right is signed.
        i = 3; //00000011
        System.out.println(i >> 1); //output 1, equivalent to i / 2

        i =  -3;  //11111111111111111111111111111101
        System.out.println(i >> 1); //output -2, surprise??
        //let's see what's the binary string of -2
        System.out.println(Integer.toBinaryString(-2));  //output: 11111111111111111111111111111110

        System.out.println(-4 >> 1); //output -2
        System.out.println(Integer.toBinaryString(-4 >> 1)); //11111111111111111111111111111110

        System.out.println(-3 << 1);
        System.out.println(Integer.toBinaryString(-3 << 1));  //output -6

        // -3 / 2 = (0 - 3) / 2 = 0 / 2 - 3 / 2 = 00000000 - 00000011 >> 1 = 00000000 - 00000001 = 11111111111111111111111111111111
        // looks like -1 should be returned, what's wrong here? please notes that 3 / 2 actually equals 1.5, not 1,
        // since we returned an integer, the fractional part got ignored.
        // so number / 2 real equals number >> 1 only happens when the number is even.
        // for positive number, if we just consider integer, all positive number / 2 equal to positive number >> 1
        // for negative number, don't ignore fractional part like positive, should round it.
        // so, negative number / 2 - 1= negative >> 1

        //third shift operator '>>>', it's unsigned shift right, means always shift in a '0' regardless of the sign.
        i = -3;
        System.out.println(i >>> 1); //output 2147483646

        //what happens here? 11111111111111111111111111111101 >>> 1 =  1111111111111111111111111111110
        System.out.println(Integer.parseInt("1111111111111111111111111111110", 2));

    }

    /**
     * There are 4 common bitwise operators:
     * ~ - The unary complement
     * & - Bitwise and
     * ^ - Bitwise exclusive or
     * | - Bitwise inclusive or
     */
    public static void bitwiseOperators() {
        //simply, ~1 to 0, ~0 to 1
        System.out.println(Integer.toBinaryString(~1)); // output 11111111111111111111111111111110 (-2)
        System.out.println(Integer.toBinaryString(~(-2))); // output 1

        //simply, 1 & 1 to 1, otherwise 0
        System.out.println(10 & 5); // 1010 & 0101 = 0
        System.out.println(Integer.toBinaryString(10 & 5));

        System.out.println(10 & 4); // 1010 & 0100 = 0
        System.out.println(Integer.toBinaryString(10 & 4));

        //simply, 0 | 0 to 0, otherwise 1
        System.out.println(10 | 5); // 1010 | 0101 = 1111
        System.out.println(Integer.toBinaryString(10 | 5));

        System.out.println(10 | 2); // 1010 | 0010 = 1010
        System.out.println(Integer.toBinaryString(10 | 2));

        //simply, same bits to 0, otherwise 1
        System.out.println(10 ^ 5); // 1010 ^ 0101 = 1111
        System.out.println(Integer.toBinaryString(10 ^ 5));

        System.out.println(10 ^ 6); // 1010 ^ 0110 = 1100
        System.out.println(Integer.toBinaryString(10 ^ 6));

        //we often use bitwise inclusive or to set the bits
        System.out.println(Integer.toBinaryString(256 | 4)); //turn on bit 2 (zero-based), 10000000 | 00000100 = 10000100
        //do the same thing by other way
        System.out.println(Integer.toBinaryString(256 | (1 << 2)));

        //turn off  bit 2
        int val = 256 | 4; //turn on bit 2
        System.out.println(Integer.toBinaryString(val));
        //turn of bit 2
        val &= ~(1 << 2);
        System.out.println(Integer.toBinaryString(val));
    }

    public static void binary() {
        System.out.println(Integer.toBinaryString(1)); // 1
        System.out.println(Integer.toBinaryString(0)); // 0
        System.out.println(Integer.toBinaryString(-1)); // 11111111111111111111111111111111

        //The integer is signed by default, the most significant bit is a "sign" bit, 0 represents positive and 1 represents negative.
        //So actually the binary of 1 is 00000000000000000000000000000001, just the leading '0's have been ignored.
        //How did we get the binary of -1 looks strange?
        // -1 = 0 -1, in binary, it looks like:
        // 00000000000000000000000000000000 - 00000000000000000000000000000001 = 11111111111111111111111111111111

        String bStr = Integer.toBinaryString(-1);
//        System.out.println(Integer.parseInt(bStr, 2));
        //will get an exception in above line, Exception in thread "main" java.lang.NumberFormatException:
        // For input string: "11111111111111111111111111111111"
        // looks like this method only parse at most 31 bits, I guess the reason is the leading bit could be a part of
        // number or a signed bit.

        System.out.println(Integer.parseInt("-0000000000000000000000000000001", 2));  //output -1
        System.out.println(Integer.parseInt("-0110", 2));  //output -6
    }

    /**
     * There are two popular approaches, one is easy to understand, and one is faster but hard to understand.
     * @param n
     * @return
     */
    public static int countSetBitsV1(int n) {
        int count = 0;
        while (n != 0) {  //the while condition is n != 0, not n > 0 or n < 0
            count += (n & 1);
            n >>>= 1;  //use unsigned shift right >>> rather than shift right >>
        }
        return count;
    }

    public static int countSetBitsV2(int n) {
        n = n - ((n >> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        return (((n + (n >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
    }

    public static int hammingDistance(int x, int y) {
        int val = (x ^ y);
        int count = 0;
        while(val > 0) {
            if((val & 1) == 1) {
                count++;
            }
            val = val >> 1;
        }
        return count;
    }

    public static String toHex(int num) {
        if(num == 0) {
            return "0";
        }
        char[] chars = new char[16];
        for(int i = 0; i < 10; i++) {
            chars[i] = Character.forDigit(i, 10);
        }

        chars[10] = 'a';
        chars[11] = 'b';
        chars[12] = 'c';
        chars[13] = 'd';
        chars[14] = 'e';
        chars[15] = 'f';

        String result = "";
        while (num != 0) {
            result = chars[num & 15] + result;
            num = num >>> 4;
        }
        return result;

    }

    public static long revertBinary(int val) {
        return revert(val, 32);
    }

    private static long revert(int val, int length) {
        if(length < 2 || val == 0) {
            return val;
        }

        int top = val >>> (length / 2);
        System.out.println(Integer.toBinaryString(top));
        int low = val << (length / 2);
        System.out.println(Integer.toBinaryString(low));
        return revert(top, length / 2) + revert(low, length / 2);
    }

    public static int reverseBits(int n) {
        if(n == 0) {
            return 0;
        }

        int res = 0;
        while (n != 0) {
            res <<= 1;
            res |= (n & 1);
            n >>>= 1;   //use unsigned shift right
        }
        return res;
    }

    public static boolean powerOfTwoV1(int n) {
        if(n < 1) {
            return false;
        }
        int count = 0;
        while (n != 0) {  //the while condition is n != 0, not n > 0 or n < 0
            count += (n & 1);
            n >>>= 1;  //use unsigned shift right >>> rather than shift right >>
        }
        return count == 1;
    }

    /**
     * It's easy to understand that  n & (n - 1) == 0 if there's only 1 set bit.
     * How to prove n & (n - 1) won't equal 0 if there are more than 1 set bits?
     * It's also simple, if there are more than 1 set bits in the binary, what happens when we compute n - 1 in binary view?
     * The right most set bit becomes to 0, and the set bits on left side still there, so the n - 1 won't go 0.
     * example: 12 - 1 = 1100 - 0001 = 1011
     * @param n
     * @return
     */
    public static boolean powerOfTwoV2(int n) {
        return (n < 1) ? false : ((n & (n - 1)) == 0);
    }

    /**
     * The set of power of four is the subset of power of two.
     * 1 is the first one, 1 * 2 is not, 1 * 2 * 2 is next one, etc.
     * So the number of power of four happens at 1 interval in set of power of two.
     * Which means, from right to left, if 1st or 3rd or 5th or 7th... is set bit, it's the number that power of four.
     * @param n
     * @return
     */
    public static boolean powerOfFour(int n) {
        return (n < 1) ? false : ((n & (n - 1)) == 0) && ((n & 0x55555555) == n);
    }


}
