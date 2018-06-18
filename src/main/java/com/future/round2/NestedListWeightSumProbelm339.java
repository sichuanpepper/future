package com.future.round2;

import com.future.experience.aibiying.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

 Example 2:
 Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

 * Created by xingfeiy on 6/17/18.
 */
public class NestedListWeightSumProbelm339 {
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() < 1) return 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        for(NestedInteger ni : nestedList) queue.offer(ni);
        int sum = 0, level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger tmp = queue.poll();
                if(tmp.isInteger()) {
                    sum += tmp.getInteger() * level;
                } else {
                    for(NestedInteger ni : tmp.getList()) queue.offer(ni);
                }
            }
            level++;
        }
        return sum;
    }
}
