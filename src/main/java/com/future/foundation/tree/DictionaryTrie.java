package com.future.foundation.tree;

/**
 * Created by xingfeiy on 4/5/18.
 */
public class DictionaryTrie {
    private class TrieNode {
        public TrieNode(char val) {
            this.val = val;
        }

        public char val;

        public boolean isEnd = false;

        public TrieNode[] children = new TrieNode[26];
    }

    public TrieNode root = new TrieNode(' ');

    public void addWord(String word) {
        TrieNode curNode = root;
        for(char ch : word.toCharArray()) {
            if(curNode.children[ch - 'a'] == null) {
                curNode.children[ch - 'a'] = new TrieNode(ch);
            }
            curNode = curNode.children[ch - 'a'];
        }
        curNode.isEnd = true;
    }

    public boolean exists(String word) {
        TrieNode curNode = root;
        for(char ch : word.toCharArray()) {
            if(curNode.children[ch - 'a'] == null) return false;
            curNode = curNode.children[ch - 'a'];
        }
        return curNode.isEnd;
    }

    public String findLongestPrefix(String word) {
        TrieNode curNode = root;
        String res = "";
        for(char ch : word.toCharArray()) {
            if(curNode.children[ch - 'a'] == null) break;
            res = res + ch;
            curNode = curNode.children[ch - 'a'];
        }
        return res;
    }


    public static void main(String[] args) {
        DictionaryTrie trie = new DictionaryTrie();
        trie.addWord("hello");
        trie.addWord("world");
        trie.addWord("welcome");
        System.out.println(trie.exists("world"));
        System.out.println(trie.exists("wor"));
        System.out.println(trie.exists("worlds"));
        System.out.println(trie.findLongestPrefix("worldsoooooo"));
        System.out.println(trie.findLongestPrefix("hel"));
    }
}
