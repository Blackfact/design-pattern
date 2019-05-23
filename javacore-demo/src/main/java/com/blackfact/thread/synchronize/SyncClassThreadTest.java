package com.blackfact.thread.synchronize;

public class SyncClassThreadTest {
    public static void main(String[] args) {
        SyncClassThread syncClassThread1 = new SyncClassThread();
        SyncClassThread syncClassThread2 = new SyncClassThread();
        Thread thread1 = new Thread(syncClassThread1,"syncClassThread1");
        Thread thread2 = new Thread(syncClassThread2,"syncClassThread2");
        thread1.start();
        thread2.start();
    }
}
