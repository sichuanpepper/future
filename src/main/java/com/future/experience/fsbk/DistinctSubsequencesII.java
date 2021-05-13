package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/distinct-subsequences-ii/
 *
 * For any string s, if we know the result is f(n), then if we add one more character, it generates f(n) + 1 new subsequence(add one character to each subseq in the previous results).
 * But here may have overlap, example, abcb, the subseq of second b will cover the subseq of first b, so we need to remove overlap.
 * So we can maintain the number of subseq of the ending character.
 */
public class DistinctSubsequencesII {
    public int distinctSubseqII(String S) {
        int end[] = new int[26], res = 0, added = 0, mod = (int)1e9 + 7;
        for (char c : S.toCharArray()) {
            added = (res + 1 - end[c - 'a']) % mod;
            res = (res + added) % mod;
            end[c - 'a'] = (end[c - 'a'] + added) % mod;
        }
        return (res + mod) % mod;
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequencesII().distinctSubseqII("lee"));
    }
}
