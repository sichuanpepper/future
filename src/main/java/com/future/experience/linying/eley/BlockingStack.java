package com.future.experience.linying.eley;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Semaphore;

/**
 * Implement a multi-threaded stack, with freedom to use existing implementations of stack. On top of being thread-safe, it must block ( not busy-wait )
 * a pushing thread when stack is full and a popping thread when stack is empty.
 *
 * It must signal the threads out of sleep when stack has space again or elements to pop. Fairness is optional in this question, and may be a good followup.
 * @param <E>
 */
public class BlockingStack <E> {
    private int capacity;

    private Semaphore pushSem;

    private Semaphore popSem;

    private Stack<E> stack = new Stack<>();

    public BlockingStack(int capacity) {
        this.capacity = capacity;
        pushSem = new Semaphore(this.capacity, true);
        popSem = new Semaphore(0, true);
    }

    public void push(E e) {
        try {
            pushSem.acquire();
            stack.push(e);
            popSem.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public E pop() {
        try {
            popSem.acquire();
            E e = stack.pop();
            pushSem.release();
            return e;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BlockingStack<Integer> stack = new BlockingStack<>(3);
        new Thread(()->{
            try {
                for(int i = 0; i < 10; i++) {
                    Thread.sleep(new Random().nextInt(1000));
                    stack.push(i);
                    System.out.println("Producer produced number " + i);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for(int i = 0; i < 10; i++) {
                    int val = stack.pop();
                    System.out.println("Consumer got " + val);
                    Thread.sleep(new Random().nextInt(2000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }).start();
    }
}
