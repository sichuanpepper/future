package com.future.experience.linying.eley.delayQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Delayed scheduling problem:
 * 1. Start by asking the candidate how to implement the following interface that executes a given task at a specified future time:
 *
 * Thoughts:
 * - For the scheduler, there're two roles, producer and consumer,
 *      - producer takes two parameters to create a Delayed object, and put it to DelayQueue.
 *      - consumer start a while loop and continue taking the delayed object from DelayQueue.
 * - Implement a Delayed object, have to implement two methods:  (Note, the time should convert to absolute time.)
 *      - getDelay(TimeUnit unit) - (absolute time - current time)
 *      - compareTo(T o)
 *
 *
 */
public class DelayQueueExample2 {

    public interface DelayedScheduler {
        void delayedRun(long timeToRun, Runnable task);

        void start();

        void end();
    }
//    public interface Runnable {
//        public void run();
//    }

    public static class MyTask implements Runnable {
        private String taskName = "";

        public MyTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(this.taskName + " is running...");
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "taskName='" + taskName + '\'' +
                    '}';
        }
    }

    public static class MyDelayedScheduler implements DelayedScheduler {

        private BlockingQueue<MyDelayedObj> delayQueue = new DelayQueue<>();

        private AtomicInteger status = new AtomicInteger(0);
        @Override
        public void delayedRun(long timeToRun, Runnable task) {
            try {
                System.out.println("Insert a task : " + task);
                delayQueue.put(new MyDelayedObj(timeToRun, task));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        // implement here

        public void start() {
            status.set(1);
            new Thread(() -> {
                while (status.get() == 1) {
                    try {
                        MyDelayedObj obj = delayQueue.take();
                        obj.task.run();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }

        public void end() {
            System.out.println("ending scheduler...");
            status.set(0);
        }
    }

    public static class MyDelayedObj implements Delayed {
        private long expired;

        private Runnable task;

        public MyDelayedObj(long expired, Runnable task) {
            this.expired = System.currentTimeMillis() + expired;
            this.task = task;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = this.expired - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "MyDelayedObj{" +
                    "expired=" + expired +
                    ", task=" + task +
                    '}';
        }
    }

    public static void main(String[] arg) {
        MyDelayedScheduler scheduler = new MyDelayedScheduler();
        scheduler.start();
        scheduler.delayedRun(1000, new MyTask("1000 delayed task!"));
        scheduler.delayedRun(2000, new MyTask("2000 delayed task!"));
        scheduler.delayedRun(4000, new MyTask("4000 delayed task!"));
        scheduler.delayedRun(3000, new MyTask("3000 delayed task!"));


        try {
            Thread.sleep(6000);
            scheduler.end();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


}
