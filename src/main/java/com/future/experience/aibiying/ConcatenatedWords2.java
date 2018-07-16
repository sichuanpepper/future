package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.*;

/**
 *
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

 * Created by xingfeiy on 7/15/18.
 */
public class ConcatenatedWords2 {
    /**
     *
     * @param words
     * @return
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length < 2) return res;

        Set<String> wordSet = new HashSet<>();
        Arrays.sort(words, (o1, o2)->(o1.length() - o2.length()));
        for(String word : words) {
            if(canBreak(word, wordSet)) res.add(word);
            wordSet.add(word);
        }
        return res;
    }

    private boolean canBreak(String word, Set<String> words) {
        //at least two shorter words...
        if(words == null || words.size() < 1 || word == null || word.length() < 2) return false;
        // f(n) = f(n - k) where word.substring(k+1, n+1) is a word
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= word.length(); i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(words.contains(word.substring(j, i)) && dp[j]) dp[i] = true;
            }
        }
        return dp[word.length()];
    }

    public static void main(String[] args) {
        ConcatenatedWords2 c = new ConcatenatedWords2();
        String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        DisplayUtils.printList(c.findAllConcatenatedWordsInADict(words));
    }
}
