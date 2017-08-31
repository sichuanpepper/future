package com.future.foundation.backtracking;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 7/26/17.
 */
public class Coloring {
    /**
     * http://www.geeksforgeeks.org/backttracking-set-5-m-coloring-problem/
     * @param graph
     * @param m
     * @return
     */
    public int[] color(int[][] graph, int m) {
        int[] res = new int[graph.length];
        helper(graph, 0, res, m);
        return res;
    }

    private void helper(int[][] graph, int vertex, int[] res, int m) {
        if(vertex == graph.length) {
            DisplayUtils.printArray(res);
            return;
        }

        for(int i = 1; i < m + 1; i++) {
            if(isGood(graph, vertex, i, res)) {
                res[vertex] = i;
                helper(graph, vertex + 1, res, m);
                res[vertex] = 0;
            }
        }
    }

    private boolean isGood(int[][] graph, int vertex, int m, int[] res) {
        for(int i = 0; i < graph.length; i++) {
            if(i == vertex) {
                continue;
            }
            if(graph[vertex][i] == 1 && res[i] == m) {
                return false;
            }
        }
        return true;
    }
}
