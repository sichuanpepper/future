package com.future.experience.linying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 Numbers can be regarded as product of its factors. For example,

 8 = 2 x 2 x 2;
 = 2 x 4.
 Write a function that takes an integer n and return all possible combinations of its factors.

 Note:

 Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 You may assume that n is always positive.
 Factors should be greater than 1 and less than n.


 Examples:
 input: 1
 output:

 []
 input: 37
 output:

 []
 input: 12
 output:

 [
 [2, 6],
 [2, 2, 3],
 [3, 4]
 ]
 input: 32
 output:

 [
 [2, 16],
 [2, 2, 8],
 [2, 2, 2, 4],
 [2, 2, 2, 2, 2],
 [2, 4, 4],
 [4, 8]
 ]

 * Created by xingfeiy on 6/17/18.
 */
public class FactorCombinations {
    /**
     * The problem is find all seb sets from 2 to n / 2 that can product to n
     * @param n
     * @return
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(n < 1) return res;
        helper(n, 2, 1, new ArrayList<>(), res);
        return res;
    }

    private void helper(int n, int curIndex, int curProduct, List<Integer> curRes, List<List<Integer>> res) {
        if(curProduct == n) {
            res.add(new ArrayList<>(curRes));
            return;
        }
        if(curProduct > n) return;
        for(int i = curIndex; i <= n / 2; i++) {
            curRes.add(i);
            helper(n, i, curProduct * i, curRes, res);
            curRes.remove(curRes.size() - 1);
        }
    }

    public static void main(String[] args) {
        FactorCombinations p = new FactorCombinations();
        List<List<Integer>> res = p.getFactors(32);
        for(List<Integer> r : res) {
            DisplayUtils.printList(r);
        }
    }
}
