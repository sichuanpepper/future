package com.future.experience.linying.twenty;

import com.future.experience.aibiying.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/nested-list-weight-sum-ii/
 */
public class NestedListWeightSumII {
    /**
     * @param nestedList: a list of NestedInteger
     * @return: the sum
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        // Write your code here.

        if(nestedList == null || nestedList.size() < 1) {
            return 0;
        }
        Queue<NestedInteger> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        for(NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            int curSum = 0;
            for(int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if(ni.isInteger()) {
                    curSum += ni.getInteger();
                } else {
                    for(NestedInteger next : ni.getList()) {
                        if(next != null) {
                            queue.offer(next);
                        }
                    }

                }
            }
            stack.push(curSum);
        }

        int depth = 1;
        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pop() * depth;
            depth++;
        }
        return res;
    }
}
