package com.future.experience.gugou;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * bus arrival times: [10:00am, 10:15am]
 * capacity: [1, 2]
 * customers arrival times: [9:50am, 10:05am, 10:10am]
 * 然后面试官让我自己说在最晚什么时间可以赶上末班车。我认为google 的面试题需要自己分析和面试官不断交流得出解题方案，最后实现。当然我的见解不一定对，毕竟我悲剧了。
 * 我就不给出解法了。大家可以讨论。
 */
public class CatchUpBus {
    /**
     * Assume the passengers are first come first server, sounds lke a queue.
     * The last time to catch up the bus, which means we should arrive station before last passenger who can take the bus.
     * So we can put all passengers into a queue sorted by arrive time.
     * When a bus arrive, it takes n passengers based on its capacity. (poll() n times from queue)
     *
     * Question need to clarify:
     * - if I and other people arrived to station at same time, can we guarantee that I'm always the first one?
     * - If the last time doesn't exist, for example all bus are full, what's the result we are expected?
     *
     *
     * @return
     */
    public int lastTimeToCacheUpBus(int[] busArriveTime, int[] capacities, int[] paxArriveTime) {
        //assume the given arrays are sorted
        Queue<Integer> paxQueue = new LinkedList<>();
        for(int pax : paxArriveTime) {
            paxQueue.offer(pax);
        }

        int lastPax = -1, totalCapacity = 0;
        for(int i = 0; i < busArriveTime.length; i++) {
            int capacity = capacities[i];
            totalCapacity += capacity;
            while (!paxQueue.isEmpty() && capacity-- > 0) {
                lastPax = paxQueue.poll();
            }
        }

        // if the queue is empty, and total capacity > number of passengers
        if(paxQueue.isEmpty() && totalCapacity > paxArriveTime.length) {
            return busArriveTime[busArriveTime.length - 1];
        }
        return lastPax - 1;
    }

    public static void main(String[] args) {
        CatchUpBus p = new CatchUpBus();
        System.out.println(p.lastTimeToCacheUpBus(new int[]{3, 4, 5}, new int[]{1, 2, 1}, new int[]{1, 2, 3, 3, 3}));
        System.out.println(p.lastTimeToCacheUpBus(new int[]{3, 4, 5}, new int[]{0, 0, 0}, new int[]{1, 2, 3, 3, 3}));
    }
}
