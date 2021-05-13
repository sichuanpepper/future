package com.future.foundation.java.multiplethreads.course;

/**
 * The life cycle of a thread
 * - New, create a new thread by using new Thread()
 * - Runnable, once we called start() method, the state moved from new to runnable.
 * - Blocked, it moves to this state when it's waiting for a monitor lock or other locks.
 * - Waiting, it moves to this state when it's waiting for other thread to perform a particular action. A thread can be enter this state by calling
 *      - wait()
 *      - join()
 *
 * - Timed_waiting, when it's waiting for another thread to perform a particular action within a time.
 * - Terminated, the execution is finished or was terminated abnormally.
 *
 * https://www.baeldung.com/java-thread-lifecycle
 *
 *
 * Create A Thread
 *  - Extends Thread and overwrite run()
 *  - Implements Runnable interface
 */
public class C003CreateThread {

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running. Extends from MyThread");
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running. Implementation of MyThread");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        MyRunnable runnable = new MyRunnable();
        runnable.run(); //it won't create new thread

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running. Annoymous way");
            }
        }).start();

        new Thread(()->System.out.println(Thread.currentThread().getName() + " is running. Lambda way")).start();

    }
}
