package com.blackfact.thread.lock;

public class SemaphoreThreadTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(new SemaphoreThread());
        Thread t2 = new Thread(new SemaphoreThread());
        Thread t3 = new Thread(new SemaphoreThread());
        Thread t4 = new Thread(new SemaphoreThread());
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
