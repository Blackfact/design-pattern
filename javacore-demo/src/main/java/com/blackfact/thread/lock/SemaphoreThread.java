package com.blackfact.thread.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreThread implements Runnable {
    // 当阀值设为1，则可类似于互斥锁的机制
    private static Semaphore semp = new Semaphore(1);
    private static int count;

    @Override
    public void run() {
//        // 尝试获取单个许可，如果无可用许可直接返回false，不会阻塞
//        if(semp.tryAcquire()){
//            try {
//                // 实际业务逻辑
//                for (int i = 0; i < 5; i++) {
//                    System.out.println(Thread.currentThread().getName() + " :" + (count++));
//                    Thread.sleep(100);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                semp.release();
//            }
//        }

        try {
             // 申请许可,获取单个许可，如果无可用许可前将一直阻塞等待
            semp.acquire();

            try {
                // 实际业务逻辑
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " :" + (count++));
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semp.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
