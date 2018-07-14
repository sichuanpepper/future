package com.future.experience;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by xingfeiy on 7/9/18.
 */
public class PhoneDirectory {
    private Queue<Integer> queue;
    private Set<Integer> set;
    int maxNumbers;

    public PhoneDirectory(int maxNumbers){
        queue = new LinkedList<>();
        set = new HashSet<>();
        this.maxNumbers = maxNumbers;

        for(int i = 0; i < maxNumbers; i++)
            queue.offer(i);
    }

    public int get(){
        if(!queue.isEmpty()){
            set.add(queue.peek());
            return queue.poll();
        }

        else return -1;
    }

    public boolean check(int number){
        if(number < 0 || number >= maxNumbers) return false;
        return !set.contains(number);
    }

    public void release(int number){
        if(!set.contains(number)) return;
        else{
            set.remove(number);
            queue.offer(number);
        }
    }

    public static void main(String args[]){
        PhoneDirectory pd = new PhoneDirectory(3);

        assert pd.get() == 0;
        assert pd.get() == 1;
        assert pd.check(2);
        assert pd.get() == 2;
        assert !pd.check(2);
        pd.release(2);
        assert pd.check(2);

        System.out.println("All passed!");
    }
}
