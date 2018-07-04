package com.future.round2;

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

 * Created by xingfeiy on 6/12/18.
 */
public class AlienDictionary {
    /**
     * Analyze:
     * Basically, it's a directed graph, and we want to output all elements by certain order.
     * Toplogical sort?
     * - Build graph
     * - In-degree
     * - pop out 0 in-degree elements and update in-degree
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        if(words == null || words.length < 2) return "";
        List<Integer>[] adjacentNodes = buildGraph(words);
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        for(int i = 0; i < adjacentNodes.length; i++) {
            if(adjacentNodes[i] == null) continue;
            indegree[i] = Math.max(indegree[i], 0);
            for(int adj : adjacentNodes[i]) {
                indegree[adj] = Math.max(indegree[adj], 0) + 1;
            }
        }

        Queue<Integer> zeroQueue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) zeroQueue.offer(i);
        }
        StringBuilder sb = new StringBuilder();
        while (!zeroQueue.isEmpty()) {
            int tmp = zeroQueue.poll();
            sb.append((char)('a' + tmp));
            if(adjacentNodes[tmp] != null) {
                for(int adj : adjacentNodes[tmp]) {
                    indegree[adj]--;
                    if(indegree[adj] == 0) {
                        zeroQueue.offer(adj);
                    }
                }
            }

        }
        return sb.toString();
    }

    /**
     * - We can't get any transition in single word.
     * - For any adjacent two words, if they are not same, we can get one transition from first different character in these two words.
     * @param words
     * @return
     */
    private List<Integer>[] buildGraph(String[] words) {
        List<Integer>[] adjacentNodes = new ArrayList[26];
        for(int i = 1; i < words.length; i++) {
            if(words[i].equals(words[i - 1])) continue;
            if(words[i].length() < 1 || words[i - 1].length() < 1) continue;
            //find first different character
            int p1 = -1, p2 = -1;
            while (p1 < words[i - 1].length() - 1 && p2 < words[i].length() - 1) {
                if(words[i - 1].charAt(++p1) != words[i].charAt(++p2)) break;
            }
            if(adjacentNodes[words[i - 1].charAt(p1) - 'a'] == null)
                adjacentNodes[words[i - 1].charAt(p1) - 'a'] = new ArrayList<>();
            adjacentNodes[words[i - 1].charAt(p1) - 'a'].add(words[i].charAt(p2) - 'a');
        }
        return adjacentNodes;
    }

    public static void main(String[] args) {
        AlienDictionary p = new AlienDictionary();
        System.out.println(p.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"})); //wertf
    }
}
