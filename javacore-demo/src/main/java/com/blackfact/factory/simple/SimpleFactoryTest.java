package com.blackfact.factory.simple;

public class SimpleFactoryTest {
    public static void main(String[] args) {
//        Vehicle vehicle = new Bike();
//        vehicle.build();

        VehicleSimpleFactory vehicleSimpleFactory = new VehicleSimpleFactory();
//        Vehicle bus = vehicleSimpleFactory.create("bus");
//        bus.build();

//        Vehicle vehicle = vehicleSimpleFactory.create("com.black.air.factory.simple.Bus");
//        vehicle.build();
        Vehicle vehicle = vehicleSimpleFactory.create(Bus.class);
        vehicle.build();



    }
}
