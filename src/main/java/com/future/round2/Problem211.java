package com.future.round2;

/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/description/
 *
 * Created by xingfeiy on 11/20/17.
 */
public class Problem211 {
    class CharTreeNode {
        public CharTreeNode[] children = new CharTreeNode[26];
        public String word = "";
    }

    private CharTreeNode root = new CharTreeNode();

    /** Initialize your data structure here. */
    public Problem211() {
    }

    /** Adds a word into the data structure. */
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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word == null || word.length() < 1) return false;
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int start, CharTreeNode node) {
        if(node == null) return false;
        if(start >= word.length() && node != null) return (!node.word.isEmpty() && word.length() == node.word.length());

        boolean found = false;
        for(int i = 0; i < node.children.length; i++) {
            if(word.charAt(start) == '.') {
                found = searchHelper(word, start + 1, node.children[i]);
            } else if(word.charAt(start) - 'a'  == i) {
                found = searchHelper(word, start + 1, node.children[i]);
            }
            if(found) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Problem211 p = new Problem211();
        p.addWord("bab");
        System.out.println(p.search("bab"));
        System.out.println(p.search("b.."));
        System.out.println(p.search("..b"));
        System.out.println(p.search("..."));
        p.addWord("mad");
        System.out.println(p.search(".a."));
        System.out.println(p.search(".d."));
    }
}
