package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-primes/description/
 *
 * Description:

 Count the number of prime numbers less than a non-negative number, n.



 * Created by someone on 12/6/17.
 */
public class Problem204 {
    /**
     * Analyze:
     * The sieve of Eratosthenes is a simple, ancient algorithm for finding all prime numbers up to any given limit.
     * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
     *
     * Input: an integer n > 1.
     * Let A be an array of Boolean values, indexed by integers 2 to n,
     * initially all set to true.
     * for i = 2, 3, 4, ..., not exceeding √n:
     * if A[i] is true:
     * for j = i2, i2+i, i2+2i, i2+3i, ..., not exceeding n:
     * A[j] := false.
     * Output: all i such that A[i] is true.
     *
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if(n <= 2) return 0;
        boolean prime[] = new boolean[n];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for(int i = 2; i * i < n; i++) {
            if(!prime[i]) continue;
            int j = 2;
            while (j * i < n) {
                prime[j++ * i] = false;
            }
        }
        int count = 0;
        for(boolean check : prime) {
            if(check) count++;
        }
        return count;

    }

    /**
     * Beats 50%
     * @param n
     * @return
     */
    public int countPrimesV2(int n) {
        if(n <= 2) return 0;
        boolean prime[] = new boolean[n];
        Arrays.fill(prime, true);
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(!prime[i]) continue;
            count++;
            int start = 2;
            while (start * i < n) prime[start++ * i] = false;
        }
        return count;
    }
}
