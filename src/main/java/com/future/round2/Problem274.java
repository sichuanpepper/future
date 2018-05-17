package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/h-index/description/
 *
 Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the
 researcher's h-index.

 According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h
 citations each, and the other N − h papers have no more than h citations each."

 Example:

 Input: citations = [3,0,6,1,5]
 Output: 3
 Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 received 3, 0, 6, 1, 5 citations respectively.
 Since the researcher has 3 papers with at least 3 citations each and the remaining
 two with no more than 3 citations each, his h-index is 3.
 Note: If there are several possible values for h, the maximum one is taken as the h-index.

 * Created by xingfeiy on 5/13/18.
 *
 * [tags] facebook
 */
public class Problem274 {
    /**
     * Analyze:
     * The first thing is understand why the index of example is 3.
     * Let's say,
     * - pick up 6, then the index is 1, we have to check others, 0, 1 are fine, but 3 and 5 violated rule.
     * - pick up 5 and 6, then the index is 2, 0, 1 are fine, but same as above, 3 and 5 violated rule.
     * - pick up 3, 5, 6, index is 3, all rest (0, 1) are fine, that's the valid index.
     *
     * So the straightforward way is sort first, and use two pointer left and right.
     * 0, 1, 3, 5, 6
     * index init as 0
     * while left <= right
     *  if citations[left] <= index move left
     *  else move right and index++
     *
     *  Time complexity O(nlg(n)), Space complexity O(1)
     *  beats 17%
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if(citations == null || citations.length < 1) return 0;
        Arrays.sort(citations);
        int left = 0, right = citations.length - 1, index = 0;
        while(left <= right) {
            if(citations[left] <= index) {
                left++;
            } else {
                right--;
                index++;
            }
        }
        return index;
    }

    /**
     * Analyze:
     * the index range is [0, n], where n is the length of citations.
     * Assuming we have n + 1 buckets, and we put each paper into correspoding buckets based on its citations,
     * ex, the paper has 1 citation, then this paper should go bucket1.
     *
     * A scientist has index h if h of his/her N papers have at least h citations each
     * which means the number of papers >= citations.
     *          3,0,6,1,5
     * buckets  1  1  0  1  0  2
     * index    0, 1, 2, 3, 4, 5
     *
     * Time complexity O(n), space complexity O(n)
     * beats 99%
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        if(citations == null || citations.length < 1) return 0;
        int[] buckets = new int[citations.length + 1];
        for(int citation : citations) {
            if(citation >= buckets.length) {
                buckets[buckets.length - 1]++;
            } else {
                buckets[citation]++;
            }
        }

        int count = 0;
        for(int i = buckets.length - 1; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) return i;
        }
        return 0;
    }

}
