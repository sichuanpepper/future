package com.future.foundation.algo.TokenBucket;

import java.util.concurrent.TimeUnit;

/**
 * Created by xingfeiy on 8/12/18.
 */
public class Test {
    public static void main(String[] args) {
        TokenBucket bucket = TokenBuckets.newFixedIntervalRefill(1024 * 10, 1, 1, TimeUnit.SECONDS);
    }
}
