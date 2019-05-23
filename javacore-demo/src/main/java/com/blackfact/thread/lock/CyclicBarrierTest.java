package com.blackfact.thread.lock;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {

        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i = 0; i<N; i++)
            new Thread(new CyclicBarrierThread(barrier),"thread"+i).start();

    }
}
