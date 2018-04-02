package com.future.ood.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xingfeiy on 3/26/18.
 */
public class ResourceMQ<T> {
    private int bufferSize = 100000;

    private Queue<T> queue = new LinkedList<>();

    public ResourceMQ(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void push(T t) {
        queue.offer(t);
        if(queue.size() / (float)bufferSize >= 0.9) {
            WorkerCoordinator.getInstance().requireMoreConsumer();
        }
    }

    public T poll() {
        if(queue.size() / (float)bufferSize <= 0.1) {
            WorkerCoordinator.getInstance().requireMoreProducer();
        }
        return queue.poll();
    }
}
