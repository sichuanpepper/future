package com.future.experience.gugou;

import java.util.Arrays;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume
 * that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing).
 * You may assume that the earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Example:
 *
 * HitCounter counter = new HitCounter();
 * // hit at timestamp 1.
 * counter.hit(1);
 * // hit at timestamp 2.
 * counter.hit(2);
 * // hit at timestamp 3.
 * counter.hit(3);
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 * // hit at timestamp 300.
 * counter.hit(300);
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 *
 *
 * 1. We just need maintain the hits in the past 5 min.
 * 2. Let's start from simple case, let's say we want to count hits in the past 5s
 *      - We use an array of length 5.
 *      - Get hit at 1s, add it to the array[0], 2s to 5s do the same thing
 *      - Get hit at 6s, the count at 1s is expired, we can just overwrite it.
 *      - Get hit at 6s again, this time we can't overwrite, we should add.
 *      - To choose overwrite or add, we should compare the timestamp.
 *
 *  Thread safe?
 *
 */
public class HitCounter {
    private static final int SIZE = 300;

    private int[] tstamp = new int[SIZE];

    //AtomicInteger
    private int[] counter = new int[SIZE];

    synchronized public void hit(int t) {
        int idx = t % SIZE;
        if(tstamp[idx] == t) {
            counter[idx]++;
        } else {
            tstamp[idx] = t;
            counter[idx] = 1;
        }
    }

    public int getHits(int t) {
        int count = 0;
        for(int i = 0; i < SIZE; i++) {
            if(t - tstamp[i] <= SIZE) {
                count += counter[i];
            }
        }
        return count;
    }
}
