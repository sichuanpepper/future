package com.future.experience.linying;

import java.util.List;

/**
 * https://leetcode.com/problems/replace-words/
 *
 */
public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        DictTrie root = new DictTrie();
        for(String word : dictionary) {
            buildTrie(word, root);
        }

        StringBuilder sb = new StringBuilder();
        for(String word : sentence.split(" ")) {
            String rootWord = searchRoot(word, root);
            sb.append(rootWord == null ? word : rootWord).append(" ");
        }
        return sb.toString().trim();
    }

    private String searchRoot(String word, DictTrie root) {
        DictTrie pointer = root;
        for(char ch : word.toCharArray()) {
            if(pointer.children[ch - 'a'] == null) {
                return null;
            }
            if(pointer.children[ch - 'a'].word != null) {
                return pointer.children[ch - 'a'].word;
            }
            pointer = pointer.children[ch - 'a'];
        }
        return null;
    }

    private DictTrie buildTrie(String word, DictTrie root) {
        DictTrie pointer = root;
        for(char ch : word.toCharArray()) {
            if(pointer.children[ch - 'a'] == null) {
                pointer.children[ch - 'a'] = new DictTrie();
            }
            pointer = pointer.children[ch - 'a'];
        }
        pointer.word = word;
        return root;
    }

    private class DictTrie {
        public DictTrie[] children = new DictTrie[26];
        public String word = null;
    }
}
