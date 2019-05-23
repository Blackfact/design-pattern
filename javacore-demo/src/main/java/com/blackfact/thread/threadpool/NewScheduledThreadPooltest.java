package com.blackfact.thread.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPooltest {
    public static void main(String[] args) {
        // 建一个定长线程池，支持定时及周期性任务执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        // 延迟执行示例代码如下：
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟3秒执行");
            }
        },3, TimeUnit.SECONDS);  //表示延迟3秒执行。

        // 定期执行示例代码如下：
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟10秒后,每3秒执行一次。");
            }
        },10,3,TimeUnit.SECONDS);  // 表示延迟10秒后每3秒执行一次。

//        // 使用后需要关闭(不需要关闭，不然周期线程无法执行)
//        scheduledThreadPool.shutdown();
    }
}
