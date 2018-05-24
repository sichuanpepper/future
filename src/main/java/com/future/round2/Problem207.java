package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule/description/
 *
 There are a total of n courses you have to take, labeled from 0 to n - 1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 For example:

 2, [[1,0]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

 2, [[1,0],[0,1]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you
 should also have finished course 1. So it is impossible.

 Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.

 * Created by xingfeiy on 1/5/18.
 */
public class Problem207 {
    /**
     * Analyze:
     * It's a Topologic problem.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses < 0 || prerequisites == null || prerequisites.length < 1) return true;
        //step 1, calculate in-degree for each course
        int[] indegree = new int[numCourses];
        List<Integer>[] adjacents = new ArrayList[numCourses];
        //*******IMPORTANT, don't fill an array with an object..., all elements share same object here.
//        Arrays.fill(adjacents, new ArrayList<>());
        for(int i = 0; i < adjacents.length; i++) adjacents[i] = new ArrayList<>();

        for(int[] token : prerequisites) {
            indegree[token[1]]++;
            adjacents[token[0]].add(token[1]);
        }

        //step 2, build zero-degree queue
        Queue<Integer> zeroQueue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) zeroQueue.offer(i);
        }

        int finished = 0;
        while (!zeroQueue.isEmpty()) {
            int tmp = zeroQueue.poll();
            finished++;
            //take this course out and update prerequisited courses.
            for(int course : adjacents[tmp]) {
                indegree[course]--;
                if(indegree[course] == 0) zeroQueue.offer(course);
            }
        }

        return finished == numCourses;

    }

    public static void main(String[] args) {
        Problem207 p  = new Problem207();
//        int[][] prerequisites = new int[3][2];
//        prerequisites[0] = new int[]{0, 2};
//        prerequisites[1] = new int[]{1, 2};
//        prerequisites[2] = new int[]{2, 0};
        int[][] prerequisites = new int[1][2];
        prerequisites[0] = new int[]{0, 1};
        System.out.println(p.canFinish(2, prerequisites));
    }
}
