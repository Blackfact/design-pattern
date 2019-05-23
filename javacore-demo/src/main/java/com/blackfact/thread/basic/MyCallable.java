package com.blackfact.thread.basic;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    private String s;
    public MyCallable(String s) {
        this.s = s;
    }

    @Override
    public Object call() throws Exception {
        return s + "实现Callable，实现有返回值的线程的创建";
    }
}
