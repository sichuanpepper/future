package com.future.experience.linying.eley.delayQueue;

import java.util.PriorityQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * https://www.jianshu.com/p/e0bcc9eae0ae
 * https://blog.csdn.net/dkfajsldfsdfsd/article/details/88966814#:~:text=DelayQueue%E5%9F%BA%E6%9C%AC%E5%8E%9F%E7%90%86,%E5%AE%83%E4%BB%AC%E8%BF%9B%E5%85%A5%E9%98%9F%E5%88%97%E7%9A%84%E9%A1%BA%E5%BA%8F%E3%80%82
 *
 * Leader-follower pattern
 * Leader is responsible for check/poll peek, and followers standby, once leader is processing an element, one follower will become to leader.
 *
 * @param <E>
 */
public class DelayQueue<E extends Delayed> {
    /**
     * ReentrantLock: https://www.journaldev.com/2377/java-lock-example-reentrantlock
     * transient: is a keyword in Java is used to avoid serialization, https://www.educative.io/edpresso/what-is-the-transient-keyword-in-java
     */
    private final transient ReentrantLock lock = new ReentrantLock();

    private final PriorityQueue<E> q = new PriorityQueue<E>();

    private final Condition available = lock.newCondition();
    private Thread leader = null;

    public DelayQueue() {}

    /**
     * Inserts the specified element into this delay queue.
     *
     * @param e the element to add
     * @return {@code true}
     * @throws NullPointerException if the specified element is null
     */
    public boolean put(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            q.offer(e);
            if (q.peek() == e) {   //the new element comes to the peek, set the leader to null and notify sleeping threads.
                leader = null;
                available.signal();
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves and removes the head of this queue, waiting if necessary
     * until an element with an expired delay is available on this queue.
     *
     * @return the head of this queue
     * @throws InterruptedException {@inheritDoc}
     */
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            for (;;) {
                E first = q.peek();
                if (first == null) //the queue is empty, thread change status to wait()
                    available.await();
                else {
                    long delay = first.getDelay(NANOSECONDS);  //get the first delay
                    if (delay <= 0)
                        return q.poll();
                    first = null; // don't retain ref while waiting
                    if (leader != null)  //there's other thread(leader) is waiting for the peek element
                        available.await();
                    else {
                        Thread thisThread = Thread.currentThread(); //no leader is waiting for the peek, current thread become to leader and waiting for peek
                        leader = thisThread;
                        try {
                            available.awaitNanos(delay);
                        } finally {
                            if (leader == thisThread)  //if something wrong and it's still leader, release leader.
                                leader = null;
                        }
                    }
                }
            }
        } finally {
            if (leader == null && q.peek() != null)  //if something wrong, leader is null and peek is not null, notify sleeping threads.
                available.signal();
            lock.unlock();
        }
    }
}