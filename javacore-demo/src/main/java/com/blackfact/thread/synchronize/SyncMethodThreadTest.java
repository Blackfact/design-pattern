package com.blackfact.thread.synchronize;

public class SyncMethodThreadTest {
    public static void main(String[] args) {
        SyncMethodThread syncMethodThread = new SyncMethodThread();
        Thread thread1 = new Thread(syncMethodThread, "syncMethodThread1");
        Thread thread2 = new Thread(syncMethodThread, "syncMethodThread2");
        thread1.start();
        thread2.start();
    }
}
