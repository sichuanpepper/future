package com.future.foundation.dp;

import com.future.utils.DisplayUtils;

/**
 * Created by someone on 5/8/17.
 */
public class CoinChanges {

    private static int[] coins = new int[]{1, 3, 5};

    /**
     * Analysis:
     *
     * Has optimal substructure?
     * Yes, take a coin away, the optimal problem to substructure is sub optimal problem of original problem.
     *
     * Has overlapping subproblems?
     * f(n) = f(n - values[0]) + f(n - values[1]) + ... + f(n - value[k])
     * @param n
     * @return
     */
    public static int bottomUp(int n) {
        //it's easy to find the f(n) = min{f(n - 1), f(n - 3), f(n - 5)} + 1
        int[] values = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
//            values[i] = Integer.MAX_VALUE;
            values[i] = n;
        }
        values[0] = 0;
        for(int i = 1; i < n + 1; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(coins[j] <= i) {
                    values[i] = Math.min(values[i - coins[j]] + 1, values[i]);
                }
            }
        }
        DisplayUtils.printArray(values);
        return values[n];

    }

    public static int topDown(int n) {
        if(n < 0) {
            return n;
        }
        if(n == 0) {
            return 1;
        }
        if(n == 1 || n == 3 || n == 5) {
            return 1;
        }

        return Math.min(Math.min(topDown(n -1), topDown(n -3)), topDown(n - 5)) + 1;
    }

    public static void main(String[] args) {
        System.out.println(bottomUp(10));
        System.out.println(topDown(10));
    }
}
