package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 7/7/18.
 */
public class KEditDistanceMy {

    class TrieNode {
        public char ch;
        public boolean isWord = false;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode(char ch) {
            this.ch = ch;
        }
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode(' ');
        if(words == null || words.length < 1) return root;

        for(String word : words) addToTrie(root, word);
        return root;
    }

    private TrieNode addToTrie(TrieNode root, String word) {
        if(word == null || word.length() < 1) return root;
        TrieNode curNode = root;
        for(char ch : word.toCharArray()) {
            if(curNode.children[ch - 'a'] == null) {
                curNode.children[ch - 'a'] = new TrieNode(ch);
            }
            curNode = curNode.children[ch - 'a'];
        }
        curNode.isWord = true;
        return root;
    }
    /**
     * Find all words which have k or less than k distance with target
     * @param words
     * @param target
     * @param k
     * @return
     */
    public List<String> findAllWords(String[] words, String target,int k) {
        List<String> res = new ArrayList<>();
        if(target == null || k < 0) return res;
        TrieNode root = buildTrie(words);
        int[] targetDP = new int[target.length() + 1];
        for(int i = 0; i < targetDP.length; i++) targetDP[i] = i;
        helper(root, "", target, targetDP, res, k);
        return res;
    }

    private void helper(TrieNode root, String curWord, String target, int[] targetDP, List<String> res, int k) {
        if(root.isWord && targetDP[targetDP.length - 1] <= k) {
            res.add(curWord);
        }

        for(TrieNode node : root.children) {
            if(node == null) continue;
            //calculate current dp
            int preVal = targetDP[0];
            targetDP[0] = curWord.length();
            for(int i = 1; i < targetDP.length; i++) {
                if(node.ch == target.charAt(i - 1)) {
                    targetDP[i] = preVal;
                } else {
                    targetDP[i] = Math.min(Math.min(targetDP[i], preVal), targetDP[i - 1]) + 1;
                }
            }
            helper(node, curWord + node.ch, target, targetDP, res, k);
        }
    }

    /**
     * Given two strings S and T, determine if they are both one edit distance apart.
     * @param s
     * @param t
     * @return
     */
    public boolean oneEditDistance(String s, String t) {
        if(s == null || t == null) return false;
        if(Math.abs(s.length() - t.length()) > 1) return false;
        int p1 = 0, p2 = 0, count = 0;
        while (p1 < s.length() && p2 < t.length()) {
            if(count > 1) return false;
            if(s.charAt(p1) == t.charAt(p2)) {
                p1++;
                p2++;
            } else if(s.length() == t.length()) {
                count++;
                p1++;
                p2++;
            } else if(s.length() > t.length()) {
                count++;
                p1++;
            } else {
                count++;
                p2++;
            }
        }
        return (s.length() == t.length() && count == 1)
                || (Math.abs(s.length() - t.length()) == 1 && count == 0);
    }

    public int editDistance(String str1, String str2) {
        if(str1 == null || str1.equals(str2)) return 0;
        if(str2 == null || str2.equals(str1)) return 0;

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for(int i = 1; i <= str2.length(); i++) dp[0][i] = i;
        for(int i = 1; i <= str1.length(); i++) dp[i][0] = i;

        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    public int editDistance2(String str1, String str2) {
        if(str1 == null || str1.equals(str2)) return 0;
        if(str2 == null || str2.equals(str1)) return 0;
        int[] dp = new int[str2.length() + 1];
        for(int i = 0; i < dp.length; i++) dp[i] = i;
        for(int i = 1; i <= str1.length(); i++) {
            int preVal = dp[0];
            dp[0] = i;
            for(int j = 1; j < dp.length; j++) {
                int tmp = dp[j];
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[j] = preVal;
                } else {
                    dp[j] = Math.min(Math.min(preVal, dp[j - 1]), dp[j]) + 1;
                }
                preVal = tmp;
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        KEditDistanceMy m = new KEditDistanceMy();
        System.out.println(m.oneEditDistance(null, null));
        System.out.println(m.oneEditDistance(null, "abc"));
        System.out.println(m.oneEditDistance("abc", "abc"));
        System.out.println(m.oneEditDistance("ab", "abc"));
        System.out.println(m.oneEditDistance("abc", "ab"));
        System.out.println(m.oneEditDistance("abc", "abd"));
        System.out.println(m.editDistance2("horse", "ros"));

        System.out.println("Find all words with k or less than k distance");
        String[] words = new String[] {"hello", "hallo", "hauuo", "hell", "hllo", "apple"};
        DisplayUtils.printList(m.findAllWords(words, "hello", 3));
    }
}
