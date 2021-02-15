package com.future.experience.didi;


import com.future.round2.Problem211;
import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a data structure that supports the following operations:
 *
 * void addWord(String word);
 *
 * List<String> prefixSearch(String prefix);
 *
 * List<String> fuzzySearch(String pattern);
 *
 * fuzzySearch(word) can search a literal word or a regular expression string containing only letters a-z or ..
 * A . means it can represent any one letter.
 *
 * examples:
 * addWord("hello");
 * addWord("world");
 * addWord("help");
 * addWord("held");
 * addWord("hard");
 *
 * prefixSearch("he") -> ["hello", "help", "held"]
 * fuzzySearch("hel.") -> ["help", "held"]
 * fuzzySearch("h..d") -> ["held", "hard"]
 *
 */
public class WordDictionary {

    class CharTreeNode {
        public CharTreeNode[] children = new CharTreeNode[26];
        public String word = null;
    }

    private CharTreeNode root = new CharTreeNode();

    public void addWord(String word) {
        if(word == null || word.length() < 1) return;
        CharTreeNode node = root;
        for(char ch : word.toCharArray()) {
            if(node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new CharTreeNode();
            }
            node = node.children[ch - 'a'];
        }
        node.word = word;
    }

    public List<String> prefixSearch(String prefix) {
        List<String> res = new ArrayList<>();
        if(prefix == null || prefix.length() < 1) return res;

        CharTreeNode node = root;
        for(char ch : prefix.toCharArray()) {
            if(node.children[ch - 'a'] == null) return res;
            node = node.children[ch - 'a'];
        }
        prefixHelper(node, res);
        return res;
    }

    private void prefixHelper(CharTreeNode node, List<String> res) {
        if(node == null) return;
        if(node.word != null) res.add(node.word);
        for(CharTreeNode nextNode : node.children) {
            prefixHelper(nextNode, res);
        }
    }

    public List<String> fuzzySearch(String pattern) {
        List<String> res = new ArrayList<>();
        if(pattern == null || pattern.length() < 1) return res;
        fuzzyHelper(root, pattern, 0, res);
        return res;
    }

    private void fuzzyHelper(CharTreeNode root , String pattern, int curPos, List<String> res) {
        if(root == null) return;
        if(curPos == pattern.length()) {
            if(root.word != null) res.add(root.word);
            return;
        }
        char ch = pattern.charAt(curPos);
        if(ch != '.' && root.children[ch - 'a'] != null) fuzzyHelper(root.children[ch - 'a'], pattern, curPos + 1, res);
        if(ch == '.') {
            for(CharTreeNode child : root.children) {
                fuzzyHelper(child, pattern, curPos + 1, res);
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("hello");
        dict.addWord("world");
        dict.addWord("help");
        dict.addWord("held");
        dict.addWord("hard");

        DisplayUtils.printList(dict.prefixSearch("he"));
        DisplayUtils.printList(dict.fuzzySearch("hel."));
        DisplayUtils.printList(dict.fuzzySearch("h..d"));
    }
}
