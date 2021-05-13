package com.future.experience.fsbk.eley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingfeiy on 5/17/18.
 */
public class SubArraySum {
    /**
     * Analyze: presum
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length < 1) return 0;
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            nums[i] += (i > 0) ? nums[i - 1] : 0;
            if(sums.containsKey(nums[i] - k)) count += sums.get(nums[i] - k);
            sums.put(nums[i], sums.getOrDefault(nums[i], 0 ) + 1);
        }
        return count;
    }
}
