package com.future.ood.producer_consumer;

/**
 * Created by xingfeiy on 3/26/18.
 */
public class WorkerCoordinator {
    private int countOfThreads = 10;

    private int countOfProducer = 5;

    public static WorkerCoordinator instance = null;

    private WorkerCoordinator() {}

    public static WorkerCoordinator getInstance() {
        if(instance == null) {
            instance = new WorkerCoordinator();
        }
        return instance;
    }

    public void requireMoreConsumer() {}

    public void requireMoreProducer() {}

}
