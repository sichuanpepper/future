package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/description/
 *
 * Created by xingfeiy on 11/20/17.
 */
public class Problem139 {
    /**
     * Analyze:
     * The first algorithm in my mind is back tracking, and second algorithm is DP.
     * Since we are going to search wordDict many times, so we put it into a set.
     * The back tracking approach got ETL.
     *
     * Let's try to solve it by using DP.
     * Use f(n) represent if the string from 0 to n can be break or not.
     * So f(n) = f(n - word.length) where word.length is the length of any word in dictionary.
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() < 1 || wordDict == null || wordDict.size() < 1) return false;
        Set<String> wordDictSet = new HashSet<>(wordDict);
        return helper(s, 0, wordDictSet);
    }

    private boolean helper(String s, int start, Set<String> wordDict) {
        if(start >= s.length()) return true;
        for(int i = start; i < s.length(); i++) {
            if(wordDict.contains(s.substring(start, i + 1))) {
                if(helper(s, i + 1, wordDict)) return true;
            }
        }
        return false;
    }

    public boolean wordBreakDB(String s, List<String> wordDict) {
        if(s == null || s.length() < 1 || wordDict == null || wordDict.size() < 1) return false;
        boolean[] canBeBreak = new boolean[s.length() + 1];
        canBeBreak[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(canBeBreak[j] && wordDict.contains(s.substring(j, i))) {
                    canBeBreak[i] = true;
                    break;
                }
            }
        }
        return canBeBreak[s.length()];
    }


    public static void main(String[] args) {
        Problem139 p = new Problem139();
        List<String> wordDict = Arrays.asList(new String[]{"leet", "code"});
        System.out.println(p.wordBreak("leetcode", wordDict));
        System.out.println(p.wordBreak("leetcoder", wordDict));
    }
}
