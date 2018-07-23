package com.future.foundation.java.multiplethreads;

/**
 * Created by xingfeiy on 7/22/18.
 */
public class HouseConcurrency {
    public synchronized static void sharedRoom() {
        System.out.println(Thread.currentThread().getName() + " is using shared room!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is leaving shared room!");
    }

    public synchronized void lockedRoom1() {
        System.out.println(Thread.currentThread().getName() + " is using locked room one!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is leaving locked room one!");
    }

    public synchronized void lockedRoom2() {
        System.out.println(Thread.currentThread().getName() + " is using locked room two!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is leaving locked room two!");
    }

    public void unlockedRoom() {
        System.out.println(Thread.currentThread().getName() + " is using unlocked room!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is leaving unlocked room!");
    }

    /**
     * Let's say we have a gym, but only one bathroom here, if we place a lock on the door of gym, that means
     * only one person can use the gym at same time, but actually multiple people are allowed entering gym for warming body
     */
    public void partialLockedRoom() {
        System.out.println(Thread.currentThread().getName() + " entered partial locked room!");
        for(int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + " is warming bodd! " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is waiting for shower room!");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " is using shower room!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is leaving shower room!");
        }
    }

    public void partialLockedRoom2() {
        System.out.println(Thread.currentThread().getName() + " entered partial locked room with enough bathrooms !");
        for(int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + " is warming bodd! " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is waiting for shower room!");
        byte[] bathroom = new byte[0];
        synchronized (bathroom) {
            System.out.println(Thread.currentThread().getName() + " is using shower room!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is leaving shower room!");
        }
    }

    public static void main(String[] args) {
        HouseConcurrency house = new HouseConcurrency();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                house.lockedRoom1();
            }
        }, "ThreadA");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                house.lockedRoom1();
            }
        }, "ThreadB");

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                house.unlockedRoom();
            }
        }, "ThreadC");

        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                house.unlockedRoom();
            }
        }, "ThreadD");

        //A and B are going to use same locked room, C and D are going to use unlocked room
//                ThreadA is using locked room one!
//                ThreadD is using unlocked room!
//                ThreadC is using unlocked room!
//                ThreadA is leaving locked room one!
//                ThreadD is leaving unlocked room!
//                ThreadC is leaving unlocked room!
//                ThreadB is using locked room one!
//                ThreadB is leaving locked room one!

//        threadA.start();
//        threadB.start();
//        threadC.start();
//        threadD.start();


        //A and B are going to use different locked room
        System.out.println("=================================");
        System.out.println("A and B are going to use different locked room");

        //there's only one key for instance
//                ThreadA is using locked room one!
//                ThreadA is leaving locked room one!
//                ThreadB is using locked room two!
//                ThreadB is leaving locked room two!

        threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                house.lockedRoom1();
            }
        }, "ThreadA");

        threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                house.lockedRoom2();
            }
        }, "ThreadB");

//        threadA.start();
//        threadB.start();

        System.out.println("=================================");
        System.out.println("A, B, C and D are going to gym!");
        threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom();
            }
        }, "ThreadA");

        threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom();
            }
        }, "ThreadB");

        threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom();
            }
        }, "ThreadC");

        threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom();
            }
        }, "ThreadD");

//                A, B, C and D are going to gym!
//                ThreadA entered partial locked room!
//                ThreadA is warming bodd! 0
//                ThreadB entered partial locked room!
//                ThreadC entered partial locked room!
//                ThreadC is warming bodd! 0
//                ThreadB is warming bodd! 0
//                ThreadD entered partial locked room!
//                ThreadD is warming bodd! 0
//                ThreadB is warming bodd! 1
//                ThreadD is warming bodd! 1
//                ThreadC is warming bodd! 1
//                ThreadA is warming bodd! 1
//                ThreadD is waiting for shower room!
//                ThreadD is using shower room!
//                ThreadA is waiting for shower room!
//                ThreadC is waiting for shower room!
//                ThreadB is waiting for shower room!
//                ThreadD is leaving shower room!
//                ThreadB is using shower room!
//                ThreadB is leaving shower room!
//                ThreadC is using shower room!
//                ThreadC is leaving shower room!
//                ThreadA is using shower room!
//                ThreadA is leaving shower room!
//        threadA.start();
//        threadB.start();
//        threadC.start();
//        threadD.start();


        System.out.println("=================================");
        System.out.println("A, B, C and D are going to a bigger gym with enough bathrooms!");
        threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom2();
            }
        }, "ThreadA");

        threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom2();
            }
        }, "ThreadB");

        threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom2();
            }
        }, "ThreadC");

        threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                house.partialLockedRoom2();
            }
        }, "ThreadD");

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

    }
}
