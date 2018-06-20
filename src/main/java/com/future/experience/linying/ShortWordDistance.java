package com.future.experience.linying;

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
        int pos1 = -1, pos2 = -1, min = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                pos1 = i;
                if(pos2 > 0) min = Math.min(min, pos1 - pos2);
            } else if(words[i].equals(word2)) {
                pos2 = i;
                if(pos1 > 0) min = Math.min(min, pos2 - pos1);
            }
        }
        return min;
    }
}
