package com.blackfact.thread.basic.join;

public class TestJoin {

    public static void main(String[] args) {

        MyThread2 t1 = new MyThread2("TestJoin");
        t1.start();
        try {
            t1.join();  //join()合并线程，子线程运行完之后，主线程才开始执行
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread1全部执行完成后，才能执行主线程。");
    }
}