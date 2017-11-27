package com.future.round2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without
 * any intervening characters.

 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]

 You should return the indices: [0,9].
 (order does not matter).
 *
 * Created by xingfeiy on 11/26/17.
 */
public class Problem30 {
    /**
     * Analyze:
     * Two algorithms in my mind which are back tracking and DP.
     *
     * - Back tracking
     *  We can build a word tree, and do the DFS.
     *
     * The back tracking is too heavy.
     *
     * Alternative solution:
     * Use two maps.
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || words == null || s.length() < 1 || words.length < 1) return res;

        //there could be duplicated words.
        Map<String, Integer> wordMap = new HashMap<>();
        for(String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        int wordLength = words[0].length();
        for(int i = 0; i <= s.length() - wordLength * words.length; i++) {
            Map<String, Integer> found = new HashMap<>();
            int foundLength = 0;
            int index = i;
            while(foundLength <= wordLength * words.length) {
                String wordFound = s.substring(index, index + wordLength);
                if(wordMap.containsKey(wordFound)) {
                    found.put(wordFound, found.getOrDefault(wordFound, 0) + 1);
                    if(found.get(wordFound) > wordMap.get(wordFound)) break;
                    foundLength += wordLength;
                } else {
                    break;
                }
                if(foundLength == wordLength * words.length) {
                    res.add(i);
                    break;
                }
                index = index + wordLength;
            }
        }

        return res;
    }
}
