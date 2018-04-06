package com.future.foundation.algo;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by someone on 10/5/17.
 */
public class TopologicSorting {
    private List<Integer>[] adj;

    public TopologicSorting(int num) {
        this.adj = new ArrayList[num];
        for(int i = 0; i < num; i++) {
            this.adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int start, int end) {
        this.adj[start].add(end);
    }

    public List<Integer> topologicSort() {
        //step 1, compute the in-degree, we use an array to story the in-degree
        int[] indegree = new int[this.adj.length];
        for(List<Integer> adjs : this.adj) {
            for(int i = 0; i < adjs.size(); i++) {
                indegree[adjs.get(i)] += 1;
            }
        }

        //step 2, pick up the in-degree 0 vertex and insert into queue.
        Queue<Integer> zeroQueue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) zeroQueue.offer(i);
        }

        //step 3, poll the zero in-degree vertex out and recompute the indegree for the neighbors.
        List<Integer> res = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            int vertex = zeroQueue.poll();
            res.add(vertex);
            //re-compute the indegress for the neighbors.
            for(int v : this.adj[vertex]) {
                indegree[v] -= 1;
                if(indegree[v] == 0) {
                    zeroQueue.offer(v);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TopologicSorting g=new TopologicSorting(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Following is a Topological Sort");
        DisplayUtils.printCollection(g.topologicSort());

    }
}
