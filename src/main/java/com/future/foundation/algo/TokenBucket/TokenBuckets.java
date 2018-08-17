package com.future.foundation.algo.TokenBucket;

import java.util.concurrent.TimeUnit;

/**
 * Created by xingfeiy on 8/12/18.
 */
public class TokenBuckets {
    private TokenBuckets() {}

    public static TokenBucket newFixedIntervalRefill(long capacityTokens, long refillTokens, long period, TimeUnit unit)
    {
        RefillStrategy strategy = new FixedIntervalRefillStrategy(refillTokens, period, unit);
        return new TokenBucket(capacityTokens, strategy);
    }
}
