package com.future.foundation.game;

/**
 * Created by xingfeiy on 6/11/18.
 */
public class GameProblems {
    /**
     * You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
     * + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no
     * longer make a move and therefore the other person will be the winner.
     * <p>
     * Write a function to determine if the starting player can guarantee a win.
     * <p>
     * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
     * <p>
     * Follow up:
     * Derive your algorithm's runtime complexity.
     * <p>
     * Analyze:
     * Let's say we are given string "++++", and I'm the starting player, I can flip any two consecutive "++", and the problem
     * became to a smaller sub-problem, can my friend win the game with new string? if my friend can win, I will lose, otherwise, I will win.
     * So we can come out the straightforward way, recursion.
     *
     * @param s
     * @return
     */
    public boolean canWinFlipGame2(String s) {
        if (!s.contains("++")) return false;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+' && !canWinFlipGame2(s.substring(0, i - 1) + "--" + s.substring(i + 1)))
                return true;
        }
        return false;
    }

    /**
     * In the "100 game," two players take turns adding, to a running total, any integer from 1..10.
     * The player who first causes the running total to reach or exceed 100 wins.
     * <p>
     * What if we change the game so that players cannot re-use integers?
     * <p>
     * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement
     * until they reach a total >= 100.
     * <p>
     * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can
     * force a win, assuming both players play optimally.
     * <p>
     * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
     * <p>
     * Example
     * <p>
     * Input:
     * maxChoosableInteger = 10
     * desiredTotal = 11
     * <p>
     * Output:
     * false
     * <p>
     * Explanation:
     * No matter which integer the first player choose, the first player will lose.
     * The first player can choose an integer from 1 up to 10.
     * If the first player choose 1, the second player can only choose integers from 2 up to 10.
     * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
     * Same with other integers chosen by the first player, the second player will always win.
     * <p>
     * Analyze:
     * If I'm the starting player, I can choose any number between 1 to maxChoossableInteger (inclusive), and the problem
     * became to a smaller one, check if can competitor win the game, if competitor can win, I will lose, otherwise, I will win.
     *
     * @param maxChoossableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canWin100Game(int maxChoossableInteger, int desiredTotal) {
        if(desiredTotal < 1) return true;
        int sum = 0;
        for(int i = 1; i <= maxChoossableInteger; i++) sum += i;
        if(sum < desiredTotal) return false;
        int[] options = new int[maxChoossableInteger];
        for (int i = 1; i <= maxChoossableInteger; i++) options[i - 1] = i;
        return helper(options, desiredTotal);
    }

    private boolean helper(int[] options, int desiredTotal) {
        for (int i = 0; i < options.length; i++) {
            if (options[i] == 0) continue;
            int tmp = options[i];
            options[i] = 0;
            //if current player can reach or exceed desiredTotal, or the competitor can't win the game with remain numbers.
            if (i + 1 >= desiredTotal || !helper(options, desiredTotal - tmp)) {
                options[i] = tmp;
                return true;
            }
            options[i] = tmp;
        }
        return false;
    }

    private byte[] m_;

    public boolean canIWin(int M, int T) {
        int sum = M * (M + 1) / 2;
        if (sum < T) return false;
        if (T <= 0) return true;
        m_ = new byte[1 << M];
        return canIWin(M, T, 0);
    }

    private boolean canIWin(int M, int T, int state) {
        if (T <= 0) return false;
        if (m_[state] != 0) return m_[state] == 1;

        for (int i = 0; i < M; ++i) {
            if ((state & (1 << i)) > 0) continue;
            if (!canIWin(M, T - (i + 1), state | (1 << i))) {
                m_[state] = 1;
                return true;
            }
        }
        m_[state] = -1;
        return false;
    }

    public static void main(String[] args) {
        GameProblems p = new GameProblems();
        System.out.println(p.canWinFlipGame2("+-+-")); //false
        System.out.println(p.canWinFlipGame2("++++")); //true
        System.out.println(p.canWinFlipGame2("++++++")); //true
        System.out.println(p.canWinFlipGame2("++++++++")); //true

        System.out.println("100 Games");
        System.out.println(p.canWin100Game(3, 4)); //false
        System.out.println(p.canWin100Game(3, 5)); //true
        System.out.println(p.canWin100Game(3, 3)); //true
        System.out.println("100 Games");
        System.out.println(p.canIWin(3, 4)); //false
        System.out.println(p.canIWin(3, 5)); //true
        System.out.println(p.canIWin(3, 3)); //true
    }
}
