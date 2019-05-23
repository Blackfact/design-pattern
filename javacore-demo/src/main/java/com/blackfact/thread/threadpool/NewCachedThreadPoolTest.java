package com.blackfact.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolTest {
    public static void main(String[] args) {
        // 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    // 打印出当前线程的名字，发现是同一个
                    System.out.println(index + " : " +Thread.currentThread().getName());
                }
            });

        }
        // 使用后需要关闭
        cachedThreadPool.shutdown();

    }
}
