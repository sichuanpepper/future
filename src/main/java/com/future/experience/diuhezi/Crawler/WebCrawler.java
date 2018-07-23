package com.future.experience.diuhezi.Crawler;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xingfeiy on 7/22/18.
 */
public class WebCrawler implements Runnable {
    private BlockingQueue<String> urlQueue = null;

    private BlockingQueue<String> contentQueue = null;

    private AtomicInteger counter = null;

    private ConcurrentHashMap<String, String> visited = null;

    public WebCrawler(BlockingQueue<String> urlQueue, BlockingQueue<String> contentQueue, AtomicInteger counter, ConcurrentHashMap visited) {
        this.urlQueue = urlQueue;
        this.contentQueue = contentQueue;
        this.counter = counter;
        this.visited = visited;
    }

    @Override
    public void run() {
        while (true) {
            if(counter.get() < 1 && urlQueue.isEmpty()) return;
            try {
//                String url = urlQueue.take();  //blocking by take()
                String url = urlQueue.poll(1000, TimeUnit.MILLISECONDS);  //blocking by take()
                if(url == null) {
                    System.out.println(Thread.currentThread().getName() + " is waiting for url.");
                    continue;
                }
                visited.put(url, Thread.currentThread().getName());
                System.out.println("Visited " + visited.size());
                System.out.println(Thread.currentThread().getName() + " is downloading data from " + url);
//                int c = counter.incrementAndGet();
                counter.set(counter.get() + 1);
                //simulate download content
                int elapsed = new Random().nextInt(5000);
                Thread.sleep(elapsed);
                contentQueue.put("Content from " + url + " elapsed: " + elapsed);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
