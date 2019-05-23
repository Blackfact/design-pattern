package com.blackfact.thread.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 方法一：继承Thread类
        MyThread1 myThread1 = new MyThread1();
        myThread1.run();


        // 方法二：实现Runnable接口
        Thread myThread2 = new Thread(new MyRunnable());
        myThread2.run();

        // 方法三：实现Callable接口
        //创建线程池
        int taskSize = 10;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取 Future 对象(执行Callable 任务后，可以获取一个Future的对象)
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从 Future 对象上获取任务的返回值，并输出到控制台（在该对象上调用get就可以获取到Callable 任务返回的Object 了）
            System.out.println("res：" + f.get().toString());
        }

        //方法四：基于线程池的方式（利用了线程池的缓存策略，避免每次都重复创建和销毁，节约了资源）
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        while(true){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running..");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }
}
