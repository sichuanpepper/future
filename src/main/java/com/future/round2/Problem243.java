package com.future.round2;

/**
 Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

 For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.

 Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

 * Created by xingfeiy on 1/3/18.
 */
public class Problem243 {
    /**
     * Analyze:
     * - The given list may contains duplicates, or may not.
     * We can use two pointers, pointer1 points to latest word1, and pointer2 points to latest word2.
     * When we meet either word1 or word2 in list, calculate the shortest path and then move corresponding pointer.
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int pointer1 = -1, pointer2 = -1, min = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                pointer1 = i;
                if(pointer2 >= 0) min = Math.min(min, i - pointer2);
            } else if(words[i].equals(word2)) {
                pointer2 = i;
                if(pointer1 >= 0) min = Math.min(min, i - pointer1);
            }
        }
        return min;
    }
}
