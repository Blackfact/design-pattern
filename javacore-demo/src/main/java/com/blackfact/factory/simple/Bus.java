package com.blackfact.factory.simple;

public class Bus implements Vehicle {

    @Override
    public void build() {
        System.out.println("生产Bus");
    }
}
