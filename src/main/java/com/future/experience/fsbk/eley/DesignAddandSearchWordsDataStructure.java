package com.future.experience.fsbk.eley;

public class DesignAddandSearchWordsDataStructure {
    private TrieNode root = new TrieNode();

    /** Initialize your data structure here. */
    public DesignAddandSearchWordsDataStructure() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word == null || word.length() < 1) {
            return;
        }

        TrieNode node = root;
        for(char ch : word.toCharArray()) {
            if(node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode(ch);
            }
            node = node.children[ch - 'a'];
        }
        node.word = word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, TrieNode node) {
        if(word == null || word.length() < 1) {
            return node.word != null;
        }
        TrieNode p = node;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(ch == '.') {
                for(TrieNode child : p.children) {
                    if(child != null && search(word.substring(i + 1), child)) {
                        return true;
                    }
                }
                return false;
            } else if(p.children[ch - 'a'] == null) {
                return false;
            } else {
                p = p.children[ch - 'a'];
            }
        }

        return p != null && p.word != null;
    }

    private class TrieNode {
        public char ch;

        public TrieNode[] children = new TrieNode[26];

        public String word = null;

        public TrieNode() {}

        public TrieNode(char ch) {
            this.ch = ch;
        }
    }

    public static void main(String[] args) {
        DesignAddandSearchWordsDataStructure p = new DesignAddandSearchWordsDataStructure();
        p.addWord("a");
        System.out.println(p.search(".a"));
    }
}
