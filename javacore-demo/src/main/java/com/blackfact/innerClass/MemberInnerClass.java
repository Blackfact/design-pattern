package com.blackfact.innerClass;
/*
  成员内部类 - 不能定义静态变量和方法(类初始化的时候先初始化静态成员，如果允许成员内部类定义静态变量，那么成员内部类的静态变量初始化顺序是有歧义的)
 */
public class MemberInnerClass {

    private static int a;

    private int b;

    public class Inner{
//      private static int c;
        public void print(){
            System.out.println("成员内部类");
            System.out.println(a);
            System.out.println(b);
        }
    }
}
