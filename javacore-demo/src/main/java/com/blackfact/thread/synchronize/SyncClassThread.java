package com.blackfact.thread.synchronize;

public class SyncClassThread implements Runnable {
    private static int count;

    public SyncClassThread() {
        count = 0;
    }

    public static void method() {
        synchronized(SyncClassThread.class) {
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " :" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        method();
    }
}
