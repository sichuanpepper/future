package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
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

        DisplayUtils.printList(p.findAllWords(boggle, dict));
        System.out.println(count);
    }
}
