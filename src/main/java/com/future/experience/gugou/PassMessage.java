package com.future.experience.gugou;

import java.util.*;

/**
 * 假设你是几个村子的领导，有一条新闻你想要发布给所有村民。新闻可以通过一个村民传播给另一个村民。给定你花了多少时间把新闻传播给某些村民，
 * 给定一个村民花了多少时间把新闻传播给其他村民。求所有村民收到新闻的最短时间。
 * 一些假定：一个村民仅且只能被另一个村民通知到。 Follow up：如果一个村民能被多个村民通知到新闻呢？
 * 类似蠡口要散气流，但面馆没有给具体的method signature, 他问了用什么样的数据结构来代表inpu, output, etc.
 *
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/
 */
public class PassMessage {

    /**
     * Let's say there are n people, and represents as 0 to n-1 respectively.
     * one -> many, List<int[]> represents the
     * @return
     */
    public int timeToInformAllPeople() {
        return 0;
    }

    /**
     * https://leetcode.com/problems/time-needed-to-inform-all-employees/
     *
     - Unique ID: 0 to n - 1
     - Only one head
     - One direct manager
     - Tree structure
     **/
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        //build tree, Map -> tree, get head
        Map<Integer, List<Integer>> tree = new HashMap<>();
        int head = -1;
        for(int i = 0; i < n; i++) {
            int managerId = manager[i];
            if(managerId == -1) {
                head = i;
            }
            List<Integer> children = tree.getOrDefault(managerId, new ArrayList<>());
            children.add(i);
            tree.put(managerId, children);
        }

        //bfs, node(id, elpsed), find the max elapsed
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{head, 0});
        int max = 0;
        while(!queue.isEmpty()) {
            int[] pair = queue.poll();
            max = Math.max(max, pair[1]);
            if(tree.containsKey(pair[0])) {
                for(int child : tree.get(pair[0])) {
                    queue.offer(new int[]{child, pair[1] + informTime[pair[0]]});
                }
            }
        }
        return max;
    }
}
