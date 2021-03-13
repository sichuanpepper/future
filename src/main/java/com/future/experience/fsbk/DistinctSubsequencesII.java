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
        return 0;
    }
}
