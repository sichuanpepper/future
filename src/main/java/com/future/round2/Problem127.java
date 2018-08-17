package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/description/
 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:

 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 Example 1:

 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output: 5

 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 Example 2:

 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: 0

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 * Created by xingfeiy on 5/6/18.
 */
public class Problem127 {
    /**
     * BFS, beats 20%
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int res = 1;

        Set<String> wordSet = new HashSet<>();
        for(String word : wordList) wordSet.add(word);
        if(!wordSet.contains(endWord)) return 0;

        Set<String> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int s = 0; s < size; s++) {
                String word = queue.poll();
                for(int i = 0; i < word.length(); i++) {
                    int index = 0;
                    while(index < 26) {
                        char ch = (char) ('a' + index++);
                        if(word.charAt(i) == ch) continue;
                        String testWord = word.substring(0, i) + ch + word.substring(i + 1, word.length());
                        if(testWord.equals(endWord)) return res + 1;
                        if(wordSet.contains(testWord)) {
                            if(visited.contains(testWord)) continue;
                            visited.add(testWord);
                            queue.offer(testWord);
                        }
                    }
                }
            }
            res++;
        }
        return 0;
    }

    /**
     * Analyze:
     * Given a start word, to find a destination word, and each word will connect with zero or multiple words.
     * It's a graph problem, since we need to find the shortest path, BFS.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthRound2(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() < 1) return 0;
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordSet.remove(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String curWord = queue.poll();
                if(curWord.equals(endWord)) return step;
                char[] chars = curWord.toCharArray();
                for(int m = 0; m < chars.length; m++) {
                    char origCh = chars[m];
                    for(int j = 0; j < 26; j++) {
                        if(chars[m] - 'a' == j) continue;
                        chars[m] = (char)('a' + j);
                        String tmp = new String(chars);
                        if(wordSet.contains(tmp)) {
                            queue.offer(tmp);
                            wordSet.remove(tmp);
                        }
                    }
                    chars[m]= origCh;
                }

            }
            step++;
        }
        return 0;
    }

    public static void main(String[] args) {
        Problem127 p = new Problem127();
        System.out.println(p.ladderLengthRound2("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
    }
}
