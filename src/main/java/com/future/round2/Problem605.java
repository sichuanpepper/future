package com.future.round2;

/**
 * https://leetcode.com/problems/can-place-flowers/description/
 *
 Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False
 Note:
 The input array won't violate no-adjacent-flowers rule.
 The input array size is in the range of [1, 20000].
 n is a non-negative integer which won't exceed the input array size.

 * Created by xingfeiy on 3/22/18.
 */
public class Problem605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int contiguousZero = 1; //init as 1 since out of the boundary of flowerbed considered as 0.
        int res = 0;
        for(int i = 0; i < flowerbed.length && res <= n; i++) {
            if(flowerbed[i] == 0) {
                contiguousZero++;
            } else {
                res += (contiguousZero - 1) / 2;
                contiguousZero = 0;
            }
        }
        res += contiguousZero > 0 ? contiguousZero / 2 : 0;
        return res >= n;
    }
}
