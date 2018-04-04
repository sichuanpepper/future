package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-break-ii/description/
 *
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct
 a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

 Return all such possible sentences.

 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].

 * Created by xingfeiy on 1/22/18.
 */
public class Problem140 {
    /**
     * Analyze:
     * The straightforward way is back tracking, but got TLE
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreakTLE(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        helper(s, wordDict, 0, "", res);
        return res;
    }

    private void helper(String s, List<String> wordDict, int start ,String curRes, List<String> res) {
        if(start > s.length()) return;
        if(start == s.length()) {
            res.add(curRes.trim());
            return;
        }
        for(int i = start + 1; i <= s.length(); i++) {
            String tmp = s.substring(start, i);
            if(wordDict.contains(tmp)) {
                helper(s, wordDict, i, curRes + " " + tmp, res);
            }
        }
    }
}
