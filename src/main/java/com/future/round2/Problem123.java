package com.future.round2;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [3,3,5,0,0,3,1,4]
 Output: 6
 Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 Example 2:

 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.
 Example 3:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.

 * Created by xingfeiy on 5/21/18.
 */
public class Problem123 {
    /**
     * https://aaronice.gitbooks.io/lintcode/content/high_frequency/best_time_to_buy_and_sell_stock_iii.html
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        int[] buyer = new int[prices.length];
        int[] seller = new int[prices.length];
        int lowest = prices[0], highest = prices[prices.length - 1];
        for(int i = 1; i < prices.length; i++) {
            buyer[i] = Math.max(buyer[i - 1], prices[i] - lowest);
            lowest = Math.min(lowest, prices[i]);
        }

        for(int i = prices.length - 2; i >= 0; i--) {
            seller[i] = Math.max(seller[i + 1], highest - prices[i]);
            highest = Math.max(highest, prices[i]);
        }

        int max = 0;
        for(int i = 0; i < prices.length; i++) {
            max = Math.max(max, buyer[i] + seller[i]);
        }
        return max;
    }
}
