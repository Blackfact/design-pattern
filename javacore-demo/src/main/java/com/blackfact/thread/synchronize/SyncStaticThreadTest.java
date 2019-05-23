package com.blackfact.thread.synchronize;

public class SyncStaticThreadTest {
    public static void main(String[] args) {
        SyncStaticThread syncStaticThread1 = new SyncStaticThread();
        SyncStaticThread syncStaticThread2 = new SyncStaticThread();
        Thread thread1 = new Thread(syncStaticThread1,"syncStaticThread1");
        Thread thread2 = new Thread(syncStaticThread2,"syncStaticThread2");
        thread1.start();
        thread2.start();
        // syncThread1和syncThread2是SyncThread的两个对象，但在thread1和thread2并发执行时却保持了线程同步。
        // 这是因为run中调用了静态方法method，而静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁。
    }
}
