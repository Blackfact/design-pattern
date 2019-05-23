package com.blackfact.annotation;


import java.lang.reflect.Field;

public class AnnotationTest {
    public static void main(String[] args) throws Exception{
        Class clazz = Class.forName("com.black.air.factory.annotation.Apple");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field: declaredFields) {
            if(field.isAnnotationPresent(Fruit.class)){
                Fruit fruit = field.getAnnotation(Fruit.class);
                System.out.println(fruit.name());
                System.out.println(fruit.type());
            }
        }

    }
}
