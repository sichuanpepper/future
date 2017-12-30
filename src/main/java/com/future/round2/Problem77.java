package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/description/
 *
 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 For example,
 If n = 4 and k = 2, a solution is:

 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]

 * Created by xingfeiy on 12/29/17.
 */
public class Problem77 {
    /**
     * Analyze:
     * Back tracking
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void helper(int start, int n, int k, List<Integer> curVal, List<List<Integer>> res) {
        if(curVal.size() == k) {
            res.add(new ArrayList<>(curVal));
            return;
        }
        if(start > n) return;
        for(int i = start; i <= n; i++) {
            curVal.add(i);
            helper(i + 1, n, k, curVal, res);
            curVal.remove(curVal.size() - 1);
        }
    }
}
