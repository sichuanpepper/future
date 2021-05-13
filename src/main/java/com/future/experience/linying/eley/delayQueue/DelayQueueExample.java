package com.future.experience.linying.eley.delayQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue is an implementation of BlockingQueue interface.
 * DelayQueue is unbounded implementation of BlockingQueue, other implementations
 *  - ArrayBlockingQueue, always bounded.
 *  - LinkedBlockingQueue, both bounded and unbounded options.
 *  - PriorityBlockingQueue, unbounded.
 *
 *  DelayQueue stores Delayed(an interface) elements.
 *  When you implement Delayed interface, two methods have to be implemented.
 *  - getDelay(TimeUnit unit)
 *  - compareTo(T o)
 */
public class DelayQueueExample {
    private static class DelayClass implements Delayed {
        private String job;

        private long expireTime;

        public DelayClass(String job, long expireTime) {
            this.job = job;
            this.expireTime = System.currentTimeMillis() + expireTime;  //the absolute time
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = this.expireTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "DelayClass{" +
                    "job='" + job + '\'' +
                    ", expireTime=" + expireTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        BlockingQueue<DelayClass> delayQueue = new DelayQueue<>();

        //create a producer
        new Thread(()->{
            for(int i = 0; i < 5; i++) {
                try {
                    System.out.println("Producer produced - " + i);
                    delayQueue.put(new DelayClass("job " + i, 500 * (i + 1)));
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        //create a consumer
        new Thread(()->{
            for(int i = 0; i < 5; i++) {
                try {
                    System.out.println("Consumer got - " + delayQueue.take());
//                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }).start();
    }
}
