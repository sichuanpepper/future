package com.future.experience.gugou;

/**
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
 */
public class MinimumNumberofKConsecutiveBitFlips {
    /**
     * todo: hard...
     * The leftmost element has only one way to flip
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        int res = 0;
        for(int i = 0; i < A.length; i++) {
            if(A[i] == 0 && i + K <= A.length) {
                for(int j = 0; j < K; j++) {
                    A[i + j] ^= 1;
                }
                res++;
            }
            if(i + K >= A.length) {
                int n = i;
                while (n < A.length) {
                    if(A[n++] == 0) {
                        return -1;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        MinimumNumberofKConsecutiveBitFlips p = new MinimumNumberofKConsecutiveBitFlips();
        System.out.println(p.minKBitFlips(new int[]{0, 1, 0}, 1));
        /**
         * Input: A = [0,0,0,1,0,1,1,0], K = 3
         * Output: 3
         * Explanation:
         * Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
         * Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
         * Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]
         */
        p.minKBitFlips(new int[]{0,0,0,1,0,1,1,0}, 3);

        System.out.println(1 ^ 1);
        System.out.println(0 ^ 1);
        System.out.println(0 ^ 0);
    }
}
