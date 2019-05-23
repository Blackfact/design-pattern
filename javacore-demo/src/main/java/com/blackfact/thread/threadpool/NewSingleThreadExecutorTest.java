package com.blackfact.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingleThreadExecutorTest {
    public static void main(String[] args) {
        // 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + " : " + Thread.currentThread().getName());
                }
            }); // 结果依次输出，相当于顺序执行各个任务。

        }
        singleThreadExecutor.shutdown();
    }
    
}
