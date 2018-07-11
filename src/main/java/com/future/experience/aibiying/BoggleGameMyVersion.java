package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The simple way is just do DFS, and check each current string, if current string is a word, add it to results.
 * Time complexity O((m*n)^2)
 *
 *
 * Created by xingfeiy on 7/3/18.
 */
public class BoggleGameMyVersion {
    public List<String> findAllWords(char[][] boggle, Set<String> dict) {
        List<String> allWords = new ArrayList<>();
        boolean[][] visited = new boolean[boggle.length][boggle[0].length];
        for(int i = 0; i < boggle.length; i++) {
            for(int j = 0; j < boggle[0].length; j++) {
                helper(boggle, i, j, "", allWords, dict, visited);
            }
        }
        return allWords;
    }

    private static int count = 0;
    private void helper(char[][] boggle, int row, int col, String cur, List<String> res, Set<String> dict, boolean[][] visited) {
        count++;
        if(row < 0 || row >= boggle.length || col < 0 || col >= boggle[0].length) return;
        if(visited[row][col]) return;
        cur += boggle[row][col];
        if(dict.contains(cur)) res.add(cur);
        visited[row][col] = true;
        helper(boggle, row - 1, col, cur, res, dict, visited);
        helper(boggle, row + 1, col, cur, res, dict, visited);
        helper(boggle, row, col - 1, cur, res, dict, visited);
        helper(boggle, row, col + 1, cur, res, dict, visited);
        helper(boggle, row - 1, col - 1, cur, res, dict, visited);
        helper(boggle, row - 1, col + 1, cur, res, dict, visited);
        helper(boggle, row + 1, col - 1, cur, res, dict, visited);
        helper(boggle, row + 1, col + 1, cur, res, dict, visited);
        visited[row][col] = false;
    }

    public List<String> findAllWords2(char[][] boggle, Set<String> dict) {
        List<String> res = new ArrayList<>();
        boolean[][] visited = new boolean[boggle.length][boggle[0].length];
        TrieNode root = buildTree(dict);
        for(int i = 0; i < boggle.length; i++) {
            for(int j = 0; j < boggle[0].length; j++) {
                helper2(boggle, i, j, "", res, root, visited);
            }
        }
        return res;
    }

    private static int count2 = 0;
    private void helper2(char[][] boggle, int row, int col, String cur, List<String> res, TrieNode root , boolean[][] visited) {
        count2++;
        if(row < 0 || row >= boggle.length || col < 0 || col >= boggle[0].length) return;
        if(visited[row][col]) return;
        cur += boggle[row][col];
        int searchRes = search(root, cur);
        if(searchRes < 0) return;
        if(searchRes == 1) res.add(cur);
        visited[row][col] = true;
        helper2(boggle, row - 1, col, cur, res, root, visited);
        helper2(boggle, row + 1, col, cur, res, root, visited);
        helper2(boggle, row, col - 1, cur, res, root, visited);
        helper2(boggle, row, col + 1, cur, res, root, visited);
        helper2(boggle, row - 1, col - 1, cur, res, root, visited);
        helper2(boggle, row - 1, col + 1, cur, res, root, visited);
        helper2(boggle, row + 1, col - 1, cur, res, root, visited);
        helper2(boggle, row + 1, col + 1, cur, res, root, visited);
        visited[row][col] = false;
    }


    private TrieNode buildTree(Set<String> dict) {
        TrieNode root = new TrieNode(' ');
        for(String word : dict) {
            TrieNode cur = root;
            for(char ch : word.toCharArray()) {
                if(cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new TrieNode(ch);
                }
                cur = cur.children[ch - 'a'];
            }
            cur.isWord = true;
        }
        return root;
    }

    /**
     * return -1, if un-matches
     * return 0, matches
     * return 1, is word matches
     * @param root
     * @param str
     * @return
     */
    private int search(TrieNode root, String str) {
        TrieNode cur = root;
        for(char ch : str.toCharArray()) {
            if(cur.children[ch - 'a'] == null) return -1;
            cur = cur.children[ch - 'a'];
        }
        return cur.isWord ? 1 : 0;
    }

    class TrieNode {
        public TrieNode(char ch) {
            this.ch = ch;
        }
        public char ch;
        public TrieNode[] children = new TrieNode[26];
        public boolean isWord = false;
    }

    public static void main(String[] args) {
        BoggleGameMyVersion p = new BoggleGameMyVersion();
        char[][] boggle = new char[3][3];
        boggle[0] = new char[]{'g', 'i', 'z'};
        boggle[1] = new char[]{'u', 'e', 'k'};
        boggle[2] = new char[]{'q', 's', 'e'};

        Set<String> dict = new HashSet<>();
        dict.add("geeks");
        dict.add("quiz");
        dict.add("ie");
        dict.add("ieksug");
        dict.add("ieksugdsfds");

        DisplayUtils.printList(p.findAllWords(boggle, dict));
        System.out.println(count);
        DisplayUtils.printList(p.findAllWords2(boggle, dict));
        System.out.println(count2);
    }
}
