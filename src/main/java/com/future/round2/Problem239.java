package com.future.round2;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/sliding-window-maximum/description/

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.

 You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

 Example:

 Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 Output: [3,3,5,5,6,7]
 Explanation:

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Note:
 You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?

 * Created by xingfeiy on 5/20/18.
 */
public class Problem239 {
    /**
     * Analyze:
     * The straightforward way is we maintain a priority queue of size k, each time we get the peek from queue.
     *
     * Time complexity O(nlogk), insert and remove are logk in the priority queue.
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 1) return new int[]{};
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for(int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if(queue.size() == k) {
                res[index++] = queue.peek();
                queue.remove(nums[i + 1 - k]);
            }
        }
        return res;
    }


    /**
     * The follow up required linear time, that means we can't use priority queue or similar data structures.
     * Do we really need sort the elements in the queue?
     * Actually, what we have to know is, if maximum one is out of window, who's the next biggest?
     *
     * For each window, likes [1  3  -1] -3  5  3  6  7
     * It's easy to know the maximum element in current window, and the elements before maximum are useless.
     * The elements after maximum which are potentially maximum in next windows, we don't know yet.
     * When we move window to next new element, we do same thing, the potentially elements which smaller than new elements now became to useless.
     *
     * Let's use a collection to store these potential maximums.
     * [1  3  -1] -3  5  3  6  7   => (3, -1), 1 is first in, but after 3 came, 1 is useless, just take away.
     * 1 [3  -1  -3] 5  3  6  7    => (3, -1, -3), there's no potential elements are less than new one -3
     * 1  3 [-1  -3  5] 3  6  7    => (5), there are two things happened, first, element 3 is out of date, remove it, second,
     *                                      new element 5 comes, all potentail elements are less than 5.
     * So, it's easy to see, the first element in the collection is the maximum element in current window, the rest are potential maximums in next windows.
     *
     * Now, let's select a data structure for this collection, here are operations in this solution
     * - insert to the end
     * - remove from the end
     * - remove from start
     * - get start
     *
     * Deque!!!
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowFollowup(int[] nums, int k) {
        if(nums == null || nums.length < 1) return new int[]{};

        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        for(int i = 0; i < nums.length; i++) {
            //first, take stale(out of date) out
            if(!deque.isEmpty() && deque.peek() <= i - k) deque.poll();

            //second, take the smaller potential candidates away.
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();

            //third, offers new potential
            deque.offer(i);

            //last, place maximum in current window
            if(i - k + 1 >= 0) res[index++] = nums[deque.peek()];

        }
        return res;
    }

}
