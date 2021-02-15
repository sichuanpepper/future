package com.future.experience.gugou;

/**
 * 给一个01字符串， 问有多少个全是0的子串？比如110010001，有9个。2）follow up，输入变成二维的情况，有多少个sub matrix（rectangle）满足条件。
 */
public class CountZeroSubstring {
    /**
     * for n continuous '0', it has n * (n + 1) / 2 substrings that contain '0' only.
     * @param str
     * @return
     */
    public int count(String str) {
        if(str == null || str.length() < 1) return 0;
        int left = str.indexOf('0'), count = 0;
        while (left >= 0 && left < str.length()) {
            int right = left;
            while (right < str.length() && str.charAt(right) == '0') right++;
            int size = right - left;
            count += size * (size + 1) / 2;
            left = str.indexOf('0', right);
        }
        return count;
    }

    /**
     * Takeaways:
     * - How many sub-matrix exists on a m * n matrix? We already know how to count in 1D in the previous problem.
     *   In 2D, we just need count it in both vertical and horizontal, then multiply them.
     *   So the answer is n(n+1)/2 * m(m+1)/2 = mn(n+1)(m+1)/4
     * - Now the problem becomes to find the sub-matrix.
     * To find the sub-matrix, we can go through all element from left top to right bottom, todo: it's hard problem...
     *
     * https://leetcode.com/problems/count-submatrices-with-all-ones/
     * @param matrix
     * @return
     */
    public int countInMatrix(int[][] matrix) {return 0;}

    public int count1D(int[] A) {
        int res = 0, length = 0;
        for (int i = 0; i < A.length; ++i) {
            length = (A[i] == 0 ? 0 : length + 1);
            res += length;
        }
        return res;
    }

    public static void main(String[] args) {
        CountZeroSubstring p = new CountZeroSubstring();
//        System.out.println(p.count(""));
//        System.out.println(p.count("0"));
        System.out.println(p.count("00"));
        System.out.println(p.count("000"));
        System.out.println(p.count1D(new int[]{1, 1, 0, 1}));
//        System.out.println(p.count("0000"));
//        System.out.println(p.count("110010001"));
//        System.out.println(p.count("000000000"));
//        System.out.println(p.count("111111111"));
//        System.out.println(p.count("000011111"));
//        System.out.println(p.count("010011000"));
    }
}
