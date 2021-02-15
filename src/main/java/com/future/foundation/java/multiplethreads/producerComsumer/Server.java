package com.future.foundation.java.multiplethreads.producerComsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xingfeiy on 5/31/18.
 */
public class Server {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        Producer producer = new Producer(queue);
        Thread prod1 = new Thread(producer, "prod1");

        Consumer consumer1 = new Consumer(queue);
        Thread cons1 = new Thread(consumer1, "cons1");
        Consumer consumer2 = new Consumer(queue);
        Thread cons2 = new Thread(consumer2, "cons2");

        prod1.start();
        cons1.start();
        cons2.start();
    }
}
