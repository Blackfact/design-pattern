package com.blackfact.innerClass;

import java.util.HashMap;
import java.util.Map;

public class InnerTest {

    private static final int testA = 1;

    public void test(NoNameInnerClass innerClass){
        System.out.println("名称："+ innerClass.getName() + "  类型："+innerClass.type());
    }

    public static void main(String[] args) {
        //静态内部类
        StaticInnerClass.Inner inner1 = new StaticInnerClass.Inner();
        inner1.print();
        System.out.println();

        //静态内部类在HashMap中的使用。Entry是HashMap存放元素的抽象，HashMap内部维护Entry数组用来存放元素，但Entry对使用者透明
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(0,"a");
        map.put(1,"b");
        map.put(2,"c");
        for(Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey() + "value:"+entry.getValue());
        }
        System.out.println();

        // 成员内部类
        MemberInnerClass memberInnerClass = new MemberInnerClass();
        MemberInnerClass.Inner inner2 = memberInnerClass.new Inner();
        inner2.print();
        System.out.println();

        // 局部内部类
        LocalInnerClass localInnerClass = new LocalInnerClass();
        localInnerClass.test(3);

        // 匿名内部类
//        在使用匿名内部类的过程中，我们需要注意如下几点：
//        1、使用匿名内部类时，我们必须是继承一个类或者实现一个接口，但是两者不可兼得，同时也只能继承一个类或者实现一个接口。
//        2、匿名内部类中是不能定义构造函数的。
//        3、匿名内部类中不能存在任何的静态成员变量和静态方法。
//        4、匿名内部类为局部内部类，所以局部内部类的所有限制同样对匿名内部类生效。
//        5、匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。
        InnerTest test = new InnerTest();
        int testB = 1;
        test.test(new NoNameInnerClass() {

            public String getName() {
                return "匿名内部类";
            }
            @Override
            public int type() {
//                testB = 1;  编译错误，testB必须定义为final
                return 4;
            }
        });
        System.out.println(testA);
    }
}
