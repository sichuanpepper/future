package com.future.experience.fsbk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/submissions/
 *
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int maxLength = 0;
        for(String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            set.add(word);
        }

        return helper(s, 0, set, maxLength);
    }

    private boolean helper(String s, int start, Set<String> dict, int maxLength) {
        if(start >= s.length()) return true;

        for(int i = start; i <= Math.min(s.length() - 1, start + maxLength); i++) {
            if(dict.contains(s.substring(start, i + 1))) {
                if(helper(s, i + 1, dict, maxLength)) return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        WordBreak p = new WordBreak();
        System.out.println(p.wordBreak("aaaaaaa", Arrays.asList(new String[]{"aaaa","aaa"})));
    }
}
