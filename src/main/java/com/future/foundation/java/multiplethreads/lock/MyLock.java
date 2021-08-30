package com.future.foundation.java.multiplethreads.lock;

public class MyLock {
    private boolean isLocked = false;

    public synchronized void lock() {
        try {
            while (this.isLocked) {
                System.out.println(Thread.currentThread().getName() + " is blocked since it's locked by other thread.");
                wait();
            }
            isLocked = true;
        } catch (Exception ex) {}
    }

    public synchronized void unlock() {
        this.isLocked = false;
        notify();
    }
}
