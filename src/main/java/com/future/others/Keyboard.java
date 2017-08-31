package com.future.others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xingfeiy on 7/31/17.
 */
public class Keyboard {
    private static final Set<Character> FIRST_LINE = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
    private static final Set<Character> SECOND_LINE = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'));
    private static final Set<Character> THIRD_LINE = new HashSet<>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));


    public static boolean isSameLine(String word) {
        if(word == null) {
            return false;
        }

        word = word.toLowerCase();
        int line = 0;
        for(char ch : word.toCharArray()) {
            int tmp = 0;
            if(FIRST_LINE.contains(ch)) {
                tmp = 1;
            } else if(SECOND_LINE.contains(ch)) {
                tmp = 2;
            } else if(THIRD_LINE.contains(ch)) {
                tmp = 3;
            } else {
                return false;
            }
            if(line == 0) {
                line = tmp;
            } else if(tmp != line) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isSameLine("qwer"));
        System.out.println(isSameLine("qax"));
        System.out.println(isSameLine("uyo2"));
    }
}
