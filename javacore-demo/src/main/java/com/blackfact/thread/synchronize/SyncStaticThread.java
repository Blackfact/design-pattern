package com.blackfact.thread.synchronize;

public class SyncStaticThread implements Runnable{
    private static int count;

    public SyncStaticThread() {
        count = 0;
    }

    /*
    静态方法是属于类的而不属于对象的。同样的，synchronized修饰的静态方法锁定的是这个类的所有对象。
     */
    public synchronized static void method() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ": " + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        method();
    }
}
