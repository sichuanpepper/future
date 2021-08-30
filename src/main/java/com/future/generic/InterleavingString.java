package com.future.generic;

/**
 * https://leetcode.com/problems/interleaving-string/
 *
 * Thoughts:
 * The straightforward way is go through the letters one by one in the s3, for each letter, try to acquire same letter from either s1 or s2, or both.
 *
 * If we think this problem start from s3, the problem actually is to check if s3 can be break down into a collection of valid segments.
 * Here the valid segment means this segment is a prefix of a substring from s1 or s2.
 * So the problem is similar as word break.
 *
 */
public class InterleavingString {
    /**
     * The straightforward way, it works but the performance is not good.
     * It's easy to see there are a lot of duplicated computation, one way to improve that is using cache.
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() < 1) {
            return s2.equals(s3);
        }
        if(s2.length() < 1) {
            return s1.equals(s3);
        }
        if(s3.length() < 1) {
            return false;
        }

        if(s1.charAt(0) == s3.charAt(0) && isInterleave(s1.substring(1), s2, s3.substring(1))) {
            return true;
        }

        if(s2.charAt(0) == s3.charAt(0) && isInterleave(s1, s2.substring(1), s3.substring(1))) {
            return true;
        }
        return false;
    }


    /**
     * To add cache, we need to think about what are the sub-problems have been recomputed.
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveWithCache(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1 + len2 != s3.length()) {
            return false;
        }
        return helper(s1, s2, s3, 0, 0, 0, new boolean[s1.length() + 1][s2.length() + 1]);
    }

    private boolean helper(String s1, String s2, String s3, int p1, int p2, int p3, boolean[][] invalid) {
        if(invalid[p1][p2]) {
            return false;
        }

        if(p3 >= s3.length()) {
            return true;
        }

        boolean res = (p1 < s1.length() && s1.charAt(p1) == s3.charAt(p3) && helper(s1, s2, s3, p1 + 1, p2, p3 + 1, invalid)) ||
                (p2 < s2.length() && s2.charAt(p2) == s3.charAt(p3) && helper(s1, s2, s3, p1, p2 + 1, p3 + 1, invalid));
        if(!res) {
            invalid[p1][p2] = true;
        }

        return res;
    }

    public static void main(String[] args) {
        InterleavingString p = new InterleavingString();
        // bcc, dbbca, dbbbaccc
        // bcc, ca, baccc
        // cc, ca, accc
        System.out.println(p.isInterleaveWithCache("aabcc", "dbbca", "aadbbcbcac"));
    }
}
