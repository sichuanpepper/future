package com.future.foundation.java.multiplethreads;

/**
 * The synchornized locks instance, if one thread is executing either add or sub, other threads can't execute neither add nor sub.
 *
 *
 * Thread-0 coming in add()
 * Thread-0: count =  1
 * Thread-1 coming in sub()
 * Thread-1: count =  0
 *
 * If there's no synchronized on sub(), it print:
 * Thread-0 coming in add()
 * Thread-1 coming in sub()
 * Thread-1: count =  -1
 * Thread-0: count =  0
 *
 * So if we use synchronized on a static method, means we locked on class.
 *
 * One thread per instance/class
 */
public class SyncKeyWord {
    private int count = 0;

    public synchronized void add(int n) {
        System.out.println(Thread.currentThread().getName() + " coming in add()");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.count += n;
        System.out.println(Thread.currentThread().getName() + ": count =  " + count);

    }

    public  void sub(int n) {
        System.out.println(Thread.currentThread().getName() + " coming in sub()");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.count -= n;
        System.out.println(Thread.currentThread().getName() + ": count =  " + count);
    }


    /**
     * Add synchronized on different monitor objects.
     *
     * Thread-0 coming in multi()
     * Thread-1 coming in div()
     * Thread-1: count =  0
     * Thread-0: count =  0
     *
     * Add it on same monitor object.
     *
     * Thread-0 coming in multi()
     * Thread-0: count =  0
     * Thread-1 coming in div()
     * Thread-1: count =  0
     */
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    public void multi(int n) {

        synchronized (lock1) {   //lock1 is the monitor object.
            System.out.println(Thread.currentThread().getName() + " coming in multi()");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.count *= n;
            System.out.println(Thread.currentThread().getName() + ": count =  " + count);
        }
    }

    public void div(int n) {

        synchronized (lock1) {   //lock2 is the monitor object.
            System.out.println(Thread.currentThread().getName() + " coming in div()");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.count /= n;
            System.out.println(Thread.currentThread().getName() + ": count =  " + count);
        }
    }

    public static void main(String[] args) {
        SyncKeyWord syncKeyWord = new SyncKeyWord();
        Thread thread1 = new Thread(()-> syncKeyWord.add(1));
        thread1.start();
        Thread thread2 = new Thread(()-> syncKeyWord.sub(1));
        thread2.start();
//        Thread thread1 = new Thread(()-> syncKeyWord.multi(1));
//        thread1.start();
//        Thread thread2 = new Thread(()-> syncKeyWord.div(1));
//        thread2.start();
    }
}
