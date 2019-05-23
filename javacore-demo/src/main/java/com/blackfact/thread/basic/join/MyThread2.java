package com.blackfact.thread.basic.join;

class MyThread2 extends Thread {

    MyThread2(String s) {
        super(s);
    }

    public void run() {
        for(int i = 1; i <= 10; i++) {
            System.out.println("I am "+getName());
            try {
                sleep(1000); //暂停，每一秒输出一次
            }catch (InterruptedException e) {
                return;
            }
        }
    }
}
