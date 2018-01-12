package com.future.round2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 This is a follow up of Shortest Word Distance(Problem 243).
 The only difference is now you are given the list of words and your method will be called repeatedly many
 times with different parameters. How would you optimize it?

 Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
 and word2 and return the shortest distance between these two words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.

 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

 * Created by xingfeiy on 1/4/18.
 */
public class Problem244 {
    private Map<String, List<Integer>> map = new HashMap<>();
    /**
     * Analyze:
     * Since the method shortest will be called many times, so we can't do it likes problem 243.
     * Here we can use a map to store each word with its positions.
     *
     * But the problem here is: if the given array includes only two words, and each word has 10000 replicas.
     * likes: "make"->[0,1,3,...,1999], "coding"->[2,4,...,1998], it takes time complexity O(nlog(n)) by binary search.
     * we can optimize it by some idea similar with merge sort.
     * @param words
     */
    public Problem244(String[] words) {
        for(int i = 0; i < words.length; i++) {
            List<Integer> array = map.getOrDefault(words[i], new ArrayList<>());
            array.add(i);
            map.put(words[i], array);
        }
    }

    public int shortest(String word1, String word2){
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int min = Integer.MAX_VALUE;
        int p1 = 0, p2 = 0;
        while (p1 < list1.size() && p2 < list2.size()) {
            min = Math.min(min, Math.abs(list1.get(p1) - list2.get(p2)));
            if(list1.get(p1) < list2.get(p2)) {
                p1++;
            } else {
                p2++;
            }
        }
        return min;
    }
}
