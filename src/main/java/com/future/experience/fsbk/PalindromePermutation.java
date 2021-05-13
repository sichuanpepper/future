package com.future.experience.fsbk;

/**
 * https://www.lintcode.com/problem/palindrome-permutation/
 */
public class PalindromePermutation {
    /**
     * @param s: the given string
     * @return: if a permutation of the string could form a palindrome
     */
    public boolean canPermutePalindrome(String s) {
        // write your code here
        int[] counter = new int[256];
        for(char ch : s.toCharArray()) {
            counter[ch]++;
        }

        int cntOfOdd = 0;
        for(int i = 0; i < counter.length; i++) {
            cntOfOdd += counter[i] % 2;
        }
        return cntOfOdd <= 1;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePermutation().canPermutePalindrome("code"));
    }
}
