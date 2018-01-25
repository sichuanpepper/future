package com.future.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/description/
 *
 *
 Given numRows, generate the first numRows of Pascal's triangle.

 For example, given numRows = 5,
 Return

    [
            [1],
           [1,1],
          [1,2,1],
         [1,3,3,1],
        [1,4,6,4,1]
    ]
 * Created by xingfeiy on 1/24/18.
 */
public class Problem118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows < 1) return res;
        List<Integer> preLevel = new ArrayList<>();
        preLevel.add(1);
        res.add(preLevel);
        for(int i = 2; i <= numRows; i++) {
            List<Integer> curLevel = new ArrayList<>();
            curLevel.add(1);
            for(int j = 1; j < i; j++) {
                curLevel.add(preLevel.get(j - 1) + (j >= preLevel.size() ? 0 : preLevel.get(j)));
            }
            res.add(curLevel);
            preLevel = curLevel;
        }
        return res;
    }
}
