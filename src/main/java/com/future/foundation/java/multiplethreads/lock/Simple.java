package com.future.foundation.java.multiplethreads.lock;

public class Simple {
    private int val = 0;

    private MyLock lock = new MyLock();

    public void addOne() {
        lock.lock();
        this.val++;
        lock.unlock();
    }

    public void addTwo() {
        lock.lock();
        this.val += 2;
        lock.unlock();
    }
}
