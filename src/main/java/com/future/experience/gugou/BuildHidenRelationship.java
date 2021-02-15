package com.future.experience.gugou;

import java.util.*;

/**
 * 知道两条rule， #1 朋友的朋友是朋友， #2 敌人的敌人是朋友。
 * 给你一串输入:  1 2 F, 2 3 F,  4 5  E,  5 6 E, 等等。。。
 * 前面两个数字表示两个人，第三个f, e 表示关系。
 * 根据两条rule，构建出所有隐藏的关系。
 *
 * For the given example:
 * 1 2 F, 2 3 F, 4, 5 E, 5 6 E
 * The hidden relationships are: 1 3 F, 4 6 F
 *
 * Questions need to clarify:
 *  1 2 F, 2 3 F, 3 4 F, are 1 and 4 are friend?  - Yes, 1 and 4 are friends.
 *  1 2 F, 2 3 F, 1 3 E, what's the relationship between 1 and 3? friend or enemy? - Assume the inputs are valid, it won't happen.
 *  1 2 F, 2 3 E, what's the relationship between 1 and r? - Assume the inputs are valid, it won't happen
 */
public class BuildHidenRelationship {
    /**
     * Friend relationship may generate hidden friend relationship
     * Enemy relationship my generate hidden friend relationship
     * So the hidden relationship only can be friend...
     *
     * First we can build enemy graph -> hidden friend relationships
     *      - Each node, its neighbors are hidden friends.
     * Second, build friend relationship
     *      - All nodes in same graph are friends
     *
     * A straightforward way is build graphs and do the BFS or DFS for each node.
     * @param relationships
     * @return
     */
    public int[] findHiddenRelationship(int[][] relationships) {
        List<int[]> friends = new ArrayList<>();
        List<int[]> enemys = new ArrayList<>();
        for(int[] relation : relationships) {
            if(relation[2] == 'F') {
                friends.add(relation);
            } else {
                enemys.add(relation);
            }
        }

        RelationNode enemyGraph = buildGraph(enemys);
        return null;
    }


    private RelationNode buildGraph(List<int[]> relations) {
         RelationNode graph = null;
         Map<Integer, RelationNode> map = new HashMap<>();
         for(int[] relation : relations) {
             RelationNode first = map.getOrDefault(relation[0], new RelationNode(relation[0]));
             RelationNode second = map.getOrDefault(relation[1], new RelationNode(relation[1]));
             if(graph == null) {
                 graph = first;
             }
             first.neighbors.add(second.id);
             second.neighbors.add(first.id);
         }
         return graph;
    }

    private class RelationNode {
        public int id;

        public Set<Integer> neighbors;

        public RelationNode(int id) {
            this.id = id;
        }

    }

    public static void main(String[] args) {
        Set<int[]> set = new HashSet<>();
        set.add(new int[]{1, 2});
        if(set.contains(new int[]{1, 2})) {
            System.out.println("found");
        } else {
            System.out.println("Not found");
        }

        int[] other = new int[]{3, 4};
        set.add(other);
        if(set.contains(other)) {
            System.out.println("found");
        } else {
            System.out.println("Not found");
        }
    }
}
