package com.future.experience.diuhezi;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xingfeiy on 7/21/18.
 */
public class TokenBucket implements Runnable{
    private int capacity = 10;

    private int fillRate = 1; // means the seconds to generate a token

    private BlockingQueue<Integer> bucket;

    private boolean alive = true;

    public TokenBucket(int capacity, int fillRate) {
        this.capacity = capacity;
        this.fillRate = fillRate;
        bucket  = new ArrayBlockingQueue<>(this.capacity);
    }

    public void stop() {
        alive = false;
    }

    /**
     * Get n tokens from bucket.
     * @param n
     * @return
     */
    public List<Integer> getTokens(int n) {
        List<Integer> res = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            while (this.bucket.isEmpty()) {
                try {
                    System.out.print("Blocking since the bucket is empty!");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res.add(bucket.poll());
        }
        return res;
    }

    /**
     * put n tokens into bucket
     * @param n
     */
    public void putTokens(int n) {}

    @Override
    public void run() {
        Random random = new Random();
        while (alive) {
            while (bucket.size() == this.capacity) try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bucket.offer(random.nextInt(1000000));

            try {
                Thread.sleep(this.fillRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        TokenBucket bucket = new TokenBucket(1000, 1);
        Thread threadA = new Thread(bucket);
        threadA.start();

//        bucket.getTokens(1000);

    }
}
