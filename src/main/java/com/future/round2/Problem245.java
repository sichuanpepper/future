package com.future.round2;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Input: word1 = “makes”, word2 = “coding”
 * Output: 1
 * Input: word1 = "makes", word2 = "makes"
 * Output: 3
 * Note:
 * You may assume word1 and word2 are both in the list.
 */
public class Problem245 {
    /**
     * Compare to problem 243, the difference is word1 may equal to word2.
     *
     * We can still traversal elements one by one from left to right, for each movement we care about only previous valid pointer.
     * - valid pointer, points either word1 or word2
     * - calculate min or reset pointer.
     *  - word1 equals to word2
     *      - cal min && reset pointer
     *  - word1 not equals to word2
     *      - cal min && reset pointer
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int idx = -1, res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1) || words[i].equals(word2)) {
                if(idx >= 0 && (word1.equals(word2) || !words[i].equals(words[idx]))) {
                    res = Math.min(res, i - idx + 1);
                }
                idx = i;
            }
        }
        return res;
    }
}
