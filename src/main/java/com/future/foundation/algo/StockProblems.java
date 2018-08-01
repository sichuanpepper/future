package com.future.foundation.algo;

/**
 * - The maximum profits depends on three factors here,
 *  - which day we are
 *  - the total transaction we can make
 *  - the stocks we have in our hand
 *
 *  So let's use P[d][t][s] represent the maximum profits we can gain, where d is the day, and t is the transactions, and s is the stocks.
 *  Here we simplify the problem, assume we can have at most 1 stock.
 *
 *  So for d-th day with t transactions, there are two status: P[d][t][0] and P[d][t][1]
 *  P[d][t][0] = max(P[d-1][t][0], P[d-1][t][1] + prices[d]), P[d-1][t][0] means do nothing today, P[d-1][t][1] means we have one stock in hand yesterday.
 *  P[d][t][1] = max(P[d-1][t][1], p[d-1][t-1][0] - prices[d]),
 *  to guarantee we have 1 stock in hand today, if we have nothing yesterday, we have to make one transaction.
 *
 * More details:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002?page=3
 *
 * Created by xingfeiy on 7/31/18.
 */
public class StockProblems {
    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
     * one transaction is allowed.
     * P[d][1][0] = max(P[d-1][1][0], P[d-1][1][1] + prices[d])
     * P[d][1][1] = max(P[d-1][1][1], p[d-1][0][0] - prices[d]) = max(P[d-1][1][1], - prices[d])
     * p[d-1][0][0] = 0 if only 0 transaction is allowed.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        int p_d10 = 0, p_d11 = Integer.MIN_VALUE; //p_d11 means without prices list but have one stock in hand, that's impossible.
        for(int price : prices) {
            p_d10 = Math.max(p_d10, p_d11 + price);
            p_d11 = Math.max(p_d11, -price);
        }
        return p_d10;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
     * You may complete as many transactions as you like.
     * P[d][t][0] = max(P[d-1][t][0], P[d-1][t][1] + prices[d])
     * P[d][t][1] = max(P[d-1][t][1], P[d-1][t-1][0] - prices[d]) = max(P[d-1][t][1], P[d-1][t][0] - prices[d])
     * Since the t is infinity here, PP[d-1][t-1][0] = P[d-1][t][0]
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        int p_dt0 = 0, p_dt1 = Integer.MIN_VALUE;
        for(int price : prices) {
            p_dt0 = Math.max(p_dt0, p_dt1 + price);
            p_dt1 = Math.max(p_dt1, p_dt0 - price);
        }
        return p_dt0;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
     * You may complete at most two transactions.
     *
     * Since at most two transactions is allowed, we will have 4 status for any day.
     * P[d][1][0], P[d][1][1], P[d][2][0], P[d][2][1]
     * P[d][1][0] = max(P[d-1][1][0], P[d-1][1][1] + price[d])
     * P[d][1][1] = max(P[d-1][1][1], P[d-1][0][0] - price[d]) = max(P[d-1][1][1], -price[d])
     * P[d][2][0] = max(P[d-1][2][0], P[d-1][2][1] + price[d])
     * P[d][2][1] = max(P[d-1][2][1], P[d-1][1][0] - price[d])
     *
     * We have unknown variables: p_d10, p_d11, p_d21, p_d20
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        int p_d10 = 0, p_d11 = Integer.MIN_VALUE, p_d20 = 0, p_d21 = Integer.MIN_VALUE;
        for(int price : prices) {
            p_d10 = Math.max(p_d10, p_d11 + price);
            p_d11 = Math.max(p_d11, -price);
            p_d20 = Math.max(p_d20, p_d21 + price);
            p_d21 = Math.max(p_d21, p_d10 - price);
        }
        return Math.max(p_d10, p_d20);
    }
}
