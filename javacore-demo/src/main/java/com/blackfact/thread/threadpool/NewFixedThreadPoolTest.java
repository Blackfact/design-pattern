package com.blackfact.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class NewFixedThreadPoolTest {
    public static void main(String[] args) {
        // 因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        new LongAdder();
        for(int i=0;i<10;i++){
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + " : " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            
        }
        // 使用后需要关闭
        fixedThreadPool.shutdown();

    }
}
