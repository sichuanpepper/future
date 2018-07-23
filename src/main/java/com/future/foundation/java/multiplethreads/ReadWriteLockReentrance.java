package com.future.foundation.java.multiplethreads;

import java.util.HashMap;
import java.util.Map;

/**
 * The problem in ReadWriteLock,
 * - Thread A get read access.
 * - Thread B is trying to get write access, now, there will be a write request is pending.
 * - Thread A get read access again, since thread A get the read lock, it can enter the readLock function again, but now,
 *   there's a write request, so thread A is waiting for thread B, and thread B is waiting for thread A.
 * Created by xingfeiy on 7/21/18.
 */
public class ReadWriteLockReentrance {
    private Map<Thread, Integer> readingThreads = new HashMap<>();

    private int writers = 0;

    private int writeRequests = 0;

    private Thread writingThread = null;

    /**
     * The current thread can reenter if:
     * - there's no writer.
     * - the thread existed in readingThreads map already.
     * - there's no writing request.
     */
    public synchronized void readLock() throws InterruptedException {
        while (!canReaderReenter(Thread.currentThread())) wait();
        readingThreads.put(Thread.currentThread(), readingThreads.getOrDefault(Thread.currentThread(), 0) + 1);
    }

    public synchronized void readUnlock() throws InterruptedException {
        if(readingThreads.get(Thread.currentThread()) == 1) {
            readingThreads.remove(Thread.currentThread());
        } else {
            readingThreads.put(Thread.currentThread(), readingThreads.get(Thread.currentThread()) - 1);
        }
        notifyAll();
    }

    private boolean canReaderReenter(Thread curThread) {
        //the writing thread is itself
        if(writingThread == curThread) return true;
        if(writers > 0) return false;
        if(readingThreads.containsKey(curThread)) return true;
        return !(writeRequests > 0);
    }

    /**
     * If the given thread is the only reader, that means the current thread is able to get write access.
     * @param thread
     * @return
     */
    private boolean isOnlyReader(Thread thread) {
        return readingThreads.size() == 1 && readingThreads.get(thread) != null;
    }


    /**
     * the thread is allowed to reenter only if the thread has write access already.
     * @throws InterruptedException
     */
    public synchronized void writeLock() throws InterruptedException {
        writeRequests++;
        while (!canWriterReenter(Thread.currentThread())) wait();
        writers++;
        writeRequests--;
        writingThread = Thread.currentThread();
    }

    public synchronized void writeUnlock() throws InterruptedException {
        if(--writers == 0) writingThread = null;
        notifyAll();
    }

    private boolean canWriterReenter(Thread thread) {
        if(isOnlyReader(thread)) return true;
        //has reader
        if(readingThreads.size() > 0) return false;
        // there's no writing thread or the current writing thread is itself.
        if(writingThread == null || writingThread == thread) return true;
        return false;
    }
}
