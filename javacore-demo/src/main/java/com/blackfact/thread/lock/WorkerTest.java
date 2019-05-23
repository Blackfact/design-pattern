package com.blackfact.thread.lock;

import java.util.concurrent.Semaphore;

/*
若一个工厂有 5 台机器，但是有 8 个工人，
一台机器同时只能被一个工人使用，只有使用完 了，其他工人才能继续使用。那么我们就可以通过 Semaphore 来实现：
 */
public class WorkerTest {
    public static void main(String[] args) {
        int workerNum = 8; // 工人总数
        Semaphore semaphore = new Semaphore(5); //机器数目
         for(int i=0;i<workerNum;i++){
             new Worker(i,semaphore).start();
         }

    }
}
