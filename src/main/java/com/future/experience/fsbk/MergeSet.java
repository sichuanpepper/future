package com.future.experience.fsbk;

import java.util.*;

/**
 * Created by xingfeiy on 5/19/18.
 */
public class MergeSet {

    /**
     * We are give a list, each element is a integer set without duplication.
     * Merge two sets if they contains same number.
     * example:
     * [1, 3], [2, 3, 4], [4, 5], [6, 7], [8], [8]
     * return [1, 2, 3, 4, 5], [6, 7], [8]
     *
     * Analyze:
     * It likes the friends group problem, if A knows B, and B knows C, then A, B and C are in same group.
     * There are two common algorithm to solve such kind of issues, Union-Find, Graph
     * Time Complexity: O(n)
     *  - create graph O(n), where n is the total numbers in list.
     *  - BFS, O(n)
     * Space Complexity: O(n)
     *  - create graph O(n)
     *  - BFS O(n)
     * @param list
     * @return
     */
    public List<Set<Integer>> merge(List<int[]> list) {
        //build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int[] group : list) {
            for(int i = 0; i < group.length; i++) {
                Set<Integer> neighbors = graph.getOrDefault(group[i], new HashSet<>());
                //you don't have to add all neighbors, just need add the closest neighbor, and you are able to find all.
                if(i > 0) {
                    neighbors.add(group[i - 1]);
                    graph.get(group[i - 1]).add(group[i]);
                }
                graph.put(group[i], neighbors);

            }
        }

        //BFS traversal graph

        Set<Integer> visited = new HashSet<>();
        List<Set<Integer>> res = new ArrayList<>();
        for(Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
            if(visited.contains(entry.getKey())) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(entry.getKey());
            Set<Integer> curRes = new HashSet<>();
            while (!queue.isEmpty()) {
                int val = queue.poll();
                curRes.add(val);
                visited.add(val);
                for(int neighbor : graph.get(val)) {
                    if(!visited.contains(neighbor)) queue.offer(neighbor);
                }
            }
            res.add(curRes);
        }
        return res;
    }


    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 3});
        list.add(new int[]{2, 3, 4});
        list.add(new int[]{4, 5});
        list.add(new int[]{6, 7});
        list.add(new int[]{8});
        list.add(new int[]{8});
        MergeSet p = new MergeSet();
        List<Set<Integer>> res = p.merge(list);
        for(Set<Integer> set : res) {
            for(int s : set) {
                System.out.print(s + ", ");
            }
            System.out.println();
        }
    }
}
