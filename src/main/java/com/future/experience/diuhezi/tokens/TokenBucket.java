package com.future.experience.diuhezi.tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by xingfeiy on 7/21/18.
 */
public class TokenBucket<E> extends ArrayBlockingQueue<E> {
    private int capacity = 1000;

    public TokenBucket(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    public List<E> getTokens(int n) {
        List<E> res = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            while (size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res.add(poll());
        }
        return res;
    }

    public void inputTokens(List<E> tokens) {
        for(int i = 0; i < tokens.size(); i++) {
            while (size() >= capacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            offer(tokens.get(i));
        }
    }

}
