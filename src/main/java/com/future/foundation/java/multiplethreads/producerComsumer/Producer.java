package com.future.foundation.java.multiplethreads.producerComsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xingfeiy on 5/31/18.
 */
public class Producer implements Runnable {
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            for(int i = 0; i < 100; i++) {
                this.queue.put("Message " + i);
                System.out.println("Producing message " + i);

                Thread.sleep(r.nextInt(1000));
            }

            //add exit message
            this.queue.put("Over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
