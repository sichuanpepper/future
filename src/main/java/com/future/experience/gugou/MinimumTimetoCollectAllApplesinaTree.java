package com.future.experience.gugou;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 *
 * For any node in the tree, if there's apple in any child(the child has apples or the path of child > 0), then add 2 paths.
 */
public class MinimumTimetoCollectAllApplesinaTree {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        //build the tree
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for(int[] edge : edges) {
            List<Integer> children = tree.getOrDefault(edge[0], new ArrayList<>());
            children.add(edge[1]);
            tree.put(edge[0], children);
            children = tree.getOrDefault(edge[1], new ArrayList<>());
            children.add(edge[0]);
            tree.put(edge[1], children);
        }

        return helper(tree, 0, hasApple, new HashSet<Integer>());
    }

    private int helper(Map<Integer, List<Integer>> tree, int root, List<Boolean> hasApple, HashSet<Integer> visited) {
        if(!tree.containsKey(root)) return  0;
        int fromChildren = 0;
        visited.add(root);
        for(int child : tree.get(root))  {
            if(visited.contains(child)) continue;
            int tmp = helper(tree, child, hasApple, visited);
            if(tmp > 0 || hasApple.get(child)) fromChildren += (tmp + 2);
        }

        return fromChildren;
    }

    public static void main(String[] args) {
        //7
        //[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]
        //[false,false,true,false,true,true,false]
        MinimumTimetoCollectAllApplesinaTree p = new MinimumTimetoCollectAllApplesinaTree();
        int[][] edges = new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{1, 4},
                new int[]{1, 5}, new int[]{2, 3}, new int[]{2, 6}};
        List<Boolean> hasApple = Arrays.asList(new Boolean[]{false,false,true,false,true,true,false});
        System.out.println(p.minTime(7, edges, hasApple));
    }
}
