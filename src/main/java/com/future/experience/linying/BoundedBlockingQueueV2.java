package com.future.experience.linying;

import java.util.LinkedList;
import java.util.List;

/**
 * Notice how notifyAll() is only called from enqueue() and dequeue() if the queue size is equal to the size bounds (0 or limit).
 * If the queue size is not equal to either bound when enqueue() or dequeue() is called, there can be no threads waiting to either enqueue or dequeue items.
 * @param <E>
 */
public class BoundedBlockingQueueV2<E> {
    // BlockingQueue using LinkedList structure
    // with a constraint on capacity
    private List<E> queue = new LinkedList<E>();

    // limit variable to define capacity
    private int limit = 10;

    // constructor of BlockingQueue
    public BoundedBlockingQueueV2(int limit) {
        this.limit = limit;
    }

    // enqueue method that throws Exception
    // when you try to insert after the limit
    public synchronized void enqueue(E item)
            throws InterruptedException
    {
        while (this.queue.size() == this.limit) {
            //The wait() method causes the current thread to wait indefinitely until another thread either invokes notify() for this object or notifyAll().
            wait(); //guarded blocks, suspend thread
        }
//        if (this.queue.size() == 0) {
//            The notify() method is used for waking up threads that are waiting for an access to this object's monitor.
//            notifyAll();
//        }
        this.queue.add(item);
        notifyAll();
    }

    // dequeue methods that throws Exception
    // when you try to remove element from an
    // empty queue
    public synchronized E dequeue()
            throws InterruptedException
    {
        while (this.queue.size() == 0) {
            wait();
        }
//        if (this.queue.size() == this.limit) {
//
//        }

        E res = this.queue.remove(0);
        notifyAll();
        return res;
    }
}
