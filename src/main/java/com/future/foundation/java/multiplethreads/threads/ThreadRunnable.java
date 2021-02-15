package com.future.foundation.java.multiplethreads.threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xingfeiy on 4/3/18.
 */
public class ThreadRunnable implements Runnable {
    private static Queue<String> messages = new LinkedList<>();

    private int counter = 0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running, and the counter is " + ++counter);
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " adding to queue, size is " + messages.size());
            messages.offer(Thread.currentThread().getName() + " ." + i);
        }
    }



    public static void main(String[] args) {
        ThreadRunnable threadRunnable = new ThreadRunnable();
        Thread thread1 = new Thread(threadRunnable);
        Thread thread2 = new Thread(threadRunnable);
        Thread thread3 = new Thread(threadRunnable);

//        thread1.start();
//        thread2.start();
//        thread3.start();


        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 2; i++) {
            Thread thread = new Thread(threadRunnable);
            thread.start();
            threads.add(thread);
        }

        int running = 0;
        do {
            running = 0;
            for(Thread thread : threads) {
                if(thread.isAlive()) running++;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (running > 0);
        System.out.println("Finally, " + messages.size());
        while (!messages.isEmpty()) {
            System.out.println(messages.poll());
        }
    }
}
