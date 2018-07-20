package com.future.experience.aibiying;

import java.util.*;

/**
 * https://leetcode.com/problems/sliding-puzzle/description/

 On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

 A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

 The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

 Given a puzzle board, return the least number of moves required so that the state of the board is solved.
 If it is impossible for the state of the board to be solved, return -1.

 Examples:

 Input: board = [[1,2,3],[4,0,5]]
 Output: 1
 Explanation: Swap the 0 and the 5 in one move.
 Input: board = [[1,2,3],[5,4,0]]
 Output: -1
 Explanation: No number of moves will make the board solved.
 Input: board = [[4,1,2],[5,0,3]]
 Output: 5
 Explanation: 5 is the smallest number of moves that solves the board.
 An example path:
 After move 0: [[4,1,2],[5,0,3]]
 After move 1: [[4,1,2],[0,5,3]]
 After move 2: [[0,1,2],[4,5,3]]
 After move 3: [[1,0,2],[4,5,3]]
 After move 4: [[1,2,0],[4,5,3]]
 After move 5: [[1,2,3],[4,5,0]]
 Input: board = [[3,2,4],[1,5,0]]
 Output: 14
 Note:

 board will be a 2 x 3 array as described above.
 board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 * Created by xingfeiy on 7/11/18.
 */
public class SlidingPuzzle {
    public int game(int[][] board) {
        if(board == null || board.length < 1) return -1;
        String dest = "";
        for(int i = 1; i < board.length * board[0].length; i++) dest += i;
        dest += "0";
        Queue<String> queue = new LinkedList<>();
        queue.offer(toString(board));
        Set<String> visited = new HashSet<>();
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String cur = queue.poll();
                if(cur.equals(dest)) return step;
                visited.add(cur);
                //move to 4 dir
                int posZero = cur.indexOf('0');
                //move left
                if(posZero % board[0].length > 0) {
                    String next = swapChars(cur, posZero, posZero - 1);
                    if(!visited.contains(next)) queue.offer(next);
                }
                //move right
                if(posZero % board[0].length < board[0].length - 1) {
                    String next = swapChars(cur, posZero, posZero + 1);
                    if(!visited.contains(next)) queue.offer(next);
                }
                //move up
                if((posZero - board[0].length) >= 0) {
                    String next = swapChars(cur, posZero, posZero - board[0].length);
                    if(!visited.contains(next)) queue.offer(next);
                }
                //move down
                if(posZero + board[0].length < board.length * board[0].length) {
                    String next = swapChars(cur, posZero, posZero + board[0].length);
                    if(!visited.contains(next)) queue.offer(next);
                }
            }
            step++;
        }
        return -1;

    }

    private String swapChars(String str, int p1, int p2) {
        char[] chars = str.toCharArray();
        char tmp = chars[p1];
        chars[p1] = chars[p2];
        chars[p2] = tmp;
        return new String(chars);
    }

    private String toString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    public int slidingPuzzle2(int[][] board) {
        Set<String> seen = new HashSet<>(); // used to avoid duplicates
        String target = "123450";
        // convert board to string - initial state.
        String s = Arrays.deepToString(board).replaceAll("\\[|\\]|,|\\s", "");
        Queue<String> q = new LinkedList<>(Arrays.asList(s));
        seen.add(s); // add initial state to set.
        int ans = 0; // record the # of rounds of Breadth Search
        while (!q.isEmpty()) { // Not traverse all states yet?
            // loop used to control search breadth.
            for (int sz = q.size(); sz > 0; --sz) {
                String str = q.poll();
                if (str.equals(target)) { return ans; } // found target.
                int i = str.indexOf('0'); // locate '0'
                int[] d = { 1, -1, 3, -3 }; // potential swap displacements.
                for (int k = 0; k < 4; ++k) { // traverse all options.
                    int j = i + d[k]; // potential swap index.
                    // conditional used to avoid invalid swaps.
                    if (j < 0 || j > 5 || i == 2 && j == 3 || i == 3 && j == 2) { continue; }
                    char[] ch = str.toCharArray();
                    // swap ch[i] and ch[j].
                    char tmp = ch[i];
                    ch[i] = ch[j];
                    ch[j] = tmp;
                    s = String.valueOf(ch); // a new candidate state.
                    if (seen.add(s)) { q.offer(s); } //Avoid duplicate.
                }
            }
            ++ans; // finished a round of Breadth Search, plus 1.
        }
        return -1;
    }

    public static void main(String[] args) {
        SlidingPuzzle s = new SlidingPuzzle();
        //[[1,2,3],[4,0,5]]
        System.out.println(s.game(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        //[[3,2,4],[1,5,0]]
        System.out.println(s.game(new int[][]{{3, 2, 4}, {1, 5, 0}}));
//        System.out.println(s.slidingPuzzle2(new int[][]{{3, 2, 4}, {1, 5, 0}}));
        System.out.println(s.game(new int[][]{{3, 2, 4}, {1, 5, 0}, {6, 7, 8}}));
        System.out.println(s.game(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}}));
    }
}
