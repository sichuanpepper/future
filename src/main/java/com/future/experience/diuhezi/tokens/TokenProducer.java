package com.future.experience.diuhezi.tokens;

import java.util.Random;

/**
 * Created by xingfeiy on 7/21/18.
 */
public class TokenProducer implements Runnable {
    private TokenBucket<Integer> tokenBucket = null;

    private int fillRate = 1;

    private int capacity = 0;

    public TokenProducer(TokenBucket<Integer> tokenBucket, int capacity, int fillRate) {
        this.tokenBucket = tokenBucket;
        this.capacity = capacity;
        this.fillRate = fillRate;
    }

    @Override
    public void run() {
        while (true) {
            while (tokenBucket.size() >= capacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.tokenBucket.offer(new Random().nextInt(10000));
            try {
                Thread.sleep(this.fillRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
