package com.blackfact.thread.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        // //初始化CountDownLatch并设置初始计数值
        final CountDownLatch latch = new CountDownLatch(2);
        try {
            new Thread() {
                public void run() {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown(); //将计数值-1
                }

                ;
            }.start();
            new Thread() {
                public void run() {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown(); //将计数值-1
                }

                ;
            }.start();
            System.out.println("等待  2 个子线程执行完毕...");
            latch.await(); // 等待直至计数值为0
            System.out.println("2 个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
