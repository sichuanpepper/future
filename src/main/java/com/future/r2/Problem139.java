package com.future.r2;

import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * You may assume the dictionary does not contain duplicate words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].

 Return true because "leetcode" can be segmented as "leet code".
 * Created by someone on 10/10/17.
 */
public class Problem139 {
    /**
     * f(n) = f(i) & dict contains substring(i + 1, n) where 0 <= i < n
     * the first character is trick, it can be included or excluded.
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] satisfy = new boolean[s.length() + 1];
        satisfy[0] = true;
        satisfy[1] = wordDict.contains(Character.toString(s.charAt(0)));

        for(int i = 1; i < s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(satisfy[j] && wordDict.contains(s.substring(j + 1, i + 1))) {
                    satisfy[i + 1] = true;
                    break;
                }
            }
        }
        return satisfy[s.length()];
    }
}

