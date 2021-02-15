package com.future.experience.linying;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xingfeiy on 6/20/18.
 */
public class ShortWordDistance {
    /**
     * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

     For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.

     Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int pos = -1, res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if((words[i].equals(word1) || words[i].equals(word2))) {
                if(pos >= 0) {
                    res = Math.min(res, i - pos);
                }
                pos = i;
            }
        }
        return res;
    }

    /**
     * The follow up question, The only difference is now you are given the list of words and your method will be called
     * repeatedly many times with different parameters. How would you optimize it?
     *
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance2(String[] words, String word1, String word2) {
        // Write your code here
        Map<String, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            List<Integer> pos = map.getOrDefault(words[i], new ArrayList<>());
            pos.add(i);
            map.put(words[i], pos);
        }

        return findShortest(map.get(word1), map.get(word2));
    }

    private int findShortest(List<Integer> list1, List<Integer> list2) {
        int p1 = 0, p2 = 0, shortest = Integer.MAX_VALUE;
        while(p1 < list1.size() || p2 < list2.size()) {
            int val1 = p1 < list1.size() ? list1.get(p1) : Integer.MAX_VALUE;
            int val2 = p2 < list2.size() ? list2.get(p2) : Integer.MAX_VALUE;
            shortest = Math.min(shortest, Math.abs(val1 - val2));
            if(val1 < val2) {
                p1++;
            } else {
                p2++;
            }
        }
        return shortest;
    }
}
