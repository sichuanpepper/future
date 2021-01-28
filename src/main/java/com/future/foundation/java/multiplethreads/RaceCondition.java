package com.future.foundation.java.multiplethreads;

import java.util.Random;

/**
 * Read-modify-write
 * Check-then-act
 */

public class RaceCondition {
    private int sum1 = 0;

    private int sum2 = 0;

    private Integer lock1 = new Integer(1);
    private Integer lock2 = new Integer(2);

    public  void add(int val1, int val2)  {
        System.out.println("Thread: " + Thread.currentThread().getName() + " is coming to sum1");
        synchronized (this.lock1) {
            this.sum1 += val1;
            try {
                System.out.println("Current Thread in sum1 : " + Thread.currentThread().getName());
                Thread.sleep(new Random().nextInt(100));
            } catch (Exception ex) {}
        }

        System.out.println("Thread: " + Thread.currentThread().getName() + " is coming to sum2");
        synchronized (this.lock2) {
            this.sum2 += val2;
            try {
                System.out.println("Current Thread: in sum2 : " + Thread.currentThread().getName());
                Thread.sleep(new Random().nextInt(100));
            } catch (Exception ex) {}
        }


        System.out.println(Thread.currentThread().getName() + " sum1 = " + this.sum1 + " , sum2 = " + this.sum2);
    }

    public static void main(String[] args) {
        RaceCondition rc = new RaceCondition();
        new Thread(()-> rc.add(1, 2)).start();
        new Thread(()-> rc.add(100, 200)).start();
    }
}
