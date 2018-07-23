package com.future.foundation.java.multiplethreads;

import com.future.foundation.threads.ThreadExtend;

/**
 * http://tutorials.jenkov.com/java-concurrency/read-write-locks.html
 *
 * - We assume write is more important than read
 * - Read access, there's no write lock and no write request.
 * - Write access, there's no read lock and no write request.
 *
 * Created by xingfeiy on 7/21/18.
 */
public class ReadWriteLock {
    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void readLock() throws InterruptedException {
        while (writers > 0 || writeRequests > 0) wait();
        readers++;
    }

    public synchronized void readUnlock() throws InterruptedException {
        readers--;
        //why notifyAll not notify?
        // - If a read thread got the notify, and there are other write threads are waiting, nothing will happen.
        // - If there's no write threads, all read threads are waking up if we use notifyAll()
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        writeRequests++; //write has high priority
        while (readers > 0 || writers > 0) wait();
        writers++;
        writeRequests--;
    }

    public synchronized void writeUnlock() throws InterruptedException {
        writers--;
        notifyAll();
    }


    public static void main(String args) {

    }
}
