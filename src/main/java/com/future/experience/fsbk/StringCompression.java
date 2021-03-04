package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/string-compression/
 */
public class StringCompression {
    public int compress(char[] chars) {
        int p1 = -1, p2 = 0;
        while(p2 < chars.length) {
            int p = p2 + 1;
            while(p < chars.length && chars[p] == chars[p2]) {
                p++;
            }
            if(p - p2 == 1) {
                chars[++p1] = chars[p2];
            } else {
                chars[++p1] = chars[p2];
                int len = p - p2;   //be careful, the length may larger than single digit 9
                for(char ch : Integer.toString(len).toCharArray()) {
                    chars[++p1] = ch;
                }
            }
            p2 = p;
        }
        return p1 + 1;
    }
}
