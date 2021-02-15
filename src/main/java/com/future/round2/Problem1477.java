package com.future.round2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 */
public class Problem1477 {
    public int minSumOfLengths(int[] arr, int target) {
        //key: sum, value index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curSum = 0;
        //int[]={start, end}
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->Integer.compare(a[1]-a[0], b[1]-b[0]));
        for(int i = 0; i < arr.length; i++) {
            curSum += arr[i];
            if(map.containsKey(curSum - target)) {
                int left = map.get(curSum - target);
                queue.offer(new int[]{left, i});
            }
            map.put(curSum, i);
        }
        int cnt = 2, left = Integer.MIN_VALUE, right = Integer.MAX_VALUE, res = 0;
        while(!queue.isEmpty() && cnt > 0) {
            int[] window = queue.poll();
            if(window[0] >= left || window[1] < right) {
                res += window[1] - window[0];
                cnt--;
                left = window[0];
                right = window[1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem1477 p = new Problem1477();
        //[1,2,2,3,2,6,7,2,1,4,8]
        //5
        System.out.println(p.minSumOfLengths(new int[]{1,2,2,3,2,6,7,2,1,4,8}, 5));
    }
}
