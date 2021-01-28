package com.future.round2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/
 */
public class Problem1553 {
    public int minDays(int n) {
        if(n < 0) return 0;
        int days = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        while(!queue.isEmpty()) {
            days++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int curNum = queue.poll();
                if(curNum == 0) {
                    return days - 1;
                }
                queue.offer(curNum - 1);
                if(curNum % 2 == 0) {
                    queue.offer(curNum / 2);
                }
                if(curNum % 3 == 0) {
                    queue.offer(curNum / 3);
                }
            }
        }
        return days;
    }

    public int minDays2(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        int res = 0;
        Set<Integer> set = new HashSet<>();
        while(!q.isEmpty()){
            res++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                int cur = q.poll();
                if(cur == 0){
                    return res - 1;
                }
                if(set.contains(cur)){
                    continue;
                }
                else{
                    set.add(cur);
                }
                if(cur % 3 == 0){
                    q.offer(cur / 3);
                }
                if(cur % 2 == 0){
                    q.offer(cur / 2);
                }
                q.offer(cur - 1);
            }
        }

        return res;

    }

    public static void main(String[] args) {
        System.out.println(new Problem1553().minDays(182));
        System.out.println(new Problem1553().minDays2(182));
    }
}
