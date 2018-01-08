package com.future.round2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-ii/description/

 There are a total of n courses you have to take, labeled from 0 to n - 1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

 There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

 For example:

 2, [[1,0]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

 4, [[1,0],[2,0],[3,1],[3,2]]
 There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2
 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

 Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.

 * Created by xingfeiy on 1/6/18.
 */
public class Problem210 {
    /**
     * Analyze:
     * It's the similar problem with Problem 207, we still can use topologic algorithm to solve it.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses < 1) return new int[0];
        int[] indegree = new int[numCourses];
        List<Integer>[] adjacents = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) adjacents[i] = new ArrayList<>();

        //calculate indegree and adjacents.
        for(int[] token : prerequisites) {
            indegree[token[0]]++;
            adjacents[token[1]].add(token[0]);
        }

        Queue<Integer> zeroQueue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) zeroQueue.offer(i);
        }

        int[] res = new int[numCourses];
        int finished = 0;
        while (!zeroQueue.isEmpty()) {
            int tmp = zeroQueue.poll();
            res[finished++] = tmp;
            //update adjacents.
            for(int adj : adjacents[tmp]) {
                indegree[adj]--;
                if(indegree[adj] == 0) zeroQueue.offer(adj);
            }
        }
        return finished == numCourses ? res : new int[0];
    }

}
