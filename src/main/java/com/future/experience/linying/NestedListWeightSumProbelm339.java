package com.future.experience.linying;

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

    /**
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Different from the previous question where weight is increasing from root to leaf, now the weight is defined
     from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

     Example 1:
     Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

     Example 2:
     Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

     * Analyze:
     * level1   sum1
     * level2  sum2+sum1   (for level 2, one more sum1 should be added)
     * level3  sum3+sum2+sum1 (for level 3, the previous two sums should be added one more time)
     *
     * @param nestedList
     * @return
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() < 1) return 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        for(NestedInteger ni : nestedList) queue.offer(ni);
        int sum = 0, pre = 0;
        while (!queue.isEmpty()) {
            int cur = 0;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger tmp = queue.poll();
                if(tmp.isInteger()) {
                    cur += tmp.getInteger();
                } else {
                    for(NestedInteger ni : tmp.getList()) queue.offer(ni);
                }
            }
            pre += cur;
            sum += pre;
        }
        return sum;
    }
}
