package com.future.round2;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower-ii/description/
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

 However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

 Example:

 n = 10, I pick 8.

 First round:  You guess 5, I tell you that it's higher. You pay $5.
 Second round: You guess 7, I tell you that it's higher. You pay $7.
 Third round:  You guess 9, I tell you that it's lower. You pay $9.

 Game over. 8 is the number I picked.

 You end up paying $5 + $7 + $9 = $21.
 Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

 * Created by xingfeiy on 1/24/18.
 */
public class Problem375 {
    /**
     * Analyze:
     * "find out how much money you need to have to guarantee a win"
     * It means, the minimum money you need for any picked number between 1 to n, just need to guarantee a win.
     * So basically it's an optimal problem, think about DP.
     * First thing is how to define the state.
     * For any picked number p, the cost is $p + max(left_sub_problem, right_sub_problem), seems the state is about a range.
     * Let's use f(m, n) represents the money we need for range m to n.
     * Now, what's the state transition?
     * Since any number in range m to n could be picked, we need to find the minimum cost one.
     * And for nay number p, it has f(m, n) = $p + max(f(m, p - 1), f(p + 1), n)
     * Now, we can come out the Top-down DP solution
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return topDownDp(dp, 0, n);
    }

    private int topDownDp(int[][] dp, int start, int end) {
        if(start >= end) return 0;
        if(dp[start][end] > 0) return dp[start][end]; //has been calculated.
        int minCostInRange = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++) {
            int curCost = i + Math.max(topDownDp(dp, start, i - 1), topDownDp(dp, i + 1, end));
            minCostInRange = Math.min(minCostInRange, curCost);
        }
        dp[start][end] = minCostInRange;
        return minCostInRange;
    }

}
