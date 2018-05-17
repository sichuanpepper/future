package com.future.experience.facebook;

import com.future.utils.UndirectedGraphNode;

import java.util.HashMap;
import java.util.Map;


/**
 * https://leetcode.com/problems/clone-graph/description/
 *
 * Created by xingfeiy on 5/16/18.
 */
class CloneGraph {
    /**
     * DFS
     * Time complexity O(n), space complexity O(n)
     * beats 100%
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return helper(node);
    }

    private  Map<Integer, UndirectedGraphNode> map = new HashMap<>();
    private UndirectedGraphNode helper(UndirectedGraphNode node) {
        if(node == null) return null;
        if(map.containsKey(node.label)) return map.get(node.label);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node.label, clone);
        for(UndirectedGraphNode n : node.neighbors) {
            clone.neighbors.add(helper(n));
        }
        return clone;
    }
}
