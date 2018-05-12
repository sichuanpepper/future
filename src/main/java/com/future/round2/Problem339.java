package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/evaluate-division/description/
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist,
 * return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].


 * Created by xingfeiy on 5/1/18.
 */
public class Problem339 {

    private void buildGraph(String a, String b, double val, Map<String, Map<String, Double>> map) {
        if(map.containsKey(a)) {
            map.get(a).put(b, val);
        } else {
            Map<String, Double> tmp = new HashMap<>();
            tmp.put(b, val);
            map.put(a, tmp);
        }
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        //build graph
        for(int i = 0; i < equations.length; i++) {
            String a = equations[i][0];
            String b = equations[i][1];
            double val = values[i];
            buildGraph(a, b, 1.0 / val, graph);
            buildGraph(b, a, val, graph);
        }

        //dfs
        double[] res = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            res[i] = dfsSearch(queries[i][1], queries[i][0], graph, new HashSet<>());
        }
        return res;
    }

    private double dfsSearch(String a, String b, Map<String, Map<String, Double>> graph, Set<String> visited) {
        if(!graph.containsKey(a) || !graph.containsKey(b)) return -1.0;
        if(a.equals(b)) return 1.0;
        visited.add(a);
        if(graph.get(a).containsKey(b)) return graph.get(a).get(b);
        for(String neighbor : graph.get(a).keySet()) {
            if(visited.contains(neighbor)) continue;
            double val = dfsSearch(neighbor, b, graph, visited) * graph.get(a).get(neighbor);
            if(val > 0) return val;
        }
        return -1.0;
    }


    public static void main(String[] args) {
        String[][] equations = new String[2][2];
        equations[0] = new String[]{"a", "b"};
        equations[1] = new String[]{"b", "c"};
        double[] values = new double[]{2.0, 3.0};
        String[][] queries = new String[1][2];
        queries[0] = new String[]{"a", "c"};
        Problem339 p = new Problem339();
        System.out.println(p.calcEquation(equations, values, queries)[0]);

    }
}
