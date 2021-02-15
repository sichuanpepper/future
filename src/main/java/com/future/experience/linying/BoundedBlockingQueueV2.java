package com.future.experience.linying;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        System.out.println(Thread.currentThread().getName() + " entered enqueue...");
        while (this.queue.size() == this.limit) {
            //The wait() method causes the current thread to wait indefinitely until another thread either invokes notify() for this object or notifyAll().
            System.out.println(Thread.currentThread().getName() + " is waiting in enqueue...");
            wait(); //guarded blocks, suspend thread
        }
//        if (this.queue.size() == 0) {
//            The notify() method is used for waking up threads that are waiting for an access to this object's monitor.
//            notifyAll();
//        }
        this.queue.add(item);
        System.out.println(Thread.currentThread().getName() + " added item : " + item);
        notifyAll();
    }

    // dequeue methods that throws Exception
    // when you try to remove element from an
    // empty queue
    public synchronized E dequeue()
            throws InterruptedException
    {
        System.out.println(Thread.currentThread().getName() + " entered dequeue...");
        while (this.queue.size() == 0) {
            System.out.println(Thread.currentThread().getName() + " is waiting in dequeue...");
            wait();
        }
//        if (this.queue.size() == this.limit) {
//
//        }

        E res = this.queue.remove(0);
        System.out.println(Thread.currentThread().getName() + " consumed item : " + res);
        notifyAll();
        return res;
    }

    public static void main(String[] args) {
        BoundedBlockingQueueV2<Integer> bq = new BoundedBlockingQueueV2(5);
        Thread enThread = new Thread(()->{
            try {
                for(int i = 0; i < 10; i++) {
                    Thread.sleep(new Random().nextInt(5) * 1000);
                    bq.enqueue(new Random().nextInt(10));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        Thread deThread = new Thread(()->{
            try {
                for(int i = 0; i < 10; i++) {
                    Thread.sleep(new Random().nextInt(5) * 1000);
                    bq.dequeue();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        enThread.start();
        deThread.start();
    }
}
