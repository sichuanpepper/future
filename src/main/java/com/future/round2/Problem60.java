package com.future.round2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutation-sequence/description/
 *
 The set [1,2,3,…,n] contains a total of n! unique permutations.

 By listing and labeling all of the permutations in order,
 We get the following sequence (ie, for n = 3):

 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 Given n and k, return the kth permutation sequence.

 Note: Given n will be between 1 and 9 inclusive.

 * Created by xingfeiy on 12/9/17.
 */
public class Problem60 {
    /**
     * Analyze:
     * The brute force solution is compute all possible permutations one by one, and find the kth permutation.
     *
     * As we know, for any number n, there are (n-1)! permutations which leading with each number from 1 to n.
     *
     * And all permutations sorted in ascending, so, given a k, it's easy to find first number.
     *
     * We can know there are 2 permutations in each line, so we can find the line easily, 4 / 2 = 2, do it recursively
     *
     * As the example above, we are given n = 3, and if we want to find 4th,
     *
     * We know there are (n-1)! permutations which consist of n digital per line, here n =3, so we have 2*1 per line.
     *
     * One line means all permutations which leading with one of digital from 1 to n, here
     *
     * 123, 132
     * 213, 231
     * 312, 321
     *
     * k/(n-1)! can find the line, here 4/2 = 2, so we got first digital 2, then we have 2 digital left, so the left thing
     * is find left k in line 2, how many k left? k - (line - 1) * (n-1)!, here left k = 4 - (2-1) * 2 =2
     * So the problem became to small problem, find the 2th in (1, 3)'s permutations.
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for(int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            list.add(i);
        }

        String res = "";
        for(int i = n; i > 0; i--) {
            int val = k / factorial[i - 1] + (k % factorial[i - 1] == 0 ? -1 : 0); //find the fist
            res += list.get(val);
            k = k - val * factorial[i - 1];
            list.remove(val);
        }

        return res;
    }

    public static void main(String[] args) {
        Problem60 p = new Problem60();
        System.out.println(p.getPermutation(3, 6));
    }


}
