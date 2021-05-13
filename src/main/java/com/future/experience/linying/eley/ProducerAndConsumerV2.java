package com.future.experience.linying.eley;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProducerAndConsumerV2 <E> {
    private int capacity = 3;

    private Queue<E> queue = new LinkedList<>();

    private Semaphore putSem = new Semaphore(this.capacity);

    private Semaphore takeSem = new Semaphore(0);

    public void put(E e) {
        try {
            putSem.acquire();
            this.queue.offer(e);
            System.out.println("The size of queue is: " + this.queue.size());
            takeSem.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public E take() {
        try {
            takeSem.acquire();
            E e = this.queue.poll();
            putSem.release();
            return e;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ProducerAndConsumerV2<Integer> p = new ProducerAndConsumerV2();
        Random random = new Random();
        //start a producer
        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                int num = random.nextInt(100);
                System.out.println(System.currentTimeMillis() + " produced number : " + (i + 1));
                try {
                    p.put(i + 1);
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
                    Thread.sleep(random.nextInt(100) * 100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
