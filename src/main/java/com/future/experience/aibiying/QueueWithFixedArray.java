package com.future.experience.aibiying;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xingfeiy on 6/14/18.
 */
public class QueueWithFixedArray {
    private int lengthOfArray = 10;

    private List<int[]> list = new LinkedList<>();

    //always points first element
    private int head = -1;

    //always points to last element
    private int tail = -1;


    public QueueWithFixedArray(int lengthOfArray) {
        this.lengthOfArray = lengthOfArray;
        list.add(new int[this.lengthOfArray]);
    }

    /**
     * if current tail points to the end of current array, add new array.
     * the insertion position is ++tail % lengthOfArray
     * @param val
     */
    public void offer(int val) {
        if(tail % (lengthOfArray - 1) == 0) {
            list.add(new int[lengthOfArray]);
        }
        list.get(list.size() - 1)[(++tail) % lengthOfArray] = val;
        if(head == -1) head =0;
    }

    public int poll() {
        if(head < 0 || head > tail) return Integer.MIN_VALUE;
        int res = list.get(0)[head % lengthOfArray];
        if(head % (lengthOfArray - 1) == 0) {
            list.remove(0);
        }
        head++;
        return res;
    }

    public static void main(String[] args) {
        QueueWithFixedArray queue = new QueueWithFixedArray(3);
        System.out.println(queue.poll());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }


}
