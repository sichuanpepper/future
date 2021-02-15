package com.future.foundation.java.multiplethreads.threads;

//import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xingfeiy on 4/3/18.
 */
public class ThreadCallable implements Callable<Integer> {
    private int f = 0;

    private int count = 0;

    public ThreadCallable(int f) {
        this.f = f;
    }

    @Override
    public Integer call() throws Exception {
        count++;
        System.out.println(Thread.currentThread().getName() + " " + count);
        int first = 1;
        int second = 2;
        int res = 3;
        for(int i = 3; i < f; i++) {
            int tmp = first + second;
            res += tmp;
            first = second;
            second = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> res = new ArrayList<>();
        for(int i = 10; i < 100; i++) {
            Future<Integer> future = executorService.submit(new ThreadCallable(10));
            res.add(future);
        }

        for(Future future : res) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
