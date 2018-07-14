package com.future.experience.aibiying;

/**
 * Created by xingfeiy on 7/12/18.
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int n : nums) res ^= n;
        return res;
    }
}
