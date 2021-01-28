package com.future.experience.gugou;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 */
public class CourseSchedule {
    /**
     To finish it, it must be a tree structure.
     **/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] p : prerequisites) {
            indegree[p[1]]++;
            List<Integer> neighbor = map.getOrDefault(p[0], new ArrayList<>());
            neighbor.add(p[1]);
            map.put(p[0], neighbor);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int finished = 0;
        while(!queue.isEmpty()) {
            int course = queue.poll();
            if(map.containsKey(course)) {
                for(int neighbor : map.get(course)) {
                    if(--indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
            finished++;
        }
        return finished == numCourses;
    }
}
