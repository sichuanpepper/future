package com.future.round2;

import com.future.experience.aibiying.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *
 * Input: the list [[1,1],2,[1,1]],
 * Output: 10.
 * Explanation:
 * four 1's at depth 2, one 2 at depth 1, 4 * 1 * 2 + 1 * 2 * 1 = 10
 * Example 2:
 *
 * Input: the list [1,[4,[6]]],
 * Output: 27.
 * Explanation:
 * one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27
 */
public class Problem339 {
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        int depth = 1, sum = 0;
        for(NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if(ni.isInteger()) {
                    sum += ni.getInteger() * depth;
                } else {
                    for(NestedInteger tmp : ni.getList()) {
                        queue.offer(tmp);
                    }
                }
            }
            depth++;
        }
        return sum;
    }


    public static void main(String[] args) {}


}
