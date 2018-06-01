package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/description/

 Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:

 Return an empty list if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 Example 1:

 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output:
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]
 Example 2:

 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: []

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 * Created by xingfeiy on 5/30/18.
 */
public class Problem126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if(wordList == null || wordList.size() < 1) return res;
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(new String[]{beginWord}));
        while (!queue.isEmpty()) {
            int size = queue.size();
            //traversal current level
            for(int i = 0; i < size; i++) {
                List<String> list = queue.poll();
                //check all possibilities
                for(int j = 0; j < list.get(list.size() - 1).length(); j++) {
                    char[] wordChs = list.get(list.size() - 1).toCharArray();
                    for(int n = 0; n <= 26; n++) {
                        wordChs[j] = (char)('a' + n);
                        String tmp = new String(wordChs);
                        if(list.contains(tmp)) continue;
                        if(wordList.contains(tmp)) {
                            List<String> newList = new ArrayList<>(list);
                            newList.add(tmp);
                            queue.add(newList);
                            if(tmp.equals(endWord)) {
                                res.add(newList);
                            }
                        }
                    }
                }
            }
            if(res.size() > 0) break;
        }
        return res;
    }


    /**
     *
     "hit"
     "cog"
     ["hot","dot","dog","lot","log","cog"]
     * @param args
     */
    public static void main(String[] args) {
        Problem126 p = new Problem126();
        List<List<String>> res = p.findLadders("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}));
        for(List<String> solution : res) {
            for(String word : solution) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }
}
