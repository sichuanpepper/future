package com.future.experience.diuhezi.Crawler;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xingfeiy on 7/22/18.
 */
public class ContentAnalyzer implements Runnable {
    private BlockingQueue<String> urlQueue = null;

    private BlockingQueue<String> contentQueue = null;

    private ConcurrentHashMap<String, String> visited = new ConcurrentHashMap();

    private AtomicInteger counter = null;


    public ContentAnalyzer(BlockingQueue<String> urlQueue, BlockingQueue<String> contentQueue, AtomicInteger counter, ConcurrentHashMap<String, String> visited ) {
        this.urlQueue = urlQueue;
        this.contentQueue = contentQueue;
        this.counter = counter;
        this.visited = visited;
    }

    @Override
    public void run() {
        while (true) {
            if(counter.get() < 1 && contentQueue.isEmpty()) return;
            try {
                String content = contentQueue.poll(3000, TimeUnit.MILLISECONDS);
                if(content == null) {
                    System.out.println(Thread.currentThread().getName() + " is waiting for content!");
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + " is analyzing " + content);

                //simulate content analyze
                Thread.sleep(new Random().nextInt(1000));
                //simulate the urls returned by analyze function
                int countOfURLs = new Random().nextInt(6);
                System.out.println("Found links: " + countOfURLs);
                countOfURLs = (countOfURLs % 2== 0) ? 0 : countOfURLs;
                for(int i = 0; i < countOfURLs; i++) {
                    System.out.println("Add new URL: " + countOfURLs);
                    if(!visited.containsKey("URL: " + i)) this.urlQueue.put("URL: " + i);
                }
                //finish
                counter.set(counter.get() - 1);
                System.out.println(Thread.currentThread().getName() + " finished, current counter " + counter.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
