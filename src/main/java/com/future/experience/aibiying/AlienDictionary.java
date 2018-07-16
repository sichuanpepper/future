package com.future.experience.aibiying;

import java.util.*;

/**
 Problem Description:

 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 Derive the order of letters in this language.

 For example,
 Given the following words in dictionary,

 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]
 The correct order is: "wertf".

 Note:

 You may assume all letters are in lowercase.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.


 * Created by xingfeiy on 7/14/18.
 */
public class AlienDictionary {
    /**
     * Analyze:
     * - The dictionary can generate a directed graph.
     *      - If the graph has cycle, there's no result.
     *      - No cycle, it's a DAG, and if we want to get an order of DAG, then topological sort.
     *          - Topological sort may have multiple results.
     *
     * Solution - topological sort
     *  - Build graph
     *  - Compute in-degree
     *  - Put zero-indegree nodes into queue
     *  - Poll zero-indegree nodes out from queue, and update adjacent nodes.
     *      - If the adjacent node is zero indegree after update, add it into queue.
     *  - The graph may have cycle, so we have to compare the length of result.
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        if(words == null || words.length < 2) return "";
        //build graph
        Set<Integer>[] graph = buildGraph(words);
        //compute in-degres
        int[] indegree = new int[26];
        //-1 means the character is not presented
        Arrays.fill(indegree, -1);
        for(int i = 0; i < graph.length; i++) {
            if(graph[i] == null) continue;
            indegree[i] = Math.max(0, indegree[i]);
            for(int adj : graph[i]) {
                indegree[adj] = Math.max(indegree[adj], 0) + 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int countOfChar = 0;
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) queue.add(i);
            if(indegree[i] != -1) countOfChar++;
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            sb.append((char)('a' + tmp));
            if(graph[tmp] != null) {
                for(int adj : graph[tmp]) {
                    if(--indegree[adj] == 0) queue.offer(adj);
                }
            }

        }
        return sb.length() == countOfChar ? sb.toString() : "";
    }

    private Set<Integer>[] buildGraph(String[] words) {
        Set<Integer>[] graph = new HashSet[26];
        //one word can't build any graph
        //for adjacent two words, we can only find the order from first different character.
        for(int i = 1; i < words.length; i++) {
            String preWord = words[i - 1];
            String curWord = words[i];
            int p1 = 0, p2 = 0;
            while (p1 < preWord.length() && p2 < curWord.length()) {
                if(preWord.charAt(p1) != curWord.charAt(p2)) break;
                p1++;
                p2++;
            }
            if(p1 < preWord.length() && p2 < curWord.length()) {
                if(graph[preWord.charAt(p1) - 'a'] == null) graph[preWord.charAt(p1) - 'a'] = new HashSet<>();
                graph[preWord.charAt(p1) - 'a'].add(curWord.charAt(p2) - 'a');
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        AlienDictionary a = new AlienDictionary();
        System.out.println(a.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(a.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt", "acd", "aef"}));
        System.out.println(a.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt", "acd", "aef", "aer"}));
    }
}
