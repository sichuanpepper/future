package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-team-with-no-conflicts/
 *
 * [4,5,6,5]
 * [2,1,2,1]
 */
public class Problem1626 {
    public int bestTeamScore(int[] scores, int[] ages) {
        int[][] pairs = new int[scores.length][2];

        for(int i = 0; i < scores.length; i++) {
            pairs[i] = new int[]{ages[i], scores[i]};
        }

        Arrays.sort(pairs, (obj1, obj2) -> {
            if(obj1[0] == obj2[0]) {
                return Integer.compare(obj1[1], obj2[1]);
            } else {
                return Integer.compare(obj1[0], obj2[0]);
            }
        });
        int[] dp = new int[pairs.length];
        for(int i = 0; i < dp.length; i++) {
            dp[i] = pairs[i][1];
        }

        int max = dp[0];
        for(int i = 1; i < pairs.length; i++) {
            for(int j = 0; j < i; j++) {
                if(pairs[i][0] == pairs[j][0] || pairs[i][1] >= pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + pairs[i][1]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;

    }

    public static void main(String[] args) {
        System.out.println(new Problem1626().bestTeamScore(new int[]{4,5,6,5}, new int[]{2,1,2,1}));
    }
}
