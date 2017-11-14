package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/task-scheduler/description/
 *
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different
 * letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.

 However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals
 that CPU are doing different tasks or just be idle.

 You need to return the least number of intervals the CPU will take to finish all the given tasks.

 Example 1:
 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 Note:
 The number of tasks is in the range [1, 10000].
 The integer n is in the range [0, 100].
 * Created by someone on 10/29/17.
 */
public class Problem621 {
    /**
     * The key point here is,
     * 1. The final number of intervals could be (think about it's a frame)
     *      - The size of tasks OR
     *      - The size of tasks + the number of idles.
     * 2. If the most frequency task can be separated in either frame above, the other tasks must be suitable in this frame.
     *
     * So the problem is, just find that frame.
     * Tasks AAABBB n = 2
     * First, the most frequency task is A, so we can get the frame like this A**A**A,
     * current frame length = (n + 1) * (f - 1) + 1, where f is the frequency of A. And it's the Minimum size.
     * The only thing left is the last "wall", the thickness of the wall.
     *
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {
        char[] array = new char[26];
        for(char ch : tasks) {
            array[ch - 'A']++;
        }
        Arrays.sort(array); //find the most frequency task - array[25]
        int thickness = 1;
        while (thickness < 26 && array[25 - thickness] == array[25]) {
            thickness++;
        }
        return Math.max(tasks.length, (n + 1) * (array[25] - 1) + thickness);
    }

    /**
     * The idea of this solution which is similar with first one, sort the frequencies by priority queue.
     * It's not a better solution than first one, just want to practice the usage of priority queue.
     * Still, imagine that we deal with in a frame, in this solution, we deal with the frame slot by slot.
     * And still keep in mind, if the frame is good for the most frequency one, it must be good for others.
     *
     * So we always process it from the most frequency in each round.
     * tasks = ["A","A","A","B","B","B", "C"], n = 2
     * First, we got the priority queue
     * A => 3; B => 3; C => 1
     * first round, we build the first slot, A, B, C, then we get the updated queue, A => 2, B => 2, we have enough characters to fill first slot
     * Second round, build the second slot, A, B, ?, then we get the update queue, A => 1, B => 1, in this round, we don't have enough characters to fill slot.
     * Third round, same as second round.
     *
     * So, in each round, there are two things we need handle.
     * 1. Check if we have enough characters to fill the slot, if don't, that means we have to use idles to fill it.
     * 2. Check if we still have rest characters in current entry set, if we do, add it back to priority queue.
     *
     *
     * @param tasks
     * @param n
     * @return
     */
    public static int solution2(char[] tasks, int n) {
        //first, build the priority queue.
        // The common queue used to FIFO which is the LinkedList, Queue is an interface.
        // The PriorityQueue is a sub class extends from AbstractQueue, and also implement the interface Queue.

        //There are multiple contractors here, the default initial capacity is 11.
        //PriorityQueue(Comparator<? super E> comparator), specified a comparator,
        // otherwise, order elements according their natural sorting.
        Map<Character, Integer> map = new HashMap<>();
        for(char t : tasks) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pg = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? Character.compare(a.getKey(), b.getKey()) : Integer.compare(a.getValue(), b.getValue()));

        pg.addAll(map.entrySet());

        int count = 0;
        while (!pg.isEmpty()) {
            int slot = n + 1;
            //pick up an element from each entry set in each round.
            List<Map.Entry<Character, Integer>> tmpList = new ArrayList<>();
            while (slot > 0 && !pg.isEmpty()) {
                Map.Entry<Character, Integer> top = pg.poll();
                //top.getValue()-- ???
                top.setValue(top.getValue() - 1);
                tmpList.add(top);
                slot--;
                count++;
            }

            //after this round, add the entry set with rest elements back to priority queue for next round.
            for(Map.Entry<Character, Integer> entry : tmpList) {
                if(entry.getValue() > 0)
                pg.add(entry);
            }

            //Don't forgot check this.
            if(pg.isEmpty()) {
                break;
            }

            //if the current slot is still not full, which means the idles are applied.
            if(slot > 0) count += slot;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3));
        System.out.println(solution2(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(solution2(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
        System.out.println(solution2(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3));
    }
}
