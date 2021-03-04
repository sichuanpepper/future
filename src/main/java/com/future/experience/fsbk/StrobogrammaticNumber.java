package com.future.experience.fsbk;

/**
 * https://www.lintcode.com/problem/strobogrammatic-number/
 */
public class StrobogrammaticNumber {
    /**
     * @param num: a string
     * @return: true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        // write your code here
        int[] map = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        char[] res = new char[num.length()];
        for(int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            if(map[ch - '0'] == -1) {
                return false;
            }
            res[num.length() - i - 1] = (char)('0' + map[ch - '0']);
        }
        return new String(res).equals(num);
    }

    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumber().isStrobogrammatic("69"));
    }
}
