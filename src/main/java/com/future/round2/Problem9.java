package com.future.round2;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 * Created by someone on 10/11/17.
 */
public class Problem9 {
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        } else if(x < 10) {
            return true;
        }

        //just compare half digits in x
        //likes 12321, x->1232, t->1; x->123, t->12; x->12, t->123; x==t/10
        //1221, x->121,t->1; x->12,t->12; x==t
        //but 1000, x->100,t->0;x->10,t->0;x->1,t->0
        if(x % 10 == 0) {
            return false;
        }

        int tmp = 0;
        while (x > tmp) {
            tmp = tmp * 10 + x % 10;
            x = x / 10;
        }
        return tmp == x || tmp / 10 == x;
    }
}
