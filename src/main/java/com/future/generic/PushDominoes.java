package com.future.generic;

public class PushDominoes {
    /**
     - We use a for loop, each loop represents a second.
     - For each loop, we check the standing domino, change l to L and r to R
     - if it has LPush on right, change the status to l. (here use lowercase represents new change)
     - if it has RPush on left, change the status to r.
     **/
    public String pushDominoes(String dominoes) {
        if(dominoes == null || dominoes.length() < 1) {
            return dominoes;
        }
        char[] chars = dominoes.toCharArray();
        while(true) {
            boolean over = true;
            for(int i = 0; i < chars.length; i++) {
                if(chars[i] == 'l') chars[i] = 'L';
                if(chars[i] == 'r') chars[i] = 'R';
                if(chars[i] == '.') {
                    boolean hasRPushOnLeft = (i == 0 ? false : chars[i - 1] == 'R');
                    boolean hasLPushOnRight = (i == chars.length - 1 ? false : (chars[i + 1] == 'l' || chars[i + 1] == 'L'));
                    if(hasRPushOnLeft && hasLPushOnRight) {
                        //
                    } else if(hasRPushOnLeft) {
                        chars[i] = 'r';
                        over = false;
                    } else if(hasLPushOnRight) {
                        chars[i] = 'l';
                        over = false;
                    }
                }
            }
            if(over) {
                break;
            }
        }
        return new String(chars);
    }

    /**
     * Optimized solution
     - The fallen domino won't be change, only the standing domino may got changed.
     - Find all standing dominos segments, check the left and right domino of each segment.
     - left is L, right is L, all standing dominos falling to left.
     - left is r, right is r, all standing dominos falling to right.
     - left is l, right is r, no change
     - left is r, right is l, half falling to left and half falling to right.
     **/
    public String pushDominoesV2(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int p = 0;
        while(p < chars.length) {
            if(chars[p] == '.') {
                //find standing segment
                int start = p, end = p;  //start included and end excluded
                while(end < chars.length && chars[end] == '.') {
                    end++;
                }
                char onLeft = (start == 0 ? 'L' : chars[start - 1]);
                char onRight = (end >= chars.length ? 'R' : chars[end]);
                if(onLeft == 'L' && onRight == 'L') {
                    for(int i = start; i < end; i++) {
                        chars[i] = 'L';
                    }
                } else if(onLeft == 'R' && onRight == 'R') {
                    for(int i = start; i < end; i++) {
                        chars[i] = 'R';
                    }
                } else if(onLeft == 'R' && onRight == 'L') {
                    int l = start, r = end - 1;
                    while(l < r) {
                        chars[l++] = 'R';
                        chars[r--] = 'L';
                    }
                }
                p = end;
            } else {
                p++;
            }
        }
        return new String(chars);
    }
}
