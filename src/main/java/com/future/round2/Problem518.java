package com.future.round2;

/**
 * https://leetcode.com/problems/coin-change-2/description/

 You are given coins of different denominations and a total amount of money. Write a function to compute the number of
 combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

 Note: You can assume that

 0 <= amount <= 5000
 1 <= coin <= 5000
 the number of coins is less than 500
 the answer is guaranteed to fit into signed 32-bit integer
 Example 1:

 Input: amount = 5, coins = [1, 2, 5]
 Output: 4
 Explanation: there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1
 Example 2:

 Input: amount = 3, coins = [2]
 Output: 0
 Explanation: the amount of 3 cannot be made up just with coins of 2.
 Example 3:

 Input: amount = 10, coins = [10]
 Output: 1

 * Created by xingfeiy on 4/30/18.
 */
public class Problem518 {
    /**
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // pay attention, the loop order.
        // if loop of amount go to first loop, there are duplicated results.
        // put the loop of coins first, it's not easy to understand, just think about:
        // if we have only one coin, how many ways here.
        // then, we got one more coin, add new ways with previous one.
        // ...
        for(int j = 0; j < coins.length; j++) {
            for(int i = 1; i <= amount; i++) {
                if(i - coins[j] >= 0) dp[i] += dp[i - coins[j]];
            }
        }
        return dp[amount];
    }
}
