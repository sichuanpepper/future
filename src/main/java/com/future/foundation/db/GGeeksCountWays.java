package com.future.foundation.db;

/**
 *
 There is a street of length n and as we know it has two sides. Therefore a total of 2 * n spots are available.
 In each of these spots either a house or an office can be built with following 2 restrictions:
 1. No two offices on the same side of the street can be adjacent.
 2. No two offices on different sides of the street can be exactly opposite to each other i.e. they can’t overlook each other.
 There are no restrictions on building houses and each spot must either have a house or office.
 Given length of the street n, find total number of ways to build the street.

 Source: Intuit Interview
 Examples:

 Input : 2
 Output : 7
 Please see below diagram for explanation.

 Input : 3
 Output : 17

 http://www.geeksforgeeks.org/count-ways-build-street-given-constraints/

 * Created by someone on 9/6/17.
 */
public class GGeeksCountWays {
    public long countWays(int n) {
        if(n < 1) {
            return 0;
        }

        //there are two choices for each row, build two houses or one house and one office.
        int[][] dp = new int[n + 1][2]; //dp[n][0] indicates choice 1, and dp[n][1] indicates choice 2.

        //init
        dp[1][0] = 1;  //there's one way to build two houses.
        dp[1][1] = 2;  //there are two ways to build one house and one office.

        for(int i = 2; i <= n; i++) {
            //if the current row choose two houses, there's no restriction,
            // and no matter what the choice the pre-row made, there's only one way to build two house.
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            // if the current row choose one house and one office.
            //  - if the pre-row made two houses choice, there's no
            // restriction and there are two ways to build one house + one office, so we came out 2 * dp[i - 1][0].
            // - if the pre-row made one house and one office choice, there are restrictions, and there's only one way
            // to build current row for each pre-row choice, so we came out dp[i - 1][1].
            dp[i][1] = 2 * dp[i - 1][0] + dp[i - 1][1];
        }
        //actually, the result could be greater than Long.MAX_VALUE.
        return dp[n][0] + dp[n][1];
    }

    public static void main(String[] args) {
        System.out.println(new GGeeksCountWays().countWays(0));
        System.out.println(new GGeeksCountWays().countWays(1));
        System.out.println(new GGeeksCountWays().countWays(2));
        System.out.println(new GGeeksCountWays().countWays(3));
        System.out.println(new GGeeksCountWays().countWays(1000));
    }
}
