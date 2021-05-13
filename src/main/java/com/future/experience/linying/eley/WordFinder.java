package com.future.experience.linying.eley;

import java.util.*;

public class WordFinder {
    private class TrieNode {
        public char ch = ' ';

        public TrieNode[] children = new TrieNode[26];

        public String word = null; //the word ended here

        public TrieNode(char ch) {
            this.ch = ch;
        }
    }

    private TrieNode root = new TrieNode(' ');
    /*
     * Initializes the wordFinder with the set of words that it will
     * search over. Words may be large (hundreds of thousands of entries),
     * but will fit in memory.
     * Does any pre-processing needed. Expected to be called only
     * once per wordFinder instance.
     */
    public void init (Set<String> words) {
        for(String word : words) {
            buildTrie(word);
        }
    }

    private void buildTrie(String word) {
        TrieNode p = root;
        for(char ch : word.toCharArray()) {
            if(p.children[ch - 'a'] == null) {
                p.children[ch - 'a'] = new TrieNode(ch);
            }
            p = p.children[ch - 'a'];
        }
        p.word = word;
    }

    /*
     * Given an array of characters, returns a Set of all of the
     * words in the word list that can be made with any subset of
     * those characters. Duplicates may be present in the array,
     * but each array index may only be used once per word.
     * Thus, given {'o', 'r', 's', 'd', 'o', 'w', 'e'} you might
     * return (among others) "word", "words", and "wood",
     * but not "order".
     * The list is expected to be short (less than 100 characters)
     */
    public Set<String> find (List<Character> characters) {
        int[] map = new int[26];
        for(char ch : characters) {
            map[ch - 'a']++;
        }

        for(int i = 0; i < map.length; i++) {
            if(map[i] > 0) {

            }
        }

        Set<String> res = new HashSet<>();
        helper(map, root, res);
        return res;
    }

    private void helper(int[] map, TrieNode node, Set<String> res) {
        if(node == null) {
            return;
        }

        if(node.word != null) {
            res.add(node.word);
        }

        for(int i = 0; i < map.length; i++) {
            if(map[i] > 0) {
                if(node.children[i] != null) {
                    map[i]--;
                    helper(map, node.children[i], res);
                    map[i]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        WordFinder p = new WordFinder();
        Set<String> dict = new HashSet<>(Arrays.asList("word", "words", "wood", "apple",
                "google", "pink", "book", "today", "money", "good", "food", "foo", "ood"));
        p.init(dict);
        List<Character> chars = new ArrayList<>();
//        'o', 'r', 's', 'd', 'o', 'w', 'e'
        chars.add('o');
        chars.add('r');
        chars.add('s');
        chars.add('d');
        chars.add('o');
        chars.add('w');
        chars.add('e');
        chars.add('f');

        p.find(chars).forEach(System.out::println);
    }

}
