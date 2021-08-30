package com.future.foundation.java.multiplethreads;

/**
 * Task: use multiple threads to count a number from 1 to 1000
 */
public class Counter {
    public int val = 1;

    public synchronized int count() {
        if(val > 1000) {
            return val;
        }
        System.out.println(Thread.currentThread().getName() + " is counting: " + val++);
        return val;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        try {
            for(int i = 0; i< 50; i++) {
                new Thread(()->{
                    while (counter.val <= 1000) {
                        counter.count();
                    }
                }).start();
            }
        } catch (Exception ex) {

        }
    }
}
