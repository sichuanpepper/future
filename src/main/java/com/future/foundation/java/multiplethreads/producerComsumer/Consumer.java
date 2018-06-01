package com.future.foundation.java.multiplethreads.producerComsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by xingfeiy on 5/31/18.
 */
public class Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String msg;
            while (!(msg = this.queue.take()).equals("Over")) {
                System.out.println(Thread.currentThread().getName() + " - Consumer is consuming " + msg);
            }
            System.out.println("Done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
