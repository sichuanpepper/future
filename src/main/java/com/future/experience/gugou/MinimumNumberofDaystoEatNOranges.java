package com.future.experience.gugou;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/
 */
public class MinimumNumberofDaystoEatNOranges {
    /**
     * The problem can be converts to a tree problem, each node has at most three children.
     *
     * @param n
     * @return
     */
    public int minDays(int n) {
        if(n == 1) return 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        Set<Integer> visited = new HashSet<>();
        int res = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int tmp = queue.poll();
                if(tmp == 1) return res;

                if(visited.contains(tmp)) {
                    continue;
                }
                visited.add(tmp);
                if(tmp % 2 == 0) {
                    queue.offer(tmp / 2);
                }
                if(tmp % 3 == 0) {
                    queue.offer(tmp / 3);
                }
                queue.offer(tmp - 1);
            }
            res++;
        }
        return res;
    }
}
