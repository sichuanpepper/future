package com.future.round2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/reach-a-number/description/


 You are standing at position 0 on an infinite number line. There is a goal at position target.

 On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

 Return the minimum number of steps required to reach the destination.

 Example 1:
 Input: target = 3
 Output: 2
 Explanation:
 On the first move we step from 0 to 1.
 On the second step we step from 1 to 3.
 Example 2:
 Input: target = 2
 Output: 3
 Explanation:
 On the first move we step from 0 to 1.
 On the second move we step  from 1 to -1.
 On the third move we step from -1 to 2.
 Note:
 target will be a non-zero integer in the range [-10^9, 10^9].

 * Created by xingfeiy on 1/6/18.
 */
public class Problem754 {
    public int reachNumber(int target) {
        Queue<Integer> queue = new LinkedList<>();
        int curStep = 0;
        queue.offer(0);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int tmp = queue.poll();
                if(tmp == target) return curStep;
                queue.offer(tmp + (curStep + 1));
                queue.offer(tmp - (curStep + 1));
            }
            curStep++;
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        Problem754 p = new Problem754();
        System.out.println(p.reachNumber(1));
    }
}
