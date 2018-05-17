package com.future.round2;

/**
 * https://leetcode.com/problems/h-index-ii/description/
 * Given an array of citations in ascending order (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.

 According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h
 citations each, and the other N − h papers have no more than hcitations each."

 Example:

 Input: citations = [0,1,3,5,6]
 Output: 3
 Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
 received 0, 1, 3, 5, 6 citations respectively.
 Since the researcher has 3 papers with at least 3 citations each and the remaining
 two with no more than 3 citations each, his h-index is 3.
 Note: If there are several possible values for h, the maximum one is taken as the h-index.

 * Created by xingfeiy on 5/13/18.
 */
public class Problem275 {
    /**
     * Analyze:
     * The problem is, we are going to find a position p in array, where citations[p] >= (l - p), l is the length of citations.
     * example, 0, 1, 3, 5, 6
     * p     citations[p]     l-p    index
     * 5        6        >=    1        1
     * 4        5        >=    2        2
     * 3        3        >=    3        3
     * 2        1        <     4       break
     *
     * Time complexity: O(n), space complexity O(1)
     * beats 40%
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if(citations == null || citations.length < 1) return 0;
        int p = citations.length - 1, index = 0;
        while(p >= 0) {
            if(citations[p] >= citations.length - p--) {
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    /**
     * Analyze:
     * based on the analysis above, it's easy to see, the column citations[p] is descending sorted, and column l-p is ascending sorted.
     * that means, all element in range[0, p) are not able to be index, range [p, length) are valid index, and position p
     * generated maximum index.
     *
     * So, the problem looks like the problem bad version, binary search is applied.
     *
     * beats 70%
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        if(citations == null || citations.length < 1) return 0;
        int left = 0, right = citations.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(citations[mid] >= citations.length - mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if(citations[left] >= citations.length - left) return citations.length - left;
        return citations[right] >= citations.length - right ? citations.length - right : 0;
    }

    public static void main(String[] args) {

    }
}
