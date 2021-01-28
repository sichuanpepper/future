package com.future.experience.gugou;

/**
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 *
 * Problem Translation: Find the smallest subarray sum of length len(cardPoints) - k
 */
public class MaximumPointsYouCanObtainfromCards {
    public int maxScore(int[] cardPoints, int k) {
        int left = 0, right = left + (cardPoints.length - k) - 1, total = 0, sumOfWindow = 0;
        for(int i = 0; i < cardPoints.length; i++) {
            if(i <= right) sumOfWindow += cardPoints[i];
            total += cardPoints[i];
        }

        int min = sumOfWindow;
        while(right < cardPoints.length - 1) {
            sumOfWindow = sumOfWindow - cardPoints[left++] + cardPoints[++right];
            min = Math.min(min, sumOfWindow);
        }
        return total - min;
    }
}
