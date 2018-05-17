package com.future.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 5/16/18.
 */
public class UndirectedGraphNode {
    public int label;

    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}
