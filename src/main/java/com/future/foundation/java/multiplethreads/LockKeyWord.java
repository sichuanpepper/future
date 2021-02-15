package com.future.foundation.java.multiplethreads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockKeyWord {
    private int counter = 0;

    private Lock lock = new ReentrantLock();

    public void count() {
        System.out.println(Thread.currentThread().getName() + " is entering method count()...");
        lock.lock();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.counter++;
        System.out.println(Thread.currentThread().getName() + " increment counter to " + this.counter);
        lock.unlock();
    }


    /**
     * Output:
     * Thread-0 is entering method count()...
     * Thread-3 is entering method count()...
     * Thread-2 is entering method count()...
     * Thread-1 is entering method count()...
     * Thread-4 is entering method count()...
     * Thread-0 increment counter to 1
     * Thread-1 increment counter to 2
     * Thread-3 increment counter to 3
     * Thread-2 increment counter to 4
     * Thread-4 increment counter to 5
     *
     * Takeaways:
     * - No guarantee which thread will enter the sync block if there are multiple threads are blocked.
     * @param args
     */
    public static void main(String[] args) {
        LockKeyWord lockKeyWord = new LockKeyWord();
        for(int i = 0; i < 5; i++) {
            new Thread(()->lockKeyWord.count()).start();
        }
    }
}
