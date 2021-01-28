package com.future.foundation.systemdesign.ratelimiter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TokenBucket {
    private int maxSize;

    private int fillRate;

    private int curSize;

    private long lastTime;  //unix time

    public TokenBucket(int maxSize, int fillRate) {
        this.maxSize = maxSize;
        this.fillRate = fillRate;
        this.curSize = maxSize;
        this.lastTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();
        if(this.curSize >= tokens) {
            this.curSize -= tokens;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = Timestamp.valueOf(LocalDateTime.now()).getTime();
        int cntOfFill = (int)(now - lastTime) * fillRate;
        curSize = Math.min(maxSize, curSize + cntOfFill);
        lastTime = now;
    }
}
