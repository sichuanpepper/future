package com.future.foundation.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by someone on 6/15/17.
 */
public class GraphUtils {
    private ArrayList<Integer>[] adjcents;

    private int size;

    public GraphUtils(int size) {
        this.size = size;
        adjcents = new ArrayList[this.size];
        for(int i = 0; i < this.size; i++) {
            adjcents[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        adjcents[from].add(to);
    }

    public void printDFS(int vertex) {
        boolean[] visited = new boolean[this.size];
        dfsHelper(vertex, visited);
    }

    public void dfsHelper(int vertex, boolean[] visited) {
        if(visited[vertex]) return;
        visited[vertex] = true;
        System.out.println(vertex);
        for(int i = 0; i < this.adjcents[vertex].size(); i++) {
            dfsHelper(this.adjcents[vertex].get(i), visited);
        }
    }

    public void printBFS(int vertex) {
        boolean[] visited = new boolean[this.size];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.println(v);
            visited[v] = true;
            for(int i = 0; i < this.adjcents[v].size(); i++) {
                if(!visited[this.adjcents[v].get(i)]) queue.offer(this.adjcents[v].get(i));
            }
        }
    }


    public static void main(String[] args) {
        GraphUtils g = new GraphUtils(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(3, 1);

        g.printDFS(2);
        System.out.println("===========");
        g.printBFS(2);
    }
}
