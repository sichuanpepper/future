package com.future.foundation.threads;

/**
 * Created by xingfeiy on 4/3/18.
 */
public class ThreadExtend extends Thread {
    private int counter = 0;

    @Override
    public synchronized void start() {
        counter++;
        System.out.println(Thread.currentThread().getName() + " starts..., and counter is " + counter);
        super.start();
    }

    @Override
    public void run() {
//        counter++;
        System.out.println(Thread.currentThread().getName() + " is running..., and counter is " + counter);
        super.run();
    }


    public static void main(String[] args) {
        ThreadExtend thread1 = new ThreadExtend();
        // display "main is running..., and counter is 1"
        thread1.run();

        ThreadExtend thread2 = new ThreadExtend();
        // display "main is running..., and counter is 1"
        thread2.run();

        ThreadExtend thread3 = new ThreadExtend();
        // display "main is running..., and counter is 1"
        thread3.run();

        // Just like what we saw here,
        // - run() doesn't create a new thread.


        System.out.println("start new threads");
        thread1.start();
        thread2.start();
        thread3.start();

        //start new threads
//        main starts..., and counter is 1
//        main starts..., and counter is 1
//        main starts..., and counter is 1
//        Thread-0 is running..., and counter is 1
//        Thread-1 is running..., and counter is 1
//        Thread-2 is running..., and counter is 1

    }
}
