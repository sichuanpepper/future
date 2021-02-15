package com.future.experience.linying;

/**
 * https://leetcode.com/problems/can-place-flowers/
 *
 * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
 * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 *
 *
 *
 * Example 1:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 * Example 2:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 */
public class CanPlaceFlowers {
    /**
     * We can find the continuous sub-array with value 0, then compute home many flowers are able to put into sub-array
     * sub-array length:
     * 1 -> 0
     * 2 -> 0
     * 3 -> 1
     * 4 -> 1
     * 5 -> 2
     * ..., actually, first put 0, and (1, 0), (1, 0)...
     * so we can get number = (length - 1) / 2
     *
     * Corner case: the boundary case, before first bed and last bed are considered as '0'
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int res = 0, continueZero = 1;
        for(int bed : flowerbed) {
            if(bed == 0) {
                continueZero++;
            } else {
                res += (continueZero - 1) / 2;
                continueZero = 0;
            }
        }
        res += continueZero / 2;
        return res >= n;
    }
}
