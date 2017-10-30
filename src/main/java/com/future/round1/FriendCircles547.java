package com.future.round1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by someone on 6/7/17.
 */
public class FriendCircles547 {
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for(int i = 0; i< M.length; i++) {
            if(visited[i]) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int head = queue.poll();
                if(visited[head]) {
                    continue;
                }
                for(int j = i + 1; j < M.length; j++) {
                    if(head != j && M[head][j] == 1) {
                        queue.offer(j);
                    }
                }
            }
            count++;
        }
        return count;
    }



}
