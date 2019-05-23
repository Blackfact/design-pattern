package com.blackfact.thread.synchronize;

public class SyncMethodThread implements Runnable {
    private static int count;

    public SyncMethodThread() {
        count = 0;
    }

    public synchronized void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCount() {
        return count;
    }
}