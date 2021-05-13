package com.future.experience.linying.eley;

import java.util.Set;

public class FindPalindromes {
    /*
     * Returns the set of all subsequences of s which are palindromes.
     * For example, given the string "abcbrta", you should return "a", "abba", "abcba", "aca", "bcb", "aba", etc.
     *
     * https://leetcode.com/problems/count-different-palindromic-subsequences/
     * https://leetcode.com/discuss/interview-question/algorithms/125005/print-all-the-palindromic-substrings-of-a-given-string
     */
    public Set<String> findPalindromes(String s) {
        return null;
    }

    /**
     * https://www.geeksforgeeks.org/count-palindromic-subsequence-given-string/
     * The problem can be solved recursively
     * s(i, j)
     *  if i == j, return 1
     *  else
     *      if char(i) == char(j)   //consider as a palindrome, no matter what chars placed between i and j, at least one palindrome here
     *          += s(i + 1, j - 1) + 1   // include both i and j
     *          += s(i + 1, j) + s(i, j - 1) - s(i + 1, j - 1) //include either i or j
     *          then come out = s(i + 1, j) + s(i, j - 1) + 1
     *      else
     *          = s(i + 1, j) + s(i, j - 1) - s(i + 1, j - 1)
     *
     * @param str
     * @return
     */
    public static int countPS(String str)
    {
        int N = str.length();

        // create a 2D array to store the count
        // of palindromic subsequence
        int[][] cps = new int[N][N];

        // palindromic subsequence of length 1
        for (int i = 0; i < N; i++)
            cps[i][i] = 1;

        // check subsequence of length L is
        // palindrome or not
        for (int L = 2; L <= N; L++) {
            for (int i = 0; i <= N-L; i++) {
                int k = L + i - 1;
                if (str.charAt(i) == str.charAt(k)) {
                    cps[i][k] = cps[i][k - 1]
                            + cps[i + 1][k] + 1;
                }else{
                    cps[i][k] = cps[i][k - 1]
                            + cps[i + 1][k]
                            - cps[i + 1][k - 1];
                }
            }
        }

        // return total palindromic subsequence
        return cps[0][N - 1];
    }

    public static void main(String[] args) {
        System.out.println(countPS("aaa"));
        System.out.println(countPS("aba"));
        System.out.println(countPS("abca")); //a, b, c, a, aa, aba, aca
    }

}
