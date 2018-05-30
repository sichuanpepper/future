package com.future.round2;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/description/
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 So the median is the mean of the two middle value.

 For example,
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.
 Example:

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2
 *
 * Created by xingfeiy on 5/22/18.
 */
public class Problem295 {
    private PriorityQueue<Integer> left;

    private PriorityQueue<Integer> right;
    /** initialize your data structure here. */
    public Problem295() {
        left = new PriorityQueue<>((a, b) -> b - a);
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(left.isEmpty() || num < left.peek()) {
            left.offer(num);
        } else {
            right.offer(num);
        }
        //rebalance
        if(left.size() - right.size() > 1) right.offer(left.poll());
        if(right.size() - left.size() > 1) left.offer(right.poll());
    }

    public double findMedian() {
        if(left.size() == right.size()) return (right.peek() + left.peek()) / 2.0;
        return left.size() > right.size() ? left.peek() : right.peek();
    }
}
