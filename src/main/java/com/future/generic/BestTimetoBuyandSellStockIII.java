package com.future.generic;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Thoughts:
 * Basically, it's an array partition problem, we need to maximize the (max_profit of partition 1) + (max_profit of partition 2)
 *
 * We are given n days, for any day between 1 and n, we can finish first order before current day and second order after current day.
 * We need to maximize that.
 *
 * It's easy to get the maximum profit with one transaction in a given rang days.
 *
 * ex:
 *        day   1,2,3,4,5,6,7,8
 *     intput: [3,3,5,0,0,3,1,4]
 *     order1: [0,0,2,2,2,3,3,4]   //the maximum profit we can get on day i
 *     order2: [4,4,4,4,4,3,3,0]   //the maximum profit we can get from day i
 *
 *
 * Array partition problem:
 * https://leetcode.com/problems/partition-array-into-disjoint-intervals/
 */
public class BestTimetoBuyandSellStockIII {
    public static int maxProfit(int[] prices) {
        int lowest = prices[0];
        int[] array1 = new int[prices.length];

        for(int i = 1; i < prices.length; i++) {
            array1[i] = Math.max(0, prices[i] - lowest);
            array1[i] = Math.max(array1[i], array1[i - 1]);
            lowest = Math.min(prices[i], lowest);

        }


        int res = 0;
        int largest = prices[prices.length - 1];
        for(int i = prices.length - 2; i >= 0; i--) {
            int tmp = Math.max(0, largest - prices[i]);
            res = Math.max(res, tmp + array1[i]);
            largest = Math.max(largest, prices[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,3,5,0,0,3,1,4}));
    }
}
