package com.future.experience;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes.

 Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

 It is possible that several hits arrive roughly at the same time.

 Example:
 HitCounter counter = new HitCounter();

 // hit at timestamp 1.
 counter.hit(1);

 // hit at timestamp 2.
 counter.hit(2);

 // hit at timestamp 3.
 counter.hit(3);

 // get hits at timestamp 4, should return 3.
 counter.getHits(4);

 // hit at timestamp 300.
 counter.hit(300);

 // get hits at timestamp 300, should return 4.
 counter.getHits(300);

 // get hits at timestamp 301, should return 3.
 counter.getHits(301);
 Follow up:
 What if the number of hits per second could be very large? Does your design scale?

 * Created by xingfeiy on 7/19/18.
 */
public class DesignHitCounter362 {
    private int[] hits;
    private long[] tmsp;
    private int checkSec;
    public DesignHitCounter362(int checkSec) {
        this.checkSec = checkSec;
        hits = new int[this.checkSec];
        tmsp = new long[this.checkSec];
    }

    public void hit() {
        long curTime = System.currentTimeMillis();
        int index = (int)(curTime % this.checkSec);
        if(tmsp[index] != curTime) {
            hits[index] = 1;
            tmsp[index] = curTime;
        } else {
            hits[index]++;
        }
    }

    public int getHits() {
        long curTime = System.currentTimeMillis();
        int res = 0;
        for(int i = 0; i < tmsp.length; i++) {
            if(curTime - tmsp[i] <= this.checkSec) {
                res += hits[i];
            }
        }
        return res;
    }
}
