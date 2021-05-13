package com.future.experience.linying.eley;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Assume M producers and N consumers share a fixed-size buffer. Producers generate data (integers in our case) and places
 * it in the buffer while consumers remove data from the buffer. The producers and consumers work independently and may produce/consume at different rates.
 *
 * Implement both a producer and consumer thread (Runnable in Java) that work with a single, bounded, non-thread safe Queue/Buffer.
 * Implement a main method that spawns 1 producer and 1 consumer, such that each producer producers K integers.
 * The program should shutdown gracefully after all producers and consumers have finished their work and the queue is empty.
 *
 * Semaphore vs Lock
 * Semaphore allows multiple threads access one resource at same time
 * Lock is mutex way that allows only one thread access one resource at same time.
 */
public class ProducerAndConsumer <E> {

    private Queue<E> queue = new LinkedList<>();

    private int capacity = 10;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition(); //not full, the condition for write

    private final Condition notEmpty = lock.newCondition(); //not empty, the condition for read

    private AtomicBoolean isRunning = new AtomicBoolean(true);

    public void put(E e) {
        lock.lock();
        try {
            while (this.queue.size() == this.capacity) {
                notFull.await();  //current is full, block write
            }
            this.queue.offer(e);
            notEmpty.signalAll();  //is not empty, wake read
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void finishedProduce() {}

    public E take() {
        lock.lock();
        try {
            while (this.queue.size() == 0) {
                notEmpty.await();  // is empty, block read
            }

            E e = queue.poll();
            notFull.signalAll();  //not full, wake write
            return e;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        ProducerAndConsumer<Integer> p = new ProducerAndConsumer();
        Random random = new Random();
        //start a producer
        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                int num = random.nextInt(100);
                System.out.println(System.currentTimeMillis() + " produced number : " + num);
                try {
                    p.put(num);
                    Thread.sleep(num * 10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        //start a consumer
        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                int num = p.take();
                System.out.println(System.currentTimeMillis() + " consumed number: " + num);
                try {
                    Thread.sleep(random.nextInt(100) * 10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
