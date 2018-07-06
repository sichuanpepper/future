package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.*;

/**
 * https://leetcode.com/problems/concatenated-words/discuss/95652/Java-DP-Solution
 *
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

 Example:
 Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

 Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

 Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 Note:
 The number of elements of the given array will not exceed 10,000
 The length sum of elements in the given array will not exceed 600,000.
 All the input string will only include lower case letters.
 The returned elements order does not matter.

 * Created by xingfeiy on 7/5/18.
 */
public class ConcatenatedWords {
    /**
     * Assumption:
     * - same word may appear multiple times
     * - low case only
     * - order doesn't matter in result.
     * Analyze:
     * - The problem is find the words that can be broke into multiple shorter words in the list.
     *  - function canBreak(String str, Set<String> words)  -- DP
     * Solution:
     * - Go through words one by one and check if it can be broke.
     * Time complexity: O(n * avg_length)
     * Space complexity: O(n * avg_length)
     *
     * Optimization:
     * - Since the word can be concatenated by shorter words, so we don't have to check the words which longer than itself.
     * @param words
     * @return
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length < 1) return res;
        TreeSet<String> wordSet = new TreeSet<>((o1, o2) -> (o1.length() - o2.length()));
        for(String word : words) wordSet.add(word);

        for(String word : wordSet) {
            if(canBreak(word, wordSet.headSet(word, false))) res.add(word);
        }
        return res;
    }

    private boolean canBreak(String str, Set<String> words) {
        if(str == null || words == null || words.size() < 1) return false;
        boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true;
        for(int i = 1; i < dp.length; i++) {
            for(int j = i - 1; j >= 0; j--) {
                String subWord = str.substring(j, i);
                if(words.contains(subWord)) {
                    System.out.println("Contains " + subWord);
                }
                if(words.contains("hip")) {
                    System.out.println("hello");
                }
                if(words.contains("abc")) {
                    System.out.println("hello");
                }
                if(words.contains("def")) {
                    System.out.println("hello");
                }
                if(dp[j] && words.contains(str.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        ConcatenatedWords c = new ConcatenatedWords();
        String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        DisplayUtils.printList(c.findAllConcatenatedWordsInADict(words));
    }
}
