package com.blackfact.innerClass;
/*
  局部内部类 - 不能有public、protected、private以及static修饰符的
  局部内部类只能访问方法中声明的final类型的变量。--JDK1.8版本后不需要了,但是不允许在方法中修改变量。
 */
public class LocalInnerClass {
    private static int a;

    private int b;

    public void test(int m){
        int d = 1;

        class Inner{
            public void print(){
//                m = 2;   编译报错，提示使用final类型
                System.out.println("局部内部类");
                System.out.println(a);
                System.out.println(b);
                System.out.println(m);
                System.out.println(d);
            }
        }
        //只能在这里调用，方法一
//        Inner inner = new Inner();
//        inner.print();

        // 方法二
        new Inner().print();
    }
}
