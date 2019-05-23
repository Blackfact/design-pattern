package com.blackfact.innerClass;

/*
 静态内部类
 */
public class StaticInnerClass {

    private static int a;

    private int b;

    private static void test(){
        System.out.println("外部方法");
    }

    public static class Inner{
        public void print(){
            System.out.println("静态内部类");
            test();
            System.out.println(a);
//            System.out.println(b); 无法访问外部类的非静态变量，会报错。
        }
    }
}
