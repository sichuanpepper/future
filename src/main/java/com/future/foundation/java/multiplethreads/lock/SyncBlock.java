package com.future.foundation.java.multiplethreads.lock;

/**
 * synchronized block is reentrent.
 * thread 1 enter the countOne function, it's also able to enter countTwo function since thread 1 already has the lock on this.
 */
public class SyncBlock {
    private int val = 1;

    public synchronized void countOne() {
        System.out.println(Thread.currentThread().getName() + " counts one: " + ++val);
        countTwo();
    }

    public synchronized void countTwo() {
        this.val += 2;
        System.out.println(Thread.currentThread().getName() + " counts two: " + val);
    }

    public static void main(String[] args) {
        SyncBlock sb = new SyncBlock();
        for(int i = 0; i < 10; i++) {
            new Thread(()->sb.countOne()).start();
        }
    }
}
