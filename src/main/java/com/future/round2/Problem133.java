package com.future.round2;

import com.future.utils.UndirectedGraphNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/clone-graph/discuss/
 *
 * Created by xingfeiy on 5/16/18.
 */
public class Problem133 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return helper(node);
    }

    private Map<Integer, UndirectedGraphNode> map = new HashMap<>();
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
