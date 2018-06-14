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
    public String alienOrder(String[] words) {
        if(words == null || words.length < 2) return "";
        List<Integer>[] adjacent = new ArrayList[26];
        for(int i = 0; i < 26; i++) adjacent[i] = new ArrayList<>();
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);   //-1 means no this character in dictionary
        //init adjacent, for any adjacent two words, we can only know the relationship between first different character in two words.
        // example, wrte && wrfk, we will know 't' must be first of 'f', but 'e' and 'k', we don't know.
        for(int i = 1; i < words.length; i++) {
            int p1 = 0, p2 = 0;
            while (p1 < words[i - 1].length() && p2 < words[i].length() && (words[i - 1].charAt(p1) == words[i].charAt(p2))) {
                p1++;
                p2++;
            }
            if(p1 < words[i - 1].length() && p2 < words[i].length()) {

            }

        }


        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) queue.offer(i);
        }

        String res = "";
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            res += (char)('a' + tmp);
            for(int adj : adjacent[tmp]) {
                indegree[adj]--;
                if(indegree[adj] == 0) {
                    queue.offer(adj);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        AlienDictionary p = new AlienDictionary();
        System.out.println(p.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"})); //wertf
    }
}
