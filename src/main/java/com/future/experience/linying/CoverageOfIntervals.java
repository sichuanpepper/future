package com.future.experience.linying;

import java.util.PriorityQueue;

/**
 * 实现一个datastructure class，里面有两个function，一个是 void addIntervals(int from, int to)，另一个是int getCoverageLength();
 * 比如:
 * addIntervals(3,7)
 * addIntervals(8,10)
 * addIntervals(1,5)
 * getCoverageLength() -> 6 + 2 = 8
 *
 * Analyze:
 * Ask the frequency of each function...
 *
 *
 */
public class CoverageOfIntervals {

    private PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

    private int length = 0;

    public void addIntervals(int from, int to) {
        int[] interval = new int[]{from, to};
        while (queue.peek()[0] <= to) {
            int[] peek = queue.poll();
            length -= peek[1] - peek[0];
//            interval
        }
    }

    public int getCoverageLength() {
        return 0;
    }
}
