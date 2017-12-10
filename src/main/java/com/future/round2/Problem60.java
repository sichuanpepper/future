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
     * As the example above, that's something like a sorted array, each number n has (n-1)! permutations.
     * So k / (n - 1)!, we can find the first number, which is k / (n - 1)! + k % (n - 1)! > 0 ? 1 : 0
     * Then, we just need find the left in (n - 1) permutations, the left is k % (n - 1)! == 0 ? (n - 1)! : k % (n - 1)!
     *
     * For example, we're going to find 4th in above sequence.
     * 1 * (n-1)!
     * 2 * (n-1)!
     * 3 * (n-1)!
     *
     * We can know there are 2 permutations in each line, so we can find the line easily, 4 / 2 = 2,
     *
     * @param n
     * @param k
     * @return
     */
//    public String getPermutation(int n, int k) {
//        int[] factorial = new int[n + 1];
//        factorial[0] = 1;
//        for(int i = 1; i <= n; i++) {
//            factorial[i] = factorial[i - 1] * i;
//        }
//
//        List<Integer> list = new ArrayList<>();
//        for(int i = 1; i <= n; i++) {
//            list.add(i);
//        }
//
//        k--;
//        String res = "";
//        for(int i = 1; i <= n; i++) {
//            int val = k / factorial[n - 1];
//            res += list.get(val);
//            k = k - val * factorial[n - 1];
//            list.remove(val);
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Problem60 p = new Problem60();
//        System.out.println(p.getPermutation(3, 1));
//    }
//

}
