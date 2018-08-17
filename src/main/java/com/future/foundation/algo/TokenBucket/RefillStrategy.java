package com.future.foundation.algo.TokenBucket;

/**
 * Created by xingfeiy on 8/12/18.
 */
public interface RefillStrategy {
    long refill();

    long getIntervalInMillis();
}
