package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 7/16/18.
 */
public class KEditDistanceMy2 {
    /**
     * - Build dictionary trie
     * - Looking up the words with at most k distance.
     *  -
     * @param words
     * @param target
     * @param k
     * @return
     */
    public List<String> findAllWords(String[] words, String target,int k) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length < 1 || target == null || target.length() < 1 || k < 0) return res;
        //
        TrieNode root = buildTrie(words);
        int[] dp = new int[target.length() + 1];
        for(int i = 0; i < dp.length; i++) dp[i] = i;
        helper(root, target, "", dp, k, res);
        return res;
    }

    private void helper(TrieNode root, String target, String curStr, int[] dp, int k, List<String> res) {
        if(root == null) return;
        if(root.isWord && dp[dp.length - 1] <= k) res.add(curStr);

        for(int i = 0; i < root.children.length; i++) {
            if(root.children[i] == null) continue;
            //update int[] dp
            int[] curDP = new int[target.length() + 1];
            curDP[0] = curStr.length() + 1;
            for(int j = 1; j < dp.length; j++) {
                if(root.children[i].ch == target.charAt(j - 1)) {
                    curDP[j] = dp[j - 1];
                } else {
                    curDP[j] = Math.min(Math.min(curDP[j - 1], dp[j]), dp[j - 1]) + 1;
                }
            }

            helper(root.children[i], target, curStr + root.children[i].ch, curDP, k, res);
        }
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode(' ');
        for(String word : words) {
            addToTrie(root, word);
        }
        return root;
    }

    private void addToTrie(TrieNode root, String word) {
        if(root == null) return;
        TrieNode cur = root;
        for(char ch : word.toCharArray()) {
            if(cur.children[ch - 'a'] == null) cur.children[ch - 'a'] = new TrieNode(ch);
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    private class TrieNode {
        private char ch;
        private boolean isWord = false;
        private TrieNode[] children = new TrieNode[26];

        public TrieNode(char ch) {
            this.ch = ch;
        }
    }

    public static void main(String[] args) {
        KEditDistanceMy2 k = new KEditDistanceMy2();
        System.out.println("Find all words with k or less than k distance");
        String[] words = new String[] {"hello", "hallo", "hauuo", "hellowor","hell", "hllo", "apple"};
        //hallo hauuo hell hello hllo
        DisplayUtils.printList(k.findAllWords(words, "hello", 3));
        DisplayUtils.printList(k.findAllWords(words, "hello", 10));
        DisplayUtils.printList(k.findAllWords(words, "hello", 0));
    }
}
