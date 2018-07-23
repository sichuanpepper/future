package com.future.experience.diuhezi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xingfeiy on 7/21/18.
 */
public class TokenBucket2 {
    private TokenProducer producer = null;

    private BlockingQueue<Integer> bucket = null;
    private int capacity = 10;
    private int fillRate = 1;
    public TokenBucket2(int capacity, int fillRate) {
        this.capacity = capacity;
        this.fillRate = fillRate;
        bucket = new ArrayBlockingQueue<Integer>(this.capacity);
    }



    private class TokenProducer implements Runnable {
        private BlockingQueue bucket = null;

        private int capacity = 0;

        public TokenProducer(BlockingQueue bucket, int capacity) {
            this.bucket = bucket;
            this.capacity = capacity;
        }

        @Override
        public void run() {
            while (true) {
                while (this.bucket.size() >= capacity) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.bucket.add(new Random().nextInt(10000000));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class TokenConsumer implements Runnable {
        private BlockingQueue<Integer> bucket = null;

        public TokenConsumer(BlockingQueue bucket) {
            this.bucket = bucket;
        }

        public List<Integer> getTokens(int n) {
            List<Integer> res = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                while (this.bucket.size() < 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                res.add(this.bucket.poll());
            }
            return res;
        }

        @Override
        public void run() {

        }
    }
}
