package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/description/
 *
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


 Example 1:

 Input: k = 3, n = 7

 Output:

 [[1,2,4]]

 Example 2:

 Input: k = 3, n = 9

 Output:

 [[1,2,6], [1,3,5], [2,3,4]]
 * Created by xingfeiy on 12/12/17.
 */
public class Problem216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(n < 6) return res;
        helper(1, k, n, new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int start, int k, int n, List<Integer> curVal, List<List<Integer>> res) {
        if(curVal.size() == k) {
            if(n == 0) res.add(new ArrayList<>(curVal));
            return;
        }

        for(int i = start; i < 10; i++) {
            curVal.add(i);
            helper(i + 1, k, n - i, curVal, res);
            curVal.remove(curVal.size() - 1);
        }
    }
}
