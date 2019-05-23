package com.blackfact.factory.simple;

public class Car implements Vehicle {


    @Override
    public void build() {

        System.out.println("生产Car");
    }
}
